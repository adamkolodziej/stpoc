/**
 *
 */
package com.hybris.platform.ticketing.populators;

import com.hybris.platform.ticketing.data.ExpenseDisputeData;
import com.hybris.platform.ticketing.model.ExpenseDisputeModel;
import com.hybris.showcase.data.CurrentExpensesData;
import com.hybris.showcase.model.ExpenseModel;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.math.BigDecimal;


/**
 * @author Sebastian Weiner
 *
 */
public class CurrentExpenseDisputePopulator implements Populator<ExpenseModel, CurrentExpensesData>
{
	@Override
	public void populate(final ExpenseModel source, final CurrentExpensesData target) throws ConversionException
	{
		if (source.getExpenseDisputes().iterator().hasNext()) {
			target.setDisputeExpenseCode(source.getExpenseDisputes().iterator().next().getCode());
		}
	}
}
