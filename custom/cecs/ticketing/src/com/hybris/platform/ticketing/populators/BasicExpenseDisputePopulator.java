/**
 *
 */
package com.hybris.platform.ticketing.populators;

import com.hybris.platform.ticketing.data.ExpenseDisputeData;
import com.hybris.platform.ticketing.model.ExpenseDisputeModel;
import com.hybris.showcase.data.CurrentExpensesData;
import com.hybris.showcase.model.ExpenseModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;

public class BasicExpenseDisputePopulator implements Populator<ExpenseDisputeModel, ExpenseDisputeData> {


    @Override
    public void populate(final ExpenseDisputeModel source, final ExpenseDisputeData target) throws ConversionException {

        target.setCode(source.getCode());
        target.setDescription(source.getDescription());

        if (source.getExpense() != null) {
            target.setExpenseCode(source.getExpense().getCode());
        }
    }
}
