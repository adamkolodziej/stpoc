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
package com.hybris.cms.turbocmspages.cockpit.liveedit.wizards.page;

import de.hybris.platform.cms2.model.CMSPageTypeModel;
import de.hybris.platform.cms2.model.pages.PageTemplateModel;
import de.hybris.platform.cms2.model.preview.PreviewDataModel;
import de.hybris.platform.cmscockpit.constants.CmsImgUrls;
import de.hybris.platform.cmscockpit.services.config.ContentElementConfiguration;
import de.hybris.platform.cmscockpit.services.config.ContentElementListConfiguration;
import de.hybris.platform.cmscockpit.wizard.page.ReferencePage;
import de.hybris.platform.cockpit.model.meta.BaseType;
import de.hybris.platform.cockpit.model.meta.ObjectTemplate;
import de.hybris.platform.cockpit.model.meta.ObjectType;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.util.UITools;
import de.hybris.platform.cockpit.wizards.Wizard;
import de.hybris.platform.cockpit.wizards.WizardPage;
import de.hybris.platform.core.model.type.ComposedTypeModel;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.zkoss.spring.SpringUtil;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Vbox;

import com.hybris.cms.turbocmspages.cockpit.liveedit.dao.TurboReferencePageDao;
import com.hybris.cms.turbocmspages.cockpit.liveedit.wizards.page.filters.ApplicablePageTemplateFilter;


/**
 * Quite simply a better version of the CMS Wizard Reference Page supporting the definition of Item Templates supporting
 * an image and a description as well as the ability to filter reference pages in the wizard.
 * 
 * @author rmcotton
 * 
 */
public class TurboReferencePage extends ReferencePage
{

	protected static final String SCLASS_ELEMENT_DESC = "contentElementDescription";
	private final List<ApplicablePageTemplateFilter> filters;
	private final PreviewDataModel previewData;

	private TurboReferencePageDao turboReferencePageDao;

	/**
	 * @param pageTitle
	 * @param wizard
	 */
	public TurboReferencePage(final String pageTitle, final Wizard wizard, final List<ApplicablePageTemplateFilter> filters,
			final PreviewDataModel previewData)
	{
		super(pageTitle, wizard);
		this.filters = filters;
		this.previewData = previewData;

	}

	@Override
	public List<TypedObject> getReferences()
	{
		final String currentTypeCode = getWizard().getCurrentType().getCode();
		final ComposedTypeModel type = getTypeService().getComposedTypeForCode(currentTypeCode);

		if (type instanceof CMSPageTypeModel)
		{
			final List<PageTemplateModel> templates = new LinkedList<PageTemplateModel>(getCmsAdminPageService()
					.getAllRestrictedPageTemplates(true, (CMSPageTypeModel) type));
			for (final ApplicablePageTemplateFilter filter : filters)
			{
				filter.filter(previewData, templates);
			}

			return UISessionUtils.getCurrentSession().getTypeService().wrapItems(templates);
		}
		return Collections.EMPTY_LIST;
	}

	@Override
	public Component createRepresentationItself()
	{
		pageContent.getChildren().clear();

		final Div firstStep = new Div();
		firstStep.setParent(pageContent);


		final Listbox listbox = new Listbox();
		listbox.setSclass(TYPE_SELECTOR_CMSWIZARD_PAGE_SCLASS);
		listbox.setWidth("100%");
		listbox.setParent(firstStep);
		listbox.setModel(new SimpleListModel(getReferences()));
		listbox.setItemRenderer(new ListitemRenderer()
		{

			@Override
			public void render(final Listitem item, final Object data) throws Exception
			{

				if (data.equals(chosenReference))
				{
					item.setSelected(true);
				}

				final Listcell cell = new Listcell();
				cell.setSclass(TYPE_SELECTOR_CMSWIZARD_ROW_SCLASS);
				cell.setParent(item);
				final TypedObject processedObject = (TypedObject) data;
				item.setValue(processedObject);
				// YTODO Auto-generated method stub
				final Div itemDiv = new Div();
				itemDiv.setParent(cell);
				itemDiv.setHeight("100%");
				itemDiv.setWidth("100%");


				final Hbox hbox = new Hbox();
				hbox.setParent(itemDiv);
				hbox.setWidth("100%");
				hbox.setWidths("20px,");
				hbox.setSclass(SCLASS_ELEMENT_BOX);

				final Image img = new Image();
				img.setSclass(SCLASS_ELEMENT_IMAGE);
				final ObjectTemplate2ContentElementConfiguration ot2contentElementConf = getElementConfiguration(processedObject,
						getContentElementConfiguration().getContentElements());
				final ContentElementConfiguration contentElementConf = ot2contentElementConf != null ? ot2contentElementConf
						.getConfiguration() : null;
				if (contentElementConf != null && contentElementConf.getImage() != null)
				{
					img.setSrc(contentElementConf.getImage());

				}
				else
				{
					img.setSrc(CmsImgUrls.DEFAULT_ELEMENT_IMAGE);
				}
				hbox.appendChild(img);

				final Vbox textVbox = new Vbox();
				final Div templateTitleDiv = new Div();
				templateTitleDiv.setSclass(SCLASS_ELEMENT_NAME);
				Label templateTitle = null;
				if (contentElementConf != null && StringUtils.isNotBlank(Labels.getLabel(contentElementConf.getName())))
				{
					templateTitle = new Label(Labels.getLabel(contentElementConf.getName()));
				}
				else if (ot2contentElementConf != null && ot2contentElementConf.getTemplate() != null
						&& StringUtils.isNotBlank(ot2contentElementConf.getTemplate().getName()))
				{
					templateTitle = new Label(ot2contentElementConf.getTemplate().getName());
				}
				else
				{
					templateTitle = new Label(UISessionUtils.getCurrentSession().getLabelService()
							.getObjectTextLabelForTypedObject(processedObject));
				}

				UITools.applyTestID(templateTitle,
						COCKPIT_ID_CREATE_PAGE_TEMPLATES_PREFIX + "_" + StringUtils.deleteWhitespace(templateTitle.getValue())
								+ "_button");

				templateTitleDiv.appendChild(templateTitle);
				textVbox.appendChild(templateTitleDiv);

				Label templateDesc = null;
				if (contentElementConf != null && StringUtils.isNotBlank(Labels.getLabel(contentElementConf.getDescription())))
				{
					templateDesc = new Label(Labels.getLabel(contentElementConf.getDescription()));
					templateDesc.setSclass(SCLASS_ELEMENT_DESC);
					textVbox.appendChild(templateDesc);
				}
				else if (ot2contentElementConf != null && ot2contentElementConf.getTemplate() != null
						&& StringUtils.isNotBlank(ot2contentElementConf.getTemplate().getDescription()))
				{
					templateDesc = new Label(ot2contentElementConf.getTemplate().getDescription());
					templateDesc.setSclass(SCLASS_ELEMENT_DESC);
					textVbox.appendChild(templateDesc);
				}


				hbox.appendChild(textVbox);
				item.addEventListener(Events.ON_CLICK, new EventListener()
				{
					@Override
					public void onEvent(final Event event) throws Exception
					{
						doItemSelected(processedObject);
					}
				});
			}
		});

		return pageContainer;
	}

	private static final class ObjectTemplate2ContentElementConfiguration
	{
		private final ObjectTemplate template;
		private final ContentElementConfiguration configuration;

		public ObjectTemplate2ContentElementConfiguration(final ObjectTemplate template,
				final ContentElementConfiguration configuration)
		{
			this.template = template;
			this.configuration = configuration;
		}

		/**
		 * @return the template
		 */
		public ObjectTemplate getTemplate()
		{
			return template;
		}

		/**
		 * @return the configuration
		 */
		public ContentElementConfiguration getConfiguration()
		{
			return configuration;
		}

	}

	/**
	 * For getting configuration for a specific ObjectTemplate please use
	 * {@link #getContentElementConfiguration(ObjectTemplate)} instead!
	 */
	@Override
	protected ContentElementListConfiguration getContentElementConfiguration()
	{
		final Set<String> codes = getTurboReferencePageDao().findObjectTemplateCodesForCode("contentElement");

		ContentElementListConfiguration mergedConfiguration = null;

		for (final String code : codes)
		{
			final ObjectTemplate objectTemplate = UISessionUtils.getCurrentSession().getTypeService().getObjectTemplate(code);
			final ContentElementListConfiguration current = getContentElementConfiguration(objectTemplate);
			if (mergedConfiguration == null)
			{
				mergedConfiguration = current;
			}
			else
			{
				mergedConfiguration.getContentElements().putAll(current.getContentElements());
			}
		}

		return mergedConfiguration;
	}

	private ObjectTemplate2ContentElementConfiguration getElementConfiguration(final TypedObject object,
			final Map<ObjectType, ContentElementConfiguration> contentElements)
	{
		ObjectTemplate2ContentElementConfiguration ret = null;
		for (final ObjectTemplate template : object.getAssignedTemplates())
		{
			final ContentElementConfiguration cec = contentElements.get(template);
			if (cec != null)
			{
				ret = new ObjectTemplate2ContentElementConfiguration(template, cec);
				break;
			}
		}
		if (ret == null)
		{
			final BaseType type = object.getType();
			final ContentElementConfiguration cec = contentElements.get(type);
			if (cec != null)
			{
				ret = new ObjectTemplate2ContentElementConfiguration(null, cec);
			}
		}
		return ret;
	}

	// COPY-PASTE due to rigorous overuse of private
	private void doItemSelected(final TypedObject processedObject)
	{
		chosenReference = processedObject;
		(getWizard()).getPredefinedValues().put("AbstractPage.masterTemplate", chosenReference);
		final PropertyDescriptor descriptor = UISessionUtils.getCurrentSession().getTypeService()
				.getPropertyDescriptor("AbstractPage.masterTemplate");
		getWizard().setValue(descriptor, chosenReference);
		getWizard().loadAndFilter();

		final WizardPage nextPage = getCurrentController().next(getWizard(), getWizard().getCurrentPage());
		if (nextPage == null)
		{
			getWizard().refreshButtons();
		}
		else
		{
			getWizard().doNext();
		}
	}


	protected TurboReferencePageDao getTurboReferencePageDao()
	{
		if (this.turboReferencePageDao == null)
		{
			this.turboReferencePageDao = (TurboReferencePageDao) SpringUtil.getBean("turboReferencePageDao");
		}
		return this.turboReferencePageDao;
	}
}
