/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.hybris.cms.turbocmspages.cockpit.liveedit.wizards;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.constants.Cms2Constants;
import de.hybris.platform.cms2.model.contents.CMSItemModel;
import de.hybris.platform.cms2.model.preview.PreviewDataModel;
import de.hybris.platform.cms2.servicelayer.services.admin.CMSAdminSiteService;
import de.hybris.platform.cmscockpit.services.GenericRandomNameProducer;
import de.hybris.platform.cmscockpit.wizard.CmsWizard;
import de.hybris.platform.cmscockpit.wizard.controller.CmsReferencePageController;
import de.hybris.platform.cmscockpit.wizard.page.CmsPageSelectorPageFactory;
import de.hybris.platform.cmscockpit.wizard.page.CreatePageMandatoryPage;
import de.hybris.platform.cmscockpit.wizard.page.MandatoryPage;
import de.hybris.platform.cmscockpit.wizard.page.ReferencePage;
import de.hybris.platform.cockpit.model.meta.ObjectTemplate;
import de.hybris.platform.cockpit.model.meta.ObjectType;
import de.hybris.platform.cockpit.services.config.UIConfigurationService;
import de.hybris.platform.cockpit.services.config.WizardConfiguration;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.wizards.Wizard;
import de.hybris.platform.cockpit.wizards.WizardPage;
import de.hybris.platform.cockpit.wizards.impl.DefaultPageController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;
import org.zkoss.zk.ui.Component;

import com.hybris.cms.turbocmspages.cockpit.liveedit.wizards.page.TurboReferencePage;
import com.hybris.cms.turbocmspages.cockpit.liveedit.wizards.page.filters.ApplicablePageTemplateFilter;
import com.hybris.cms.turbocmspages.cockpit.liveedit.wizards.page.filters.impl.UiExperienceApplicablePageTemplateFilter;


/**
 * @author Richard Cotton
 */
public class DefaultLiveEditCategoryLandingPageWizardInvoker implements LiveEditCategoryLandingPageWizardInvoker
{
	protected static final String WIZARD_CONFIG = "wizardConfig";
	protected static final String DEFAULT_WIZARD_FRAME = "/cockpit/wizards/defaultWizardFrame.zul";
	protected static final String ABSTRACTPAGE_UID_PREFIX = "page";

	@Resource(name = "uiConfigurationService")
	protected UIConfigurationService uiConfigurationService;

	@Resource(name = "cmsAdminSiteService")
	protected CMSAdminSiteService cmsAdminSiteService;

	@Resource(name = "genericRandomNameProducer")
	protected GenericRandomNameProducer genericRandomNameProducer;

	@Resource(name = "cmsPageSelectorPageFactory")
	protected CmsPageSelectorPageFactory cmsPageSelectorPageFactory;

	protected WizardConfiguration getWizardConfiguration()
	{
		final ObjectTemplate objectTemplate = UISessionUtils.getCurrentSession().getTypeService()
				.getObjectTemplate(Cms2Constants.TC.ABSTRACTPAGE);
		return getUiConfigurationService().getComponentConfiguration(objectTemplate, WIZARD_CONFIG, WizardConfiguration.class);
	}

	@Override
	public Wizard start(final Component parent, final CategoryModel category, final PreviewDataModel previewData)
	{
		final WizardConfiguration config = getWizardConfiguration();

		final CmsWizard wizard = newWizard(config, category);

		if (config != null)
		{
			wizard.setShowPrefilledValues(config.isShowPrefilledValues());
		}

		wizard.setPages(getPages(config, wizard, previewData));
		wizard.setParent(parent);
		wizard.initialize(null, getPredefinedValues(category, previewData));
		wizard.setCurrentType(getType(config));
		wizard.show();
		return wizard;
	}

	protected CmsWizard newWizard(final WizardConfiguration config, final CategoryModel category)
	{
		final DefaultPageController defaultPageController = new DefaultPageController();

		final CmsCategoryPageWizard wizard = new CmsCategoryPageWizard(null, null, category);

		wizard.setTitle("wizard.page");
		wizard.setDefaultController(defaultPageController);
		wizard.setComponentURI(DEFAULT_WIZARD_FRAME);
		return wizard;
	}

	protected List<WizardPage> getPages(final WizardConfiguration config, final CmsWizard wizard,
			final PreviewDataModel previewData)
	{
		final MandatoryPage mandatoryPage = new CreatePageMandatoryPage("wizard.cmspage.title", wizard);
		mandatoryPage.setController(new CreateCmsCategoryPageController());
		mandatoryPage.setId("mandatoryPage");

		final ReferencePage reference = new TurboReferencePage("wizard.page.templateReference", wizard, getFilters(), previewData);

		reference.setController(new CmsReferencePageController());
		reference.setId("referenceSelector");

		if (config != null)
		{
			mandatoryPage.setDisplayedAttributes(new ArrayList<String>(config.getQualifiers(true).keySet()));
		}

		final List<WizardPage> pages = new ArrayList<WizardPage>();
		pages.add(reference);
		pages.add(mandatoryPage);
		return pages;
	}

	protected List<ApplicablePageTemplateFilter> getFilters()
	{
		final List<ApplicablePageTemplateFilter> filters = new LinkedList<ApplicablePageTemplateFilter>();
		filters.add(new UiExperienceApplicablePageTemplateFilter());
		return filters;
	}

	protected Map<String, Object> getPredefinedValues(final CategoryModel category, final PreviewDataModel previewData)
	{
		final Map<String, Object> contextValues = new HashMap<String, Object>();
		contextValues.put(CMSItemModel._TYPECODE + "." + CMSItemModel.CATALOGVERSION, getCmsAdminSiteService()
				.getActiveCatalogVersion());
		contextValues.put(CMSItemModel._TYPECODE + "." + CMSItemModel.UID,
				getGenericRandomNameProducer().generateSequence(Cms2Constants.TC.ABSTRACTPAGE, ABSTRACTPAGE_UID_PREFIX));
		contextValues.put(CMSItemModel._TYPECODE + "." + CMSItemModel.NAME, category.getName() + " Page");
		return contextValues;
	}

	protected ObjectType getType(final WizardConfiguration wizardConfiguration)
	{
		return UISessionUtils.getCurrentSession().getTypeService().getObjectType(Cms2Constants.TC.CATEGORYPAGE);
	}


	/**
	 * @return the uiConfigurationService
	 */
	public UIConfigurationService getUiConfigurationService()
	{
		return uiConfigurationService;
	}

	/**
	 * @param uiConfigurationService
	 *           the uiConfigurationService to set
	 */
	@Required
	public void setUiConfigurationService(final UIConfigurationService uiConfigurationService)
	{
		this.uiConfigurationService = uiConfigurationService;
	}

	/**
	 * @return the cmsAdminSiteService
	 */
	public CMSAdminSiteService getCmsAdminSiteService()
	{
		return cmsAdminSiteService;
	}

	/**
	 * @param cmsAdminSiteService
	 *           the cmsAdminSiteService to set
	 */
	@Required
	public void setCmsAdminSiteService(final CMSAdminSiteService cmsAdminSiteService)
	{
		this.cmsAdminSiteService = cmsAdminSiteService;
	}

	/**
	 * @return the genericRandomNameProducer
	 */
	public GenericRandomNameProducer getGenericRandomNameProducer()
	{
		return genericRandomNameProducer;
	}

	/**
	 * @param genericRandomNameProducer
	 *           the genericRandomNameProducer to set
	 */
	@Required
	public void setGenericRandomNameProducer(final GenericRandomNameProducer genericRandomNameProducer)
	{
		this.genericRandomNameProducer = genericRandomNameProducer;
	}

	/**
	 * @return the cmsPageSelectorPageFactory
	 */
	public CmsPageSelectorPageFactory getCmsPageSelectorPageFactory()
	{
		return cmsPageSelectorPageFactory;
	}

	/**
	 * @param cmsPageSelectorPageFactory
	 *           the cmsPageSelectorPageFactory to set
	 */
	@Required
	public void setCmsPageSelectorPageFactory(final CmsPageSelectorPageFactory cmsPageSelectorPageFactory)
	{
		this.cmsPageSelectorPageFactory = cmsPageSelectorPageFactory;
	}

}
