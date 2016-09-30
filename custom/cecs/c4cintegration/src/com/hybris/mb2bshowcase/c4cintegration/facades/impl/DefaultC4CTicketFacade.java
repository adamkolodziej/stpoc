package com.hybris.mb2bshowcase.c4cintegration.facades.impl;

import com.hybris.platform.ticketing.constants.LifeCycleStatusCodeEnum;
import com.hybris.platform.ticketing.data.TicketData;
import com.hybris.platform.ticketing.facades.impl.DummyExternalManagerTicketFacade;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.ticket.model.CsTicketModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hybris.platform.ticket.service.TicketService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.mb2bshowcase.c4cintegration.daos.C4CMappingsDao;
import com.hybris.mb2bshowcase.c4cintegration.services.C4CTicketService;
import com.hybris.platform.ticketing.data.ProductEntitlementData;


/**
 * @author ddyrcz
 */
public class DefaultC4CTicketFacade extends DummyExternalManagerTicketFacade
{
	private String giftCategory = "gifts";
	private C4CTicketService c4cTicketService;
	private TicketService ticketService;
	private ModelService modelService;
	private UserService userService;
	private CategoryService categoryService;
	private C4CMappingsDao c4cMappingsDao;
	private Converter<ProductEntitlementModel, ProductEntitlementData> giftEntitlementConverter;

	// CECS-338	Implement a Mashup for C4C allowing to grant entitlement for specific movie
	@Override
	public void createTicket(TicketData ticket) {

		String masterTicketId = createExternalTicket(ticket.getHeadline(), ticket.getNote());
		String statusCode = getStatusForExternalTicketId(masterTicketId);

		final CsTicketModel ticketModel = ticketService.getTicketForTicketId(ticket.getTicketID());
		ticketModel.setC4cAttemptTicketId(masterTicketId);
		ticketModel.setC4cAttemptTicketStatusCode(statusCode);

		modelService.save(ticketModel);
	}

	@Override
	public TicketData updateTicket(TicketData ticketData) {
		if( StringUtils.isNotBlank(ticketData.getC4cAttemptTicketId()) ) {
			final String c4cTicketStatus = c4cTicketService.getTicketsLifeCycleStatusCode(ticketData.getC4cAttemptTicketId());

			if( StringUtils.isNotBlank(c4cTicketStatus)) {
				ticketData.setC4cAttemptTicketStatusCode(LifeCycleStatusCodeEnum.valueOf("E" + c4cTicketStatus).getCode());
				final CsTicketModel ticketModel = ticketService.getTicketForTicketId(ticketData.getTicketID());
				ticketModel.setC4cAttemptTicketStatusCode(c4cTicketStatus);
				modelService.save(ticketModel);
			}
		}
		return ticketData;
	}

	@Override
	public String createExternalTicket(final String headline, final String note)
	{
		final CustomerModel currentCustomerModel = (CustomerModel) userService.getCurrentUser();

		//CECS-307 separate ID in data model for C4C customer (c4cCustomerId) START
		String c4cCustomerID = currentCustomerModel.getC4cCustomerID();
		if (StringUtils.isBlank(c4cCustomerID))
		{
			c4cCustomerID = currentCustomerModel.getCustomerID();
		}

		return c4cTicketService.createAndSendMasterTicket(c4cCustomerID, headline, note);
		//CECS-307 separate ID in data model for C4C customer (c4cCustomerId) END
	}

	// CECS-338 Implement a Mashup for C4C allowing to grant entitlement for specific movie START
	@Override
	public CustomerModel getCustomerForExternalId(final String c4cCustomerID)
	{
		if (StringUtils.isNotBlank(c4cCustomerID))
		{
			return c4cMappingsDao.findCustomerForC4cId(c4cCustomerID);
		}
		return null;
	}

	@Override
	public CsTicketModel getTicketForExternalId(final String c4cAttemptTicketId)
	{
		if (StringUtils.isNotBlank(c4cAttemptTicketId))
		{
			return c4cMappingsDao.findC4cTicketForC4cTicketId(c4cAttemptTicketId);
		}
		return null;
	}

	@Override
	public String getStatusForExternalTicketId(final String masterTicketId)
	{
		return c4cTicketService.getTicketsLifeCycleStatusCode(masterTicketId);
	}

	@Override
	public Collection<ProductEntitlementData> getProductEntitlementsForGiftCategory()
	{
		final CategoryModel category = getCategoryService().getCategoryForCode(giftCategory);
		final List<ProductModel> products = category.getProducts();

		final Collection<ProductEntitlementData> prodProdEntitlementsDataCollection = new ArrayList<>();

		for (final ProductModel prod : products)
		{
			final Collection<ProductEntitlementData> productDataCollection = new ArrayList();
			for (final ProductEntitlementModel productEntModel : prod.getProductEntitlements())
			{
				productDataCollection.add(getGiftEntitlementConverter().convert(productEntModel));
			}

			prodProdEntitlementsDataCollection.addAll(productDataCollection);
		}

		return prodProdEntitlementsDataCollection;
	}

	public void setGiftCategory(String giftCategory) {
		this.giftCategory = giftCategory;
	}

	public String getGiftCategory() {
		return giftCategory;
	}

	public C4CTicketService getC4cTicketService() {
		return c4cTicketService;
	}

	@Required
	public void setC4cTicketService(C4CTicketService c4cTicketService) {
		this.c4cTicketService = c4cTicketService;
	}

	public TicketService getTicketService() {
		return ticketService;
	}

	@Required
	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	public ModelService getModelService() {
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Required
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	@Required
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public C4CMappingsDao getC4cMappingsDao() {
		return c4cMappingsDao;
	}

	@Required
	public void setC4cMappingsDao(C4CMappingsDao c4cMappingsDao) {
		this.c4cMappingsDao = c4cMappingsDao;
	}

	public Converter<ProductEntitlementModel, ProductEntitlementData> getGiftEntitlementConverter() {
		return giftEntitlementConverter;
	}

	@Required
	public void setGiftEntitlementConverter(Converter<ProductEntitlementModel, ProductEntitlementData> giftEntitlementConverter) {
		this.giftEntitlementConverter = giftEntitlementConverter;
	}
}
