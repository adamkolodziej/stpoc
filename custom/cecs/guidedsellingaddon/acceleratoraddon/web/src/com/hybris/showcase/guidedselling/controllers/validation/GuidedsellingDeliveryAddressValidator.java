package com.hybris.showcase.guidedselling.controllers.validation;

import de.hybris.platform.commercefacades.user.data.AddressData;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by m.golubovic on 23.7.2015..
 */
@Component("guidedsellingDeliveryAddressValidator")
public class GuidedsellingDeliveryAddressValidator implements Validator
{
    public static final String ALPHA = "ALPHA";
    public static final String NUM = "NUM";

    @Override
    public void validate(Object o, Errors errors)
    {
        final AddressData addressData = (AddressData) o;

        if(addressData.getCountry() == null)
        {
            errors.rejectValue("country","addressData.country.required","Country must not be empty.");
        }
        if(StringUtils.isEmpty(addressData.getTitleCode()))
        {
            errors.rejectValue("titleCode","addressData.title.required","Title must not be empty.");
        }
        if(StringUtils.isEmpty(addressData.getFirstName()))
        {
            errors.rejectValue("firstName","addressData.firstName.required","First name must not be empty.");
        }
        if(StringUtils.isEmpty(addressData.getLastName()))
        {
            errors.rejectValue("lastName","addressData.lastName.required","Last name must not be empty.");
        }
        if(StringUtils.isEmpty(addressData.getLine1()))
        {
            errors.rejectValue("line1","addressData.line1.required","Line1 must not be empty.");
        }
        if(StringUtils.isEmpty(addressData.getTown()))
        {
            errors.rejectValue("town","addressData.town.required","Town must not be empty.");
        }
        if(addressData.getRegion() == null)
        {
            errors.rejectValue("region","addressData.region.required","Region must not be empty.");
        }
        if (StringUtils.isEmpty(addressData.getPostalCode()))
        {
            errors.rejectValue("postalCode", "addressData.postalCode.required", "Postal code must not be empty.");
        }
        else {
            String isocode = addressData.getCountry().getIsocode();
            boolean isValidPostalCode;
            switch (isocode) {
                case "CA":
                    isValidPostalCode = isPostalCodeValid(addressData.getPostalCode(), 6, ALPHA);
                    break;
                case "CN":
                    isValidPostalCode = isPostalCodeValid(addressData.getPostalCode(), 6, NUM);
                    break;
                case "JP":
                    isValidPostalCode = isPostalCodeValid(addressData.getPostalCode(), 7, NUM);
                    break;
                case "GB":
                    isValidPostalCode = isPostalCodeValid(addressData.getPostalCode(), 7, ALPHA);
                    break;
                default: // DE and US
                    isValidPostalCode = isPostalCodeValid(addressData.getPostalCode(), 5, NUM);
                    break;
            }
            if(!isValidPostalCode){
                errors.rejectValue("postalCode", "addressData.postalCode.wrong", "Postal code must have the right format.");
            }
        }
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return AddressData.class.equals(aClass);
    }

    private boolean isPostalCodeValid(String isocode, int charNum, String valueType) {
        String regexp = null;

        if(valueType.equals(NUM)){
            regexp = "\\d{"+charNum+"}";
        } else if (valueType.equals(ALPHA)){
            regexp = "\\w{"+charNum+"}";
        }

        return isocode.matches(regexp);
    }
}
