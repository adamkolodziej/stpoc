package com.hybris.showcase.services.impl;

import de.hybris.platform.commerceservices.address.AddressErrorCode;
import de.hybris.platform.commerceservices.address.AddressFieldType;
import de.hybris.platform.commerceservices.address.AddressVerificationDecision;
import de.hybris.platform.commerceservices.address.data.AddressFieldErrorData;
import de.hybris.platform.commerceservices.address.data.AddressVerificationResultData;
import de.hybris.platform.commerceservices.address.impl.MockAddressVerificationService;
import de.hybris.platform.core.model.user.AddressModel;

import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * @author Sebastian Weiner on 2016-01-25.
 */
public class DefaultAdditionalFieldsVerificationService extends MockAddressVerificationService
{

	public static final String ALPHA = "ALPHA";
	public static final String NUM = "NUM";

	public static final String ZIP_CODE = "zipcode";

	public static final String MISSING = "missing";
	public static final String INVALID = "invalid";


	@Override
	protected void validateAddressFields(
			final AddressVerificationResultData<AddressVerificationDecision, AddressFieldErrorData<AddressFieldType, AddressErrorCode>> result,
			final AddressModel address)
	{

		super.validateAddressFields(result, address);

		final List<AddressFieldErrorData<AddressFieldType, AddressErrorCode>> errorList = result.getFieldErrors();

		final String houseNo = address.getLine2();
		if (StringUtils.isEmpty(houseNo))
		{
			addErrorToVerificationResult(ADDRESS_LINE_2, MISSING, errorList);
		}
		else if (!isHouseNoValid(houseNo))
		{
			addErrorToVerificationResult(ADDRESS_LINE_2, INVALID, errorList);
		}

		final String postalcode = address.getPostalcode();
		if (StringUtils.isEmpty(address.getPostalcode()))
		{
			addErrorToVerificationResult(ZIP_CODE, MISSING, errorList);
		}
		else
		{
			final String isocode = address.getCountry().getIsocode();
			boolean isValidPostalCode;
			switch (isocode)
			{
				case "CA":
					isValidPostalCode = isPostalCodeValid(postalcode, 6, ALPHA);
					break;
				case "CN":
					isValidPostalCode = isPostalCodeValid(postalcode, 6, NUM);
					break;
				case "JP":
					isValidPostalCode = isPostalCodeValid(postalcode, 7, NUM);
					break;
				case "GB":
					isValidPostalCode = isPostalCodeValid(postalcode, 7, ALPHA);
					break;
				default: // DE and US
					isValidPostalCode = isPostalCodeValid(postalcode, 5, NUM);
					break;
			}
			if (!isValidPostalCode)
			{
				addErrorToVerificationResult(ZIP_CODE, INVALID, errorList);
			}
		}

		result.setFieldErrors(errorList);
	}

	private boolean isPostalCodeValid(final String isocode, final int charNum, final String valueType)
	{
		String regexp = null;

		if (valueType.equals(NUM))
		{
			regexp = "\\d{" + charNum + "}";
		}
		else if (valueType.equals(ALPHA))
		{
			regexp = "\\w{" + charNum + "}";
		}

		return isocode.matches(regexp);
	}

	private boolean isHouseNoValid(final String houseNo)
	{
		final String regex = "[0-9]+";
		return houseNo.matches(regex);
	}

}
