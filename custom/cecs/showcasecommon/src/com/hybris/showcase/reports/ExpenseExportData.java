package com.hybris.showcase.reports;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by i323354 on 03/11/15.
 */
public class ExpenseExportData {

    BigDecimal amount;
    String name;
    String code;
    String currencyIso;
    Date expenseDate;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrencyIso() {
        return currencyIso;
    }

    public void setCurrencyIso(String currencyIso) {
        this.currencyIso = currencyIso;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
