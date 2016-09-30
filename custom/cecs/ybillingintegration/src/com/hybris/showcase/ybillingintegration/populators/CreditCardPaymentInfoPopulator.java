/**
 *
 */
package com.hybris.showcase.ybillingintegration.populators;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import javax.annotation.Nonnull;


/**
 * @author Rafal Zymla
 *
 */
public class CreditCardPaymentInfoPopulator implements Populator<CreditCardPaymentInfoModel, CCPaymentInfoData>
{

	@Override
	public void populate(@Nonnull final CreditCardPaymentInfoModel source, @Nonnull final CCPaymentInfoData target)
			throws ConversionException
	{
		validateParameterNotNullStandardMessage("target", target);
		validateParameterNotNullStandardMessage("source", source);
		target.setYBillingCardId(source.getYBillingCardId());
		target.setYBillingBusinessAgreementId(source.getYBillingBusinessAgreementId());
	}

}
