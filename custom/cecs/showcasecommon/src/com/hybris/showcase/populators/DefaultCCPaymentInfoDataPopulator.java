/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.hybris.showcase.populators;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.converters.Populator;

import java.util.Map;


/**
 * Default credit card payment info populator. Modified copy from cissubscription.
 */
public class DefaultCCPaymentInfoDataPopulator implements Populator<Map<String, String>, CCPaymentInfoData>
{
	@Override
	public void populate(final Map<String, String> source, final CCPaymentInfoData target) throws IllegalArgumentException
	{
		validateParameterNotNullStandardMessage("target", source);
		validateParameterNotNullStandardMessage("source", target);

		target.setAccountHolderName(source.get("nameOnCard"));
		target.setCardNumber(source.get("cardNumber"));

		populateCardType(source, target);

		target.setExpiryMonth(source.get("expiryMonth"));
		target.setExpiryYear(source.get("expiryYear"));


		final AddressData billingAddress = new AddressData();

		populateBillingAddress(billingAddress, source);

		target.setBillingAddress(billingAddress);
	}

	private void populateCardType(final Map<String, String> source, final CCPaymentInfoData target)
	{
		switch (source.get("cardType"))
		{
			case "Visa":
				target.setCardType("visa");
				break;
			case "American Express":
				target.setCardType("amex");
				break;
			case "MasterCard":
				target.setCardType("master");
				break;
			default:
				target.setCardType(source.get("cardType"));
				break;
		}

	}

	protected void populateBillingAddress(final AddressData billingAddress, final Map<String, String> source)
	{
		billingAddress.setFirstName(source.get("billingAddress_firstName"));
		billingAddress.setLastName(source.get("billingAddress_lastName"));
		billingAddress.setTitleCode(source.get("billingAddress_titleCode"));
		billingAddress.setLine1(source.get("billingAddress_line1"));
		billingAddress.setLine2(source.get("billingAddress_line2"));
		final CountryData country = new CountryData();
		country.setIsocode(source.get("billingAddress_countryIso"));
		billingAddress.setCountry(country);
		billingAddress.setPostalCode(source.get("billingAddress_postcode"));
		billingAddress.setTown(source.get("billingAddress_townCity"));
	}

}
