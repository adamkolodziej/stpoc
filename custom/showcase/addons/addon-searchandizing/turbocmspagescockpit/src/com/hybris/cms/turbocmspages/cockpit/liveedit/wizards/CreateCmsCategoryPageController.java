package com.hybris.cms.turbocmspages.cockpit.liveedit.wizards;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.restrictions.CMSCategoryRestrictionModel;
import de.hybris.platform.cmscockpit.wizard.controller.CmsPageController;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.services.values.ObjectValueContainer.ObjectValueHolder;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.wizards.Wizard;
import de.hybris.platform.cockpit.wizards.WizardPage;

import java.util.Collections;


public class CreateCmsCategoryPageController extends CmsPageController
{

	@Override
	public void done(final Wizard wizard, final WizardPage page)
	{

		// Create the CMS Category Restriction
		final CmsCategoryPageWizard categoryWizard = (CmsCategoryPageWizard) wizard;
		final PropertyDescriptor catalogVersionPd = UISessionUtils.getCurrentSession().getTypeService()
				.getPropertyDescriptor(CategoryPageModel._TYPECODE + "." + CategoryPageModel.CATALOGVERSION);
		final PropertyDescriptor uidPd = UISessionUtils.getCurrentSession().getTypeService()
				.getPropertyDescriptor(CategoryPageModel._TYPECODE + "." + CategoryPageModel.UID);
		final ObjectValueHolder catalogVersionOVH = categoryWizard.getObjectValueContainer().getValue(catalogVersionPd, null);
		final CatalogVersionModel currentCatalogVersion = (CatalogVersionModel) catalogVersionOVH.getLocalValue();

		final CMSCategoryRestrictionModel categoryRestriction = UISessionUtils.getCurrentSession().getModelService()
				.create(CMSCategoryRestrictionModel.class);
		UISessionUtils.getCurrentSession().getModelService().initDefaults(categoryRestriction);
		categoryRestriction.setCatalogVersion(currentCatalogVersion);
		categoryRestriction.setCategories(Collections.singleton(categoryWizard.getCategory()));
		categoryRestriction.setUid(categoryWizard.getCategory().getCode() + "-"
				+ categoryWizard.getObjectValueContainer().getValue(uidPd, null).getLocalValue());
		categoryRestriction.setName(categoryWizard.getCategory().getName() + " Category Restriction");
		UISessionUtils.getCurrentSession().getModelService().save(categoryRestriction);



		final PropertyDescriptor restrictionPd = UISessionUtils.getCurrentSession().getTypeService()
				.getPropertyDescriptor(CategoryPageModel._TYPECODE + "." + CategoryPageModel.RESTRICTIONS);
		final ObjectValueHolder restrictionValueHolder = categoryWizard.getObjectValueContainer().getValue(restrictionPd, null);
		restrictionValueHolder.setLocalValue(Collections.singletonList(categoryRestriction));
		restrictionValueHolder.stored();
		restrictionValueHolder.setModified(true);

		final PropertyDescriptor defaultPd = UISessionUtils.getCurrentSession().getTypeService()
				.getPropertyDescriptor(CategoryPageModel._TYPECODE + "." + CategoryPageModel.DEFAULTPAGE);
		final ObjectValueHolder defaultValueHolder = categoryWizard.getObjectValueContainer().getValue(defaultPd, null);
		defaultValueHolder.setLocalValue(Boolean.FALSE);
		defaultValueHolder.stored();
		defaultValueHolder.setModified(true);

		super.done(wizard, page);
	}

}
