/**
 *
 */
package com.hybris.showcase.renderers.cms;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.servicelayer.time.TimeService;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.jsp.PageContext;

import com.hybris.showcase.model.TimeSwitcherComponentModel;


/**
 * @author i324339
 *
 */
public class TimeSwitcherComponentRenderer extends DefaultAddOnCMSComponentRenderer<TimeSwitcherComponentModel>
{
	@Resource
	private TimeService timeService;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final TimeSwitcherComponentModel component)
	{
		final Map<String, Object> variables = new HashMap<>();

		variables.put("currentDate", new Long(timeService.getCurrentTime().getTime() + TimeZone.getDefault().getRawOffset()));

		return variables;
	}

	/**
	 * @return the timeService
	 */
	public TimeService getTimeService()
	{
		return timeService;
	}

	/**
	 * @param timeService
	 *           the timeService to set
	 */
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}

}
