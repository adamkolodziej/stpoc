package com.hybris.platform.ticketing.facades.impl;

import com.hybris.platform.ticketing.data.ExpenseDisputeData;
import com.hybris.platform.ticketing.data.TicketData;
import com.hybris.platform.ticketing.facades.ExpenseDisputeFacade;
import com.hybris.platform.ticketing.facades.TicketFacade;
import com.hybris.platform.ticketing.model.ExpenseDisputeModel;
import com.hybris.platform.ticketing.services.ExpenseDisputeService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;

/**
 * Created by I323354
 */
public class DefaultExpenseDisputeFacade implements ExpenseDisputeFacade {

    private ExpenseDisputeService expenseDisputeService;
    private Converter<ExpenseDisputeData, ExpenseDisputeModel> reverseExpenseDisputeConverter;

    @Override
    public ExpenseDisputeData createDispute(ExpenseDisputeData dispute) {
        ExpenseDisputeModel disputeModel = reverseExpenseDisputeConverter.convert(dispute);
        expenseDisputeService.createDispute(disputeModel);
        return dispute;
    }


    public ExpenseDisputeService getExpenseDisputeService() {
        return expenseDisputeService;
    }

    @Required
    public void setExpenseDisputeService(ExpenseDisputeService expenseDisputeService) {
        this.expenseDisputeService = expenseDisputeService;
    }

    public Converter<ExpenseDisputeData, ExpenseDisputeModel> getReverseExpenseDisputeConverter() {
        return reverseExpenseDisputeConverter;
    }

    @Required
    public void setReverseExpenseDisputeConverter(Converter<ExpenseDisputeData, ExpenseDisputeModel> reverseExpenseDisputeConverter) {
        this.reverseExpenseDisputeConverter = reverseExpenseDisputeConverter;
    }

}
