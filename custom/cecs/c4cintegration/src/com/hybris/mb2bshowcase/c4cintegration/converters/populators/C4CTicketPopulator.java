package com.hybris.mb2bshowcase.c4cintegration.converters.populators;

import com.hybris.platform.ticketing.constants.LifeCycleStatusCodeEnum;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.ticket.model.CsTicketModel;

import com.hybris.platform.ticketing.data.TicketData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;


/**
 * Created by miroslaw.szot@sap.com on 2015-10-26.
 */
public class C4CTicketPopulator implements Populator<CsTicketModel, TicketData>
{
	@Override
	public void populate(final CsTicketModel source, final TicketData target) throws ConversionException
	{
		target.setC4cAttemptTicketId(source.getC4cAttemptTicketId());

		final String c4cStatus = source.getC4cAttemptTicketStatusCode();
		if(StringUtils.isNotBlank(c4cStatus)) {
			LifeCycleStatusCodeEnum status = LifeCycleStatusCodeEnum.valueOf("E" + c4cStatus);
			target.setC4cAttemptTicketStatusCode(status.getCode());
		}
	}

}
