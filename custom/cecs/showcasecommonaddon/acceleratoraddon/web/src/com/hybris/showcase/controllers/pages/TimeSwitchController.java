/**
 *
 */
package com.hybris.showcase.controllers.pages;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping(TimeSwitchController.TIME_SWITCH_REQUESTPATH)
public class TimeSwitchController
{
	private static final Logger LOG = Logger.getLogger(TimeSwitchController.class);
	public static final String TIME_SWITCH_REQUESTPATH = "/session/time";

	@Resource
	private TimeService timeService;

	@Resource(name = "cartService")
	private CartService cartService;

	@Resource(name = "modelService")
	private ModelService modelService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void setCurrentTime(@RequestParam("currentTime") final long currentTimeMillis,
			@RequestParam("timezoneOffset") final int timezoneOffset)
	{
		LOG.info("*** tz offset: " + timezoneOffset);
		final TimeZone timeZone = TimeZone.getDefault();

		int offset = TimeZone.getDefault().getRawOffset();

		if (timeZone.inDaylightTime(new Date()))
		{

			offset += timeZone.getDSTSavings();
		}

		final Date date = new Date(currentTimeMillis - timezoneOffset * 60 * 1000 - offset);
		shiftTimeTo(date);
	}

	protected void shiftTimeTo(Date date)
	{
		timeService.setCurrentTime(date);
		final String dateAsString = getDateAsString(date, null);
		final String tzName = TimeZone.getDefault().getDisplayName();
		LOG.info("*** Shifting server time date to: " + dateAsString + " " + tzName);

		final CartModel cart = cartService.getSessionCart();
		cart.setContractStartDate(date);
		modelService.save(cart);
	}

	@RequestMapping(value = "/clear", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void reset()
	{
		timeService.resetTimeOffset();
		final CartModel cart = cartService.getSessionCart();
		cart.setContractStartDate(null);
		modelService.save(cart);
	}

	private String getDateAsString(Date date, TimeZone tz)
	{
		final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if (tz != null)
		{
			dateSdf.setTimeZone(tz);
		}
		String formatedDate = dateSdf.format(date);

		String result = formatedDate.substring(0, 12).concat("00");

		return result;
	}
}
