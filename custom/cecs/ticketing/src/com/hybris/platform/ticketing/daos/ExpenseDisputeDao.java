package com.hybris.platform.ticketing.daos;


import com.hybris.platform.ticketing.model.ExpenseDisputeModel;

/**
 * Created by I323354
 */
public interface ExpenseDisputeDao {
    
	public void saveOrUpdate(ExpenseDisputeModel disputeModel);

	public ExpenseDisputeModel findExpenseDisputeByTicket(String ticketId);
}
