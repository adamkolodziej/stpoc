package com.hybris.showcase.reports;

import de.hybris.platform.converters.impl.AbstractConverter;

import com.hybris.showcase.data.CurrentExpensesData;


/**
 * Created by i323354 on 07/11/15.
 */
public class ExpenseExportDataConverter extends AbstractConverter<CurrentExpensesData, ExpenseExportData>
{

	public ExpenseExportDataConverter()
	{
		super.setTargetClass(ExpenseExportData.class);
	}

	@Override
	public void populate(final CurrentExpensesData source, final ExpenseExportData target)
	{
		if (source.getAmount() != null)
		{
			target.setCurrencyIso(source.getAmount().getCurrencyIso() + " ");
			target.setAmount(source.getAmount().getValue());
		}

		target.setType(source.getType());
		target.setCode(source.getCode());
		target.setExpenseDate(source.getExpenseDate());
		target.setName(source.getName());
	}
}
