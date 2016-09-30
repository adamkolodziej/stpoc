/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package de.hybris.platform.yacceleratorstorefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.entitlementfacades.CoreEntitlementFacade;
import de.hybris.platform.entitlementfacades.data.EntitlementData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hybris.showcase.emsextras.facades.EntitlementsPaginationFacade;


/**
 * Controller for home page
 */
@Controller
@Scope("tenant")
@RequestMapping("/my-account")
public class AccountEntitlementsPageController extends AbstractSearchPageController
{
	// CMS Pages
	// CECS-89: Migrate my-account entitlements page
	private static final String ENTITLEMENTS_CMS_PAGE = "entitlements";

	private static final Logger LOG = Logger.getLogger(AccountEntitlementsPageController.class);

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

	// CECS-260 entitlements page improvements
	@Resource(name = "entitlementsPaginationFacade")
	private EntitlementsPaginationFacade entitlementsPaginationFacade;

	// CECS-89: Migrate my-account entitlements page
	@Resource(name = "coreEntitlementFacade")
	private CoreEntitlementFacade entitlementFacade;

	// CECS-89: Migrate my-account entitlements page
	@RequestMapping(value = "/entitlements", method = RequestMethod.GET)
	@RequireHardLogIn
	public String entitlements(@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", defaultValue = "startDate", required = false) final String sortCode, final Model model)
			throws CMSItemNotFoundException
	{
		final Collection<EntitlementData> entitlements = entitlementFacade.getUserGrants(getUser().getUid());

		// CECS-260	entitlements page improvements
		// Handle paged search results
		final PageableData pageableData = createPageableData(page, getSearchPageSize(), sortCode, showMode);
		final List<EntitlementData> entitlementsArray = new ArrayList<EntitlementData>(entitlements);
		final SearchPageData<EntitlementData> searchPageData = entitlementsPaginationFacade.getEntitlementSearchPageData(
				entitlementsArray, pageableData, sortCode);
		populateModel(model, searchPageData, showMode);

		storeCmsPageInModel(model, getContentPageForLabelOrId(ENTITLEMENTS_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ENTITLEMENTS_CMS_PAGE));

		// CECS-353 Access and Entitlements bug fixing
		model.addAttribute("grants", searchPageData.getResults());
		model.addAttribute("msgKey", "text.account.entitlements");

		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.entitlements"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		return getViewForPage(model);
	}
}
