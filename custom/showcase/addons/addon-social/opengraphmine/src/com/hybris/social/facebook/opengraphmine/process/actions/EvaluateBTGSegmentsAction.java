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
package com.hybris.social.facebook.opengraphmine.process.actions;

import de.hybris.platform.btg.enums.BTGConditionEvaluationScope;
import de.hybris.platform.btg.enums.BTGEvaluationMethod;
import de.hybris.platform.btg.enums.BTGResultScope;
import de.hybris.platform.btg.enums.BTGRuleType;
import de.hybris.platform.btg.model.BTGRuleModel;
import de.hybris.platform.btg.model.BTGSegmentModel;
import de.hybris.platform.btg.segment.SegmentService;
import de.hybris.platform.btg.services.BTGEvaluationService;
import de.hybris.platform.btg.services.BTGResultService;
import de.hybris.platform.btg.services.impl.BTGEvaluationContext;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.task.RetryLaterException;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.model.process.FacebookSynchronizationProcessModel;


/**
 * @author rmcotton
 * 
 */
public class EvaluateBTGSegmentsAction extends AbstractSimpleDecisionAction
{
	private CatalogVersionService catalogVersionService;
	private SegmentService segmentService;
	private CMSSiteService cmsSiteService;
	private BTGResultService btgResultService;
	private BTGEvaluationService btgEvaluationService;

	private BTGRuleType[] ruleTypes = new BTGRuleType[]
	{ BTGRuleType.FACEBOOK };


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.processengine.action.AbstractSimpleDecisionAction#executeAction(de.hybris.platform.processengine
	 * .model.BusinessProcessModel)
	 */
	@Override
	public Transition executeAction(final BusinessProcessModel arg0) throws RetryLaterException, Exception
	{
		final FacebookSynchronizationProcessModel model = (FacebookSynchronizationProcessModel) arg0;

		final BTGEvaluationContext context = new BTGEvaluationContext(BTGConditionEvaluationScope.ONLINE, BTGEvaluationMethod.FULL,
				BTGResultScope.PERMANENT);

		for (final UserModel user : getUsers(model))
		{
			for (final BTGRuleType ruleType : getRuleTypes())
			{
				getBtgResultService().invalidateEvaluationResults(getCmsSiteService().getSites(), user, context, ruleType);
			}

			final List<BTGSegmentModel> segmentsToEvaluate = getApplicableBtgSegments(user);
			for (final BTGSegmentModel segment : segmentsToEvaluate)
			{
				getBtgEvaluationService().evaluateSegment(user, segment, context);
			}
		}

		return Transition.OK;
	}

	/**
	 * In the event of the facebook user not being linked to a user account we must run the rules against the facebook
	 * user. However if the account is linked, the rules are run against both the facebook user account and the linked
	 * customer. This ensure segments are still valid if the users is independently logged in via either methods.
	 */
	protected Set<? extends UserModel> getUsers(final FacebookSynchronizationProcessModel model)
	{
		final FacebookUserModel facebookUser = model.getUser();
		if (facebookUser.getLinkedCustomer() == null)
		{
			return Collections.singleton(facebookUser);
		}
		else
		{
			final Set<UserModel> users = new LinkedHashSet<UserModel>();
			users.add(facebookUser.getLinkedCustomer());
			users.add(facebookUser);
			return users;
		}
	}

	protected List<BTGSegmentModel> getApplicableBtgSegments(final UserModel user)
	{
		final List<BTGRuleType> ruleTypesList = Arrays.asList(getRuleTypes());
		final List<BTGSegmentModel> result = new LinkedList<BTGSegmentModel>();
		for (final BTGSegmentModel segment : getSegmentService().getSegments(getCmsSiteService().getSites(),
				BTGConditionEvaluationScope.ONLINE, getCatalogVersionService().getAllCatalogVersions()))
		{
			for (final BTGRuleModel rule : segment.getRules())
			{
				if (ruleTypesList.contains(rule.getRuleType()))
				{
					result.add(segment);
					break;
				}
			}
		}
		return result;
	}


	/**
	 * @param catalogVersionService
	 *           the catalogVersionService to set
	 */
	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}


	/**
	 * @return the catalogVersionService
	 */
	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	/**
	 * @return the segmentService
	 */
	public SegmentService getSegmentService()
	{
		return segmentService;
	}


	/**
	 * @param segmentService
	 *           the segmentService to set
	 */
	@Required
	public void setSegmentService(final SegmentService segmentService)
	{
		this.segmentService = segmentService;
	}

	/**
	 * @param cmsSiteService
	 *           the cmsSiteService to set
	 */
	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	/**
	 * @return the cmsSiteService
	 */
	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	public void setRuleTypes(final BTGRuleType[] ruleTypes)
	{
		this.ruleTypes = ruleTypes;
	}

	public BTGRuleType[] getRuleTypes()
	{
		return this.ruleTypes;
	}

	public BTGResultService getBtgResultService()
	{
		return btgResultService;
	}

	@Required
	public void setBtgResultService(final BTGResultService btgResultService)
	{
		this.btgResultService = btgResultService;
	}

	public BTGEvaluationService getBtgEvaluationService()
	{
		return btgEvaluationService;
	}

	@Required
	public void setBtgEvaluationService(final BTGEvaluationService btgEvaluationService)
	{
		this.btgEvaluationService = btgEvaluationService;
	}


}
