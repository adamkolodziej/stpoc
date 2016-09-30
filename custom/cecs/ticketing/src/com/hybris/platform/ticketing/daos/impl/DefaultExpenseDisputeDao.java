package com.hybris.platform.ticketing.daos.impl;

import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import com.hybris.platform.ticketing.daos.ExpenseDisputeDao;
import com.hybris.platform.ticketing.model.ExpenseDisputeModel;
import com.mysql.jdbc.StringUtils;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;


/**
 * Created by I323354
 */
public class DefaultExpenseDisputeDao implements ExpenseDisputeDao
{
	private ModelService modelService;
	private KeyGenerator expenseDisputeCodeGenerator;
	private FlexibleSearchService flexibleSearchService;
	
	@Override
	public void saveOrUpdate(ExpenseDisputeModel disputeModel)
	{
		if( disputeModel.getCode() == null ) {
			if(disputeModel.getCsTicket() != null && disputeModel.getCsTicket().getTicketID() != null) {
				disputeModel.setCode(disputeModel.getCsTicket().getTicketID());
			}
			else {
				disputeModel.setCode(expenseDisputeCodeGenerator.generate().toString());
			}
		}
		modelService.save(disputeModel);
	}

	@Required
	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	public ModelService getModelService() {
		return modelService;
	}

	public KeyGenerator getExpenseDisputeCodeGenerator() {
		return expenseDisputeCodeGenerator;
	}

	@Required
	public void setExpenseDisputeCodeGenerator(KeyGenerator expenseDisputeCodeGenerator) {
		this.expenseDisputeCodeGenerator = expenseDisputeCodeGenerator;
	}

	@Override
	public ExpenseDisputeModel findExpenseDisputeByTicket(String ticketId) {
		validateParameterNotNull(ticketId, "Ticket ID must not be null");
		
		StringBuilder queryString = new StringBuilder(" SELECT {ed:pk} FROM { ExpenseDispute AS ed ");
		queryString.append(" JOIN CsTicket AS t ON {ed:csTicket} = {t:pk} ")
				   .append(" JOIN Expense AS e ON {ed:expense} = {e:pk} }")
				   .append(" WHERE {t:ticketId} = ?ticketId ");
		
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("ticketId", ticketId);
		
		FlexibleSearchQuery flexQ = new FlexibleSearchQuery(queryString.toString(), queryParams);
		
		return getFlexibleSearchService().searchUnique(flexQ);
		
	}
	
	protected FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
