package com.hybris.showcase.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.hybris.showcase.dao.ExpenseDao;
import com.hybris.showcase.model.ContractModel;
import com.hybris.showcase.model.ExpenseModel;
import com.hybris.showcase.model.InvoiceModel;


/**
 *
 * @author marius.bogdan.ionescu@sap.com
 *
 */
public class DefaultExpenseDao extends AbstractItemDao implements ExpenseDao
{

	public static final String EXPENSES_USER_QUERY = "" + //
			" select {e.pk} from {Expense as e} " + //
			"  where {e.customer} = ?user";

	public static final String EXPENSES_MONTH_QUERY = "" + //
			" select {e.pk} from {Expense as e} " + //
			"  where {e.customer} = ?user " + //
			" and MONTH({e.expenseDate}) = ?month and YEAR({e.expenseDate}) =?year";

	public static final String EXPENSES_USER_CONTRACT_MONTH_QUERY = "" + //
			" select {e.pk} from {Expense as e} " + //
			"  where {e.customer} = ?user " + //
			" and {e.contract} = ?contract" + //
			" and MONTH({e.expenseDate}) = ?month and YEAR({e.expenseDate}) =?year";

	public static final String EXPENSES_CONTRACT_MONTH_QUERY = "" + //
			" select {e.pk} from {Expense as e} " + //
			"  where {e.contract} = ?contract" + //
			" and MONTH({e.expenseDate}) = ?month and YEAR({e.expenseDate}) =?year";

	public static final String EXPENSES_CONTRACT_MONTH_TYPE_QUERY = "" + //
			" select {e.pk} from {Expense as e} " + //
			"  where {e.contract} = ?contract" + //
			" and {e.type} LIKE ?type" + //
			" and MONTH({e.expenseDate}) = ?month and YEAR({e.expenseDate}) =?year";

	public static final String EXPENSES_FOR_INVOICE_QUERY = "" + //
			" select {e.pk} from {" + ExpenseModel._TYPECODE + " AS e JOIN " + InvoiceModel._TYPECODE
			+ " AS inv ON {e:invoice}={inv.pk}} " + //
			"  where {inv.invoiceNumber} = ?invoiceNumber";

	public static final String EXPENSES_BY_TYPE_CONTRACT_PRODUCT_QUERY = "" + //
			" select {e.pk} from {" + ExpenseModel._TYPECODE + " AS e}" + //
			"  where {e.contract} = ?contract" + //
			" and {e.type} like ?type" + //
			" and {e.sourceProduct} = ?product";

	public static final String EXPENSES_BY_CUSTOMER_PRODUCT_MONTH_QUERY = "" + //
			" select {e.pk} from {Expense as e} " + //
			"  where {e.customer} = ?customer " + //
			" and MONTH({e.expenseDate}) = ?month and YEAR({e.expenseDate}) =?year" + //
			" and {e.sourceProduct} = ?product";




	@Resource
	private PagedFlexibleSearchService pagedFlexibleSearchService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Resource
	private KeyGenerator expenseCodeGenerator;


	@Override
	public ExpenseModel getExpenseForCode(final String code)
	{
		final ExpenseModel example = new ExpenseModel();
		example.setCode(code);
		final List<ExpenseModel> result = getFlexibleSearchService().getModelsByExample(example);
		if (CollectionUtils.isNotEmpty(result))
		{
			return result.iterator().next();
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<ExpenseModel> getExpensesForContract(final ContractModel contract, final Integer month, final Integer year)
	{

		validateParameterNotNull(contract, "Contract must not be null");

		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("contract", contract);
		queryParams.put("month", month);
		queryParams.put("year", year);

		final SearchResult<ExpenseModel> searchResult = getFlexibleSearchService().search(EXPENSES_CONTRACT_MONTH_QUERY,
				queryParams);

		return searchResult.getResult();

	}

	@Override
	public List<ExpenseModel> getExpenses(final String type, final ContractModel contract, final ProductModel product)
	{
		validateParameterNotNull(contract, "Contract must not be null");

		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("contract", contract);
		queryParams.put("product", product);
		queryParams.put("type", type);

		final SearchResult<ExpenseModel> searchResult = getFlexibleSearchService().search(EXPENSES_BY_TYPE_CONTRACT_PRODUCT_QUERY,
				queryParams);

		return searchResult.getResult();
	}

	@Override
	public List<ExpenseModel> getExpensesForContractByType(final ContractModel contract, final Integer month, final Integer year,
			final String type)
	{

		validateParameterNotNull(contract, "Contract must not be null");

		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("contract", contract);
		queryParams.put("month", month);
		queryParams.put("year", year);
		queryParams.put("type", type);

		final SearchResult<ExpenseModel> searchResult = getFlexibleSearchService().search(EXPENSES_CONTRACT_MONTH_TYPE_QUERY,
				queryParams);

		return searchResult.getResult();

	}

	@Override
	public List<ExpenseModel> getExpensesForInvoice(final String invoiceNumber)
	{
		validateParameterNotNull(invoiceNumber, "Invoice number must not be null");

		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("invoiceNumber", invoiceNumber);


		final SearchResult<ExpenseModel> searchResult = getFlexibleSearchService().search(EXPENSES_FOR_INVOICE_QUERY, queryParams);

		return searchResult.getResult();

	}

	@Override
	public List<ExpenseModel> getExpenses(final CustomerModel customer, final ProductModel product, final int month,
			final int year)
	{
		validateParameterNotNull(customer, "Customer must not be null");
		validateParameterNotNull(product, "Product must not be null");

		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("customer", customer);
		queryParams.put("month", month);
		queryParams.put("year", year);
		queryParams.put("product", product);

		final SearchResult<ExpenseModel> searchResult = getFlexibleSearchService().search(EXPENSES_BY_CUSTOMER_PRODUCT_MONTH_QUERY,
				queryParams);

		return searchResult.getResult();

	}


	@Override
	public void removeExpenses(final CustomerModel customer, final ProductModel product, final int month, final int year)
	{
		final List<ExpenseModel> expenses = getExpenses(customer, product, month, year);
		if (CollectionUtils.isNotEmpty(expenses))
		{
			for (final ExpenseModel expense : expenses)
			{
				getModelService().remove(expense);
			}
		}
	}

	@Override
	public SearchPageData<ExpenseModel> getExpensesForUser(final UserModel user, final String orderBy,
			final PageableData pageableData)
	{
		validateParameterNotNull(user, "User must not be null");

		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("user", user);

		final String order = "by" + orderBy;
		final List<SortQueryData> sortQueries = Arrays
				.asList(createSortQueryData(order, EXPENSES_USER_QUERY + " ORDER BY " + orderBy));

		if (orderBy != null)
		{
			return pagedFlexibleSearchService.search(sortQueries, order, queryParams, pageableData);
		}
		else
		{
			return pagedFlexibleSearchService.search(EXPENSES_USER_QUERY, queryParams, pageableData);
		}
	}

	@Override
	public SearchPageData<ExpenseModel> getExpensesForMonth(final UserModel user, final ContractModel contract,
			final Integer month, final Integer year, final String orderBy, final PageableData pageableData)
	{
		validateParameterNotNull(user, "User must not be null");
		validateParameterNotNull(contract, "Contract must not be null");

		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("user", user);
		queryParams.put("month", month);
		queryParams.put("year", year);
		queryParams.put("contract", contract);

		final String order = "by" + orderBy;
		final List<SortQueryData> sortQueries = Arrays
				.asList(createSortQueryData(order, EXPENSES_USER_CONTRACT_MONTH_QUERY + " ORDER BY " + orderBy));

		if (orderBy != null)
		{
			return pagedFlexibleSearchService.search(sortQueries, order, queryParams, pageableData);
		}
		else
		{
			return pagedFlexibleSearchService.search(EXPENSES_USER_CONTRACT_MONTH_QUERY, queryParams, pageableData);
		}
	}

	@Override
	public List<ExpenseModel> getExpensesForMonth(final UserModel user, final Integer month, final Integer year)
	{
		validateParameterNotNull(user, "User must not be null");

		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("user", user);
		queryParams.put("month", month);
		queryParams.put("year", year);

		return flexibleSearchService.<ExpenseModel> search(EXPENSES_MONTH_QUERY, queryParams).getResult();
	}

	protected SortQueryData createSortQueryData(final String sortCode, final String query)
	{
		final SortQueryData result = new SortQueryData();
		result.setSortCode(sortCode);
		result.setQuery(query);
		return result;
	}

	@Override
	public ExpenseModel save(final CustomerModel customer, final ContractModel contract, final String productCode, final Date date,
			final String type, final double amount, final String currencyIso, final ProductModel product)
	{
		final ExpenseModel exp = getModelService().create(ExpenseModel.class);

		exp.setCode(expenseCodeGenerator.generate().toString());
		exp.setCustomer(customer);
		exp.setAmount(Double.valueOf(amount));
		exp.setCurrencyIso(currencyIso);
		exp.setType(type);
		exp.setName(productCode);
		exp.setExpenseDate(date);
		exp.setContract(contract);
		if (product != null)
		{
			exp.setSourceProduct(product);
		}

		getModelService().save(exp);
		return exp;
	}

	/**
	 * @return the pagedFlexibleSearchService
	 */
	public PagedFlexibleSearchService getPagedFlexibleSearchService()
	{
		return pagedFlexibleSearchService;
	}

	/**
	 * @param pagedFlexibleSearchService
	 *           the pagedFlexibleSearchService to set
	 */
	public void setPagedFlexibleSearchService(final PagedFlexibleSearchService pagedFlexibleSearchService)
	{
		this.pagedFlexibleSearchService = pagedFlexibleSearchService;
	}

	/**
	 *
	 */
	@Override
	public ExpenseModel getExpenseForExpenseDispute(final String expenseDisputeCode)
	{
		validateParameterNotNull(expenseDisputeCode, "Expense Dispute Code must not be null");

		final StringBuilder queryString = new StringBuilder(" SELECT {e:pk} FROM { Expense AS e ");
		queryString.append(" JOIN ExpenseDispute AS ed ON {ed:expense} = {e:pk} }")
				.append(" WHERE {ed:code} = ?expenseDisputeCode ");

		final Map<String, String> queryParams = new HashMap<>();
		queryParams.put("expenseDisputeCode", expenseDisputeCode);

		final FlexibleSearchQuery flexQ = new FlexibleSearchQuery(queryString.toString(), queryParams);

		return getFlexibleSearchService().searchUnique(flexQ);

	}



}
