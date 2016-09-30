/**
 *
 */
package de.hybris.platform.yacceleratorstorefront.forms.validation;

import de.hybris.platform.acceleratorstorefrontcommons.forms.AddressForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.AddressValidator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


/**
 * @author lmachnik
 *
 */
@Component("addressWithHouseNoValidator")
public class AddressWithHouseNoValidator extends AddressValidator
{
	private static final int MAX_FIELD_LENGTH = 255;

	@Override
	protected void validateStandardFields(final AddressForm addressForm, final Errors errors)
	{
		super.validateStandardFields(addressForm, errors);
		validateHouseNoField(addressForm, errors);
	}

	private void validateHouseNoField(final AddressForm addressForm, final Errors errors)
	{
		validateStringField(addressForm.getLine2(), AddressField.LINE2, MAX_FIELD_LENGTH, errors);
		if (errors.getFieldError(AddressField.LINE2.getFieldKey()) == null)
		{
			if (!hasOnlyNumbersHouseNo(addressForm.getLine2()))
			{
				errors.rejectValue(AddressField.LINE2.getFieldKey(), AddressField.LINE2.getErrorKey());
			}
		}
	}

	private boolean hasOnlyNumbersHouseNo(final String houseNo)
	{
		final String regex = "[0-9]+";
		return houseNo.matches(regex);
	}
}
