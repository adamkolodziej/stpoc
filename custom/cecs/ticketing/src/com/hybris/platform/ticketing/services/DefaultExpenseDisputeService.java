package com.hybris.platform.ticketing.services;

import com.hybris.platform.ticketing.daos.ExpenseDisputeDao;
import com.hybris.platform.ticketing.model.ExpenseDisputeModel;
import com.hybris.showcase.model.ExpenseModel;

import de.hybris.platform.servicelayer.model.ModelService;

import org.springframework.beans.factory.annotation.Required;


/**
 * Created by I323354
 */
public class DefaultExpenseDisputeService implements ExpenseDisputeService
{
	private ExpenseDisputeDao expenseDisputeDao;
	private ModelService modelService;

	@Override
	public void createDispute(ExpenseDisputeModel dispute)
	{
		expenseDisputeDao.saveOrUpdate(dispute);
	}
	

    public ExpenseDisputeDao getExpenseDisputeDao() {
        return expenseDisputeDao;
    }

    @Required
    public void setExpenseDisputeDao(ExpenseDisputeDao expenseDisputeDao) {
        this.expenseDisputeDao = expenseDisputeDao;
    }

	@Override
	public ExpenseDisputeModel findExpenseDisputeByTicket(String ticketId) {
		return expenseDisputeDao.findExpenseDisputeByTicket(ticketId);
	}

	@Override
	public void acceptExpenseDispute(ExpenseDisputeModel dispute) {
		ExpenseModel expense = dispute.getExpense();
		expense.setAmount(new Double(0L));
		getModelService().save(expense);
	}


	/**
	 * @return the modelService
	 */
	public ModelService getModelService() {
		return modelService;
	}


	/**
	 * @param modelService the modelService to set
	 */
	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
}
