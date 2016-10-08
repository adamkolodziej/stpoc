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

import de.hybris.platform.cockpit.model.editor.EditorHelper;
import de.hybris.platform.cockpit.model.meta.BaseType;
import de.hybris.platform.cockpit.model.meta.ObjectTemplate;
import de.hybris.platform.cockpit.model.meta.ObjectType;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.services.values.ObjectValueContainer;
import de.hybris.platform.cockpit.services.values.ObjectValueContainer.ObjectValueHolder;
import de.hybris.platform.cockpit.services.values.ValueHandlerException;
import de.hybris.platform.cockpit.session.UISession;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.wizards.Message;
import de.hybris.platform.cockpit.wizards.Wizard;
import de.hybris.platform.cockpit.wizards.WizardPage;
import de.hybris.platform.cockpit.wizards.exception.WizardConfirmationException;
import de.hybris.platform.cockpit.wizards.impl.DefaultPageController;
import de.hybris.platform.cockpit.wizards.impl.DefaultWizardContext;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.zkoss.util.resource.Labels;

import com.hybris.campaigns.enums.Timezone;
import com.hybris.campaigns.model.PublicationPeriodModel;
import com.hybris.campaigns.services.ItemSyncStrategy;


/**
 * SHOW-1549 New Campaign Wizard
 * 
 * @author miroslaw.szot
 */
public class DefaultCampaignPageController extends DefaultPageController
{
	public static final String RESTRICT_TO_TIME_IN_DAY = "restrictToTimeInDay";
	public static final String RESTRICT_BY_DAYS_OF_WEEK = "restrictByDaysOfWeek";
	public static final String RESTRICT_BY_DATES = "restrictByDates";
	public static final String RESTRICT_BY_USERS = "restrictByUsers";

	private static final Logger LOG = Logger.getLogger(DefaultCampaignPageController.class);

	private TypeService typeService;
	private PostWizardStrategy postWizardStrategy;
	private ItemSyncStrategy itemSyncStrategy;

	@Override
	public void beforeNext(final Wizard wizard, final WizardPage page)
	{
		super.beforeNext(wizard, page);

		final CampaignWizard campaignWizard = (CampaignWizard) wizard;
		final TypedObject publicationPeriodItem = campaignWizard.getPublicationPeriodItem();

		if (isEditing(publicationPeriodItem))
		{
			final DefaultWizardContext wizardContext = (DefaultWizardContext) wizard.getWizardContext();
			final Boolean restrictByUsers = (Boolean) wizardContext.getAttribute(RESTRICT_BY_USERS);
			final Boolean restrictByDates = (Boolean) wizardContext.getAttribute(RESTRICT_BY_DATES);
			final Boolean restrictByDaysOfWeek = (Boolean) wizardContext.getAttribute(RESTRICT_BY_DAYS_OF_WEEK);
			final Boolean restrictToTimeInDay = (Boolean) wizardContext.getAttribute(RESTRICT_TO_TIME_IN_DAY);

			final ObjectValueContainer valueContainer = campaignWizard.getPublicationPeriodObjectValueContainer();

			if (restrictByDates == null)
			{
				final Date startDate = (Date) getValue(valueContainer, publicationPeriodItem.getType(),
						PublicationPeriodModel.STARTDATE).getCurrentValue();
				final Date defaultStartDate = new DateTime(1970, 1, 1, 12, 0, 0, 0).toDate();

				final Date defaultEndDate = new DateTime(2099, 1, 1, 12, 0, 0, 0).toDate();
				final Date endDate = (Date) getValue(valueContainer, publicationPeriodItem.getType(), PublicationPeriodModel.ENDDATE)
						.getCurrentValue();

				final boolean defaultDates = DateUtils.isSameDay(defaultStartDate, startDate)
						&& DateUtils.isSameDay(defaultEndDate, endDate);
				wizardContext.setAttribute(RESTRICT_BY_DATES, Boolean.valueOf(!defaultDates));
			}

			if (restrictByUsers == null)
			{
				final Collection<TypedObject> userGroupsItems = (Collection<TypedObject>) getValue(valueContainer,
						publicationPeriodItem.getType(), PublicationPeriodModel.USERGROUPS).getCurrentValue();
				final List<ItemModel> userGroups = getTypeService().unwrapItems(userGroupsItems);
				boolean specificUsers = !userGroups.isEmpty();
				if (userGroups.size() == 1)
				{
					// not covered: generally you could put customergroup as a second item and it would work as not restricted
					final UserGroupModel userGroup = (UserGroupModel) userGroups.get(0);
					specificUsers = !userGroup.getUid().equals("customergroup");
				}

				wizardContext.setAttribute(RESTRICT_BY_USERS, Boolean.valueOf(specificUsers));
			}

			if (restrictByDaysOfWeek == null)
			{
				final Collection<?> daysOfWeek = (Collection<?>) getValue(valueContainer, publicationPeriodItem.getType(),
						PublicationPeriodModel.DAYSOFWEEK).getCurrentValue();
				wizardContext.setAttribute(RESTRICT_BY_DAYS_OF_WEEK, Boolean.valueOf(CollectionUtils.isNotEmpty(daysOfWeek)));
			}

			if (restrictToTimeInDay == null)
			{
				final Integer fromHour = (Integer) getValue(valueContainer, publicationPeriodItem.getType(),
						PublicationPeriodModel.FROMHOUR).getCurrentValue();
				final Integer toHour = (Integer) getValue(valueContainer, publicationPeriodItem.getType(),
						PublicationPeriodModel.TOHOUR).getCurrentValue();
				final Timezone timezone = (Timezone) getValue(valueContainer, publicationPeriodItem.getType(),
						PublicationPeriodModel.TIMEZONE).getCurrentValue();

				final boolean timeInDayUsed = fromHour != null || toHour != null
						|| (timezone != null && !Timezone.SYSTEM.equals(timezone));
				wizardContext.setAttribute(RESTRICT_TO_TIME_IN_DAY, Boolean.valueOf(timeInDayUsed));
			}
		}

	}

	protected boolean isEditing(final TypedObject item)
	{
		final ModelService modelService = UISessionUtils.getCurrentSession().getModelService();
		return item != null && !modelService.isNew(item.getObject());
	}

	@Override
	public void done(final Wizard wizard, final WizardPage page) throws WizardConfirmationException
	{
		if (next(wizard, page) == null)
		{
			final Boolean restrictByUsers = (Boolean) wizard.getWizardContext().getAttribute(RESTRICT_BY_USERS);
			final Boolean restrictByDates = (Boolean) wizard.getWizardContext().getAttribute(RESTRICT_BY_DATES);
			final Boolean restrictByDaysOfWeek = (Boolean) wizard.getWizardContext().getAttribute(RESTRICT_BY_DAYS_OF_WEEK);
			final Boolean restrictToTimeInDay = (Boolean) wizard.getWizardContext().getAttribute(RESTRICT_TO_TIME_IN_DAY);

			if (LOG.isDebugEnabled())
			{
				LOG.debug("restrictByUsers: " + restrictByUsers);
				LOG.debug("restrictByDates: " + restrictByDates);
				LOG.debug("restrictByDaysOfWeek: " + restrictByDaysOfWeek);
				LOG.debug("restrictToTimeInDay: " + restrictToTimeInDay);
			}

			final CampaignWizard campaignWizard = (CampaignWizard) wizard;
			final TypedObject promotionItem = campaignWizard.getItem(); // if it's null it means we don't care about promotions (promotion steps detached)
			if (promotionItem != null)
			{
				final ObjectValueContainer promotionValueContainer = campaignWizard.getObjectValueContainer();

				final Set<ObjectValueHolder> allValues = promotionValueContainer.getAllValues();
				for (final ObjectValueHolder objectValueHolder : allValues)
				{
					// HACK: somehow wizard mandatory page in edit mode stores all the values but also they are not cosnidered as modified
					// and later not saved
					objectValueHolder.setModified(true);
				}
				saveChanges(wizard, promotionItem, promotionValueContainer);
			}

			final TypedObject publicationPeriodItem = campaignWizard.getPublicationPeriodItem();
			final ObjectValueContainer publicationPeriodValueContainer = campaignWizard.getPublicationPeriodObjectValueContainer();

			if (BooleanUtils.isFalse(restrictByUsers))
			{
				setValue(publicationPeriodValueContainer, publicationPeriodItem, PublicationPeriodModel.USERGROUPS, null);
			}

			if (BooleanUtils.isFalse(restrictByDates))
			{
				setValue(publicationPeriodValueContainer, publicationPeriodItem, PublicationPeriodModel.STARTDATE, null);
				setValue(publicationPeriodValueContainer, publicationPeriodItem, PublicationPeriodModel.ENDDATE, null);
			}

			if (BooleanUtils.isFalse(restrictByDaysOfWeek))
			{
				setValue(publicationPeriodValueContainer, publicationPeriodItem, PublicationPeriodModel.DAYSOFWEEK, null);
			}

			if (BooleanUtils.isFalse(restrictToTimeInDay))
			{
				setValue(publicationPeriodValueContainer, publicationPeriodItem, PublicationPeriodModel.FROMHOUR, null);
				setValue(publicationPeriodValueContainer, publicationPeriodItem, PublicationPeriodModel.TOHOUR, null);
				setValue(publicationPeriodValueContainer, publicationPeriodItem, PublicationPeriodModel.TIMEZONE, null);
			}

			if (promotionItem != null)
			{
				adjustPromotions(promotionItem, publicationPeriodItem, publicationPeriodValueContainer);
			}

			saveChanges(wizard, publicationPeriodItem, publicationPeriodValueContainer);

			getItemSyncStrategy().synchronize(publicationPeriodItem);

			getPostWizardStrategy().afterWizardAction(campaignWizard, page);
		}
		super.done(wizard, page);
	}

	protected void adjustPromotions(final TypedObject promotionItem, final TypedObject publicationPeriodItem,
			final ObjectValueContainer publicationPeriodValueContainer)
	{
		final String qualifier = PublicationPeriodModel.PROMOTIONS;
		final BaseType type = publicationPeriodItem.getType();
		Set<AbstractPromotionModel> promos = (Set<AbstractPromotionModel>) getValue(publicationPeriodValueContainer, type,
				qualifier).getCurrentValue();
		promos = new HashSet<AbstractPromotionModel>(promos == null ? Collections.EMPTY_SET : promos);
		promos.add((AbstractPromotionModel) promotionItem.getObject());
		setValue(publicationPeriodValueContainer, publicationPeriodItem, qualifier, promos);

	}

	protected ObjectValueHolder getValue(final ObjectValueContainer valueContainer, final BaseType type, final String qualifier)
	{
		final PropertyDescriptor propertyDescriptor = getTypeService()
				.getPropertyDescriptor(type, type.getCode() + "." + qualifier);
		final ObjectValueHolder valueHolder = valueContainer.getValue(propertyDescriptor, null);

		return valueHolder;
	}

	protected void setValue(final ObjectValueContainer valueContainer, final TypedObject item, final String qualifier,
			final Object value)
	{
		final PropertyDescriptor propertyDescriptor = getTypeService().getPropertyDescriptor(item.getType(),
				item.getType().getCode() + "." + qualifier);
		final ObjectValueHolder valueHolder = valueContainer.getValue(propertyDescriptor, null);
		valueHolder.setLocalValue(value);
	}

	private void saveChanges(final Wizard wizard, TypedObject item, final ObjectValueContainer valueContainer)
	{
		final UISession uiSession = UISessionUtils.getCurrentSession();
		final ModelService modelService = uiSession.getModelService();
		try
		{
			if (modelService.isNew(item.getObject()))
			{
				item = createNewItem(item.getType(), valueContainer);
			}
			else
			{
				EditorHelper.persistValues(item, valueContainer);
			}
		}
		catch (final Exception e)
		{
			final Message msg = createErrorMessageForException(e);
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Could not create item.", e);
			}
			wizard.addMessage(msg);
			//re-thrown  
			throw new WizardConfirmationException(e);
		}
	}

	private TypedObject createNewItem(final ObjectType type, final ObjectValueContainer valueContainer)
			throws ValueHandlerException
	{
		final UISession session = UISessionUtils.getCurrentSession();
		final ObjectTemplate template = getTypeService().getObjectTemplate(type.getCode());
		final TypedObject newItem = session.getNewItemService().createNewItem(valueContainer, template);
		return newItem;
	}

	protected Message createErrorMessageForException(final Exception exception)
	{
		final String label = Labels.getLabel("editorarea.persist.error", new String[]
		{ ":" + exception.getMessage() });

		return new Message(Message.ERROR, label, null);
	}

	public TypeService getTypeService()
	{
		if (typeService == null)
		{
			final UISession session = UISessionUtils.getCurrentSession();
			typeService = session.getTypeService();
		}
		return typeService;
	}

	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

	public PostWizardStrategy getPostWizardStrategy()
	{
		return postWizardStrategy;
	}

	public void setPostWizardStrategy(final PostWizardStrategy postWizardStrategy)
	{
		this.postWizardStrategy = postWizardStrategy;
	}

	public ItemSyncStrategy getItemSyncStrategy()
	{
		return itemSyncStrategy;
	}

	public void setItemSyncStrategy(final ItemSyncStrategy itemSyncStrategy)
	{
		this.itemSyncStrategy = itemSyncStrategy;
	}

}
