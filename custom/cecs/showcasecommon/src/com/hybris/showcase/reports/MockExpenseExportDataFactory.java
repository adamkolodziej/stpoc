package com.hybris.showcase.reports;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Factory bean used in Jasper Studio to build reports
 *
 * NOTE: This class is not used in hybris but is helpful when previewing reports as it helps to see some data
 *
 * Created by i323354 on 03/11/15.
 */
public class MockExpenseExportDataFactory {

    public static Collection<ExpenseExportData> load() {
        ArrayList<ExpenseExportData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(sampleExpense(BigDecimal.valueOf(Math.random() % 10), "code" + i, "A sample expense " + i, "regular"));
        }
        return list;
    }

    public static ExpenseExportData sampleExpense(BigDecimal amount, String code, String name, String type) {

        ExpenseExportData exp = new ExpenseExportData();

        exp.setAmount(amount);
        exp.setCode(code);
        exp.setName(name);
        exp.setCurrencyIso("EUR");
        exp.setExpenseDate(new Date());
        exp.setType("testing from eclipse");

        return exp;
    }
}
