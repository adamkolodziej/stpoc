/**
 *
 */
package com.hybris.showcase.emsextras.strategy.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.subscriptionfacades.data.UsageChargeEntryData;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.dao.ContractDao;
import com.hybris.showcase.emsextras.strategy.SubmitUsageStrategy;
import com.hybris.showcase.model.ContractModel;
import com.hybris.showcase.model.ExpenseModel;
import com.hybris.showcase.services.ExpenseService;


/**
 * @author I307113
 *
 */
public class DefaultSubmitUsageStrategy implements SubmitUsageStrategy
{
	private static final Logger LOG = Logger.getLogger(DefaultSubmitUsageStrategy.class);
	private ExpenseService expenseService;
	private UserService userService;
	private CommonI18NService commonI18NService;
	private ProductService productService;
	private ContractDao contractDao;
	private TimeService timeService;

	@Override
	public Boolean submitUsage(final String productCode, final String entitlementType, final int quantity,
			final UsageChargeEntryData charge)
	{
		final CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();
		final ContractModel contract = contractDao.getLatestContractForUser(customer);

		double amount = 0.0;
		String currencyIso;
		if (charge != null)
		{
			amount = charge.getPrice().getValue().doubleValue();
			currencyIso = charge.getPrice().getCurrencyIso();
		}
		else
		{
			currencyIso = commonI18NService.getCurrentCurrency().getIsocode();
		}

		final ExpenseModel expense = expenseService.persistExpense(customer, contract, productCode, timeService.getCurrentTime(),
				entitlementType, amount, currencyIso, productService.getProductForCode(productCode));

		LOG.debug("Expense registered: " + expense.getCode() + expense.getAmount() + " " + expense.getCurrencyIso());

		return true;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public ExpenseService getExpenseService()
	{
		return expenseService;
	}

	@Required
	public void setExpenseService(final ExpenseService expenseService)
	{
		this.expenseService = expenseService;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @return the productService
	 */
	public ProductService getProductService()
	{
		return productService;
	}

	/**
	 * @param productService
	 *           the productService to set
	 */
	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	/**
	 * @return the contractDao
	 */
	public ContractDao getContractDao()
	{
		return contractDao;
	}

	@Required
	public void setContractDao(final ContractDao contractDao)
	{
		this.contractDao = contractDao;
	}

	/**
	 * @return the timeService
	 */
	public TimeService getTimeService()
	{
		return timeService;
	}

	@Required
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}




}

