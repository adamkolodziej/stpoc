package com.hybris.platform.ticketing.services;


import com.hybris.platform.ticketing.model.ExpenseDisputeModel;

/**
 * Created by I323354
 */
public interface ExpenseDisputeService {
    
	public void createDispute(ExpenseDisputeModel dispute);
    
    public ExpenseDisputeModel findExpenseDisputeByTicket(String ticketId);

	public void acceptExpenseDispute(ExpenseDisputeModel dispute);
    
}
