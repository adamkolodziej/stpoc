package com.hybris.showcase.guidedselling.controllers.validation;

import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Calendar;


/**
 * Created by mgolubovic on 15.5.2015..
 */
@Component("ccPaymentInfoDataValidator")
public class CCPaymentInfoDataValidator implements Validator
{
	@Override
	public boolean supports(final Class<?> aClass)
	{
		return CCPaymentInfoData.class.equals(aClass);
	}

    @Override
    public void validate(final Object object, final Errors errors)
    {
        final CCPaymentInfoData ccPaymentInfoData = (CCPaymentInfoData) object;

        if(StringUtils.isBlank(ccPaymentInfoData.getCardNumber()))
        {
            errors.rejectValue("cardNumber", "cardNumber.required", "Card number must not be empty");
        }
        if(StringUtils.isBlank(ccPaymentInfoData.getAccountHolderName()))
        {
            errors.rejectValue("accountHolderName", "accountHolderName.required", "Account holder name must not be empty");
        }
        if(StringUtils.isBlank(ccPaymentInfoData.getCardType()))
        {
            errors.rejectValue("cardType", "cardType.required", "Card type must not be empty");
        }
        if(StringUtils.isBlank(ccPaymentInfoData.getExpiryMonth()))
        {
            errors.rejectValue("expiryMonth", "expiryMonth.required", "Expiry month must not be empty");
        }
        if(StringUtils.isBlank(ccPaymentInfoData.getExpiryYear()))
        {
            errors.rejectValue("expiryYear", "expiryYear.required", "Expiry year must not be empty");
        }
        if(StringUtils.isBlank(ccPaymentInfoData.getIssueNumber()))
        {
            errors.rejectValue("issueNumber", "issueNumber.required", "Issue number must not be empty");
        }

        final Calendar start = parseDate(ccPaymentInfoData.getStartMonth(), ccPaymentInfoData.getStartYear());
        final Calendar expiration = parseDate(ccPaymentInfoData.getExpiryMonth(), ccPaymentInfoData.getExpiryYear());

        if (start != null && expiration != null && start.after(expiration))
        {
            errors.rejectValue("startMonth", "payment.startDate.invalid");
        }
    }

    protected Calendar parseDate(final String month, final String year)
    {
        if (StringUtils.isNotBlank(month) && StringUtils.isNotBlank(year))
        {
            final Integer yearInt = getIntegerForString(year);
            final Integer monthInt = getIntegerForString(month);

            if (yearInt != null && monthInt != null)
            {
                final Calendar date = getCalendarResetTime();
                date.set(Calendar.YEAR, yearInt.intValue());
                date.set(Calendar.MONTH, monthInt.intValue() - 1);
                date.set(Calendar.DAY_OF_MONTH, 1);
                return date;
            }
        }
        return null;
    }

    protected Calendar getCalendarResetTime()
    {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    protected Integer getIntegerForString(final String value)
    {
        if (value != null && !value.isEmpty())
        {
            try
            {
                return Integer.valueOf(value);
            }
            catch (final Exception ignore)
            {
                // Ignore
            }
        }

        return null;
    }

}
