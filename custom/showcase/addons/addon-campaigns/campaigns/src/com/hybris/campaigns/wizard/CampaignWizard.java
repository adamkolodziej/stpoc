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
package com.hybris.campaigns.wizard;

import de.hybris.platform.cockpit.model.meta.ObjectTemplate;
import de.hybris.platform.cockpit.model.meta.ObjectType;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor.Multiplicity;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.config.AvailableValuesProvider;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.services.values.ObjectValueContainer;
import de.hybris.platform.cockpit.services.values.ObjectValueContainer.ObjectValueHolder;
import de.hybris.platform.cockpit.services.values.ObjectValueHandler;
import de.hybris.platform.cockpit.services.values.ObjectValueHandlerRegistry;
import de.hybris.platform.cockpit.services.values.ValueHandlerException;
import de.hybris.platform.cockpit.services.values.ValueHandlerPermissionException;
import de.hybris.platform.cockpit.session.UISession;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.wizards.WizardPage;
import de.hybris.platform.cockpit.wizards.WizardPageController;
import de.hybris.platform.cockpit.wizards.generic.GenericItemWizard;
import de.hybris.platform.cockpit.wizards.impl.DefaultPage;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;

import com.hybris.campaigns.model.PublicationPeriodModel;
import com.hybris.campaigns.wizard.page.ComponentItemPage;


/**
 * SHOW-1549 New Campaign Wizard
 * 
 * @author miroslaw.szot
 */
public class CampaignWizard extends GenericItemWizard
{
	public static final String CLASSPATH_PREFIX = "classpath:";
	private static final Logger LOG = Logger.getLogger(CampaignWizard.class);

	private ObjectValueContainer publicationPeriodObjectValueContainer;
	private TypedObject publicationPeriodItem;
	private Map<String, Object> additionalValues = new HashMap<String, Object>();

	@Override
	protected Component createPageComponent(final Component parent, final WizardPage page, final WizardPageController controller)
	{
		Component ret = null;

		if (StringUtils.isNotBlank(page.getComponentURI()) && page instanceof ComponentItemPage)
		{
			ret = defaultCreatePageComponent(parent, page, controller);
		}
		else
		{
			ret = super.createPageComponent(parent, page, controller);
		}

		return ret;
	}

	protected Component defaultCreatePageComponent(final Component parent, final WizardPage page,
			final WizardPageController controller)
	{
		parent.setVariable("pageBean", page, true);
		parent.setVariable("controllerBean", controller, true);
		parent.setVariable("wizardBean", this, true);

		Component ret;
		String pageCmpURI = page.getComponentURI();
		if (StringUtils.isBlank(pageCmpURI))
		{
			ret = new Div();
			if (page instanceof DefaultPage)
			{
				((DefaultPage) page).renderView(ret);
			}
		}
		else
		{
			if (StringUtils.isNotBlank(getPageRoot()) && pageCmpURI.charAt(0) != '/' && !pageCmpURI.startsWith(CLASSPATH_PREFIX))
			{
				pageCmpURI = getPageRoot() + "/" + pageCmpURI; // NOPMD
			}

			if (pageCmpURI.startsWith(CLASSPATH_PREFIX))
			{
				final String viewURI = pageCmpURI.replaceFirst(CLASSPATH_PREFIX, "");
				final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(viewURI);

				if (inputStream == null)
				{
					LOG.error("Could not open specified uri '" + viewURI + "'");
					ret = new Div();
				}
				else
				{
					try
					{
						ret = Executions.createComponentsDirectly(new InputStreamReader(inputStream), null, parent, null);
					}
					catch (final IOException e)
					{
						LOG.error("Could not create view with URI '" + viewURI + "', reason: ", e);
						ret = new Div();
					}
				}
			}
			else
			{
				ret = Executions.createComponents(pageCmpURI, parent, null);
			}
		}
		ret.applyProperties();
		final List<Component> newones = parent.getChildren();
		new AnnotateDataBinder(newones.toArray(new Component[newones.size()])).loadAll();
		return ret;
	}


	public ObjectValueContainer getPublicationPeriodObjectValueContainer()
	{
		if (publicationPeriodObjectValueContainer == null)
		{
			final ModelService modelService = UISessionUtils.getCurrentSession().getModelService();
			final TypeService typeService = UISessionUtils.getCurrentSession().getTypeService();

			final ItemModel model;
			if (publicationPeriodItem == null)
			{
				model = modelService.create(PublicationPeriodModel._TYPECODE);
				publicationPeriodItem = typeService.wrapItem(model);
			}
			else
			{
				model = (ItemModel) publicationPeriodItem.getObject();
			}

			publicationPeriodObjectValueContainer = getObjectValueContainer(model);
		}
		return publicationPeriodObjectValueContainer;
	}

	public void setPublicationPeriodObjectValueContainer(final ObjectValueContainer publicationPeriodObjectValueContainer)
	{
		this.publicationPeriodObjectValueContainer = publicationPeriodObjectValueContainer;
	}

	public ObjectValueContainer getObjectValueContainer(final ItemModel model)
	{
		final UISession uiSession = UISessionUtils.getCurrentSession();
		final TypeService typeService = uiSession.getTypeService();

		final ObjectTemplate itemType = typeService.getObjectTemplate(model.getItemtype());
		final ObjectValueContainer objectValueContainer = new ObjectValueContainer(itemType, null);

		final ObjectType currentBaseType = itemType.getBaseType();

		final ObjectValueHandlerRegistry valueHandlerRegistry = uiSession.getValueHandlerRegistry();

		final TypedObject artificialTypedObject = typeService.wrapItem(model);

		objectValueContainer.setObject(model);

		for (final ObjectValueHandler valueHandler : valueHandlerRegistry.getValueHandlerChain(currentBaseType))
		{
			try
			{
				valueHandler.loadValues(objectValueContainer, currentBaseType, artificialTypedObject,
						currentBaseType.getPropertyDescriptors(), getLoadLanguages());
			}
			catch (final ValueHandlerPermissionException e)
			{
				//do nothing
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Not sufficient privilages!", e);
				}
			}
			catch (final ValueHandlerException e)
			{
				LOG.error("error loading object values", e);
			}
		}

		uiSession.getNewItemService().setDefaultValues(objectValueContainer,
				uiSession.getTypeService().getObjectTemplate(currentBaseType.getCode()));

		return objectValueContainer;
	}

	@Override
	public ObjectValueContainer getObjectValueContainer()
	{
		if (objectValueContainer == null)
		{
			if (getItem() != null)
			{
				objectValueContainer = getObjectValueContainer((ItemModel) getItem().getObject());
			}
			else
			{
				return super.getObjectValueContainer();
			}
		}
		return objectValueContainer;
	}

	public TypedObject getPublicationPeriodItem()
	{
		return publicationPeriodItem;
	}

	protected void handleAdditionalValues(final Map<String, Object> additionalValues, final String type,
			final ObjectValueContainer valueContainer)
	{
		if (additionalValues == null)
		{
			return;
		}

		final TypeService typeService = UISessionUtils.getCurrentSession().getTypeService();

		for (final Entry<String, Object> entry : additionalValues.entrySet())
		{
			if (entry.getKey().startsWith(type + "."))
			{
				final PropertyDescriptor propertyDescriptor = typeService.getPropertyDescriptor(entry.getKey());

				final ObjectValueHolder holder = valueContainer.getValue(propertyDescriptor, null);
				final Multiplicity multiplicity = propertyDescriptor.getMultiplicity();

				switch (multiplicity)
				{
					case SINGLE:
						holder.setLocalValue(typeService.wrapItem(entry.getValue()));
						break;
					case LIST:
					{
						Collection<TypedObject> values = (Collection<TypedObject>) holder.getCurrentValue();
						if (values == null)
						{
							values = new ArrayList<TypedObject>();
						}
						else
						{
							values = new ArrayList<TypedObject>(values);
						}

						appendValues(entry.getValue(), values);
						holder.setLocalValue(values);
						break;
					}
					case SET:
					{
						Collection<TypedObject> values = (Collection<TypedObject>) holder.getCurrentValue();
						if (values == null)
						{
							values = new HashSet<TypedObject>();
						}
						else
						{
							values = new HashSet<TypedObject>(values);
						}

						appendValues(entry.getValue(), values);
						holder.setLocalValue(values);
						break;
					}
					default:
						throw new UnsupportedOperationException("multiplicity unsupported: " + multiplicity);
				}
				holder.setModified(true);
			}
		}
	}

	private void appendValues(final Object valueToAppend, final Collection<TypedObject> values)
	{
		final TypeService typeService = UISessionUtils.getCurrentSession().getTypeService();
		if (valueToAppend instanceof Collection)
		{
			values.addAll(typeService.wrapItems((Collection<? extends Object>) valueToAppend));
		}
		else
		{
			values.add(typeService.wrapItem(valueToAppend));
		}
	}

	public void setPublicationPeriodItem(final TypedObject publicationPeriodItem)
	{
		this.publicationPeriodItem = publicationPeriodItem;
		this.publicationPeriodObjectValueContainer = null;
	}

	public AvailableValuesProvider getCatalogVersionsForSelectedSiteProvider()
	{
		return new BaseSiteCatalogVersionValuesProvider(this);
	}

	public Map<String, Object> getAdditionalValues()
	{
		return additionalValues;
	}

	public void setAdditionalValues(final Map<String, Object> additionalValues)
	{
		this.additionalValues = additionalValues;
	}

	@Override
	public void setCurrentType(final ObjectType currentType)
	{
		String oldTypeCode = null;

		if (getCurrentType() != null)
		{
			oldTypeCode = getCurrentType().getCode();
		}

		super.setCurrentType(currentType);

		if (currentType != null && !currentType.getCode().equals(oldTypeCode))
		{
			if (getItem() != null && !getItem().getType().getCode().equals(currentType.getCode()))
			{
				setItem(null);
			}
		}
	}
}
