/**
 * 
 */
package com.hybris.campaigns.interceptors;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.model.PublicationPeriodModel;


/**
 * @author npavlovic
 * 
 */
public class PublicationPeriodPrepareInterceptor implements PrepareInterceptor
{
	private UserService userService;
	private static final String CUSTOMERGROUP_UID = "customergroup";

	@Override
	public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof PublicationPeriodModel)
		{
			final PublicationPeriodModel publicationPeriod = (PublicationPeriodModel) model;

			final boolean startDateChanged = ctx.isNew(publicationPeriod) || ctx.isModified(model, PublicationPeriodModel.STARTDATE);
			if (startDateChanged && publicationPeriod.getStartDate() == null)
			{
				final Date startDate = new DateTime(1970, 1, 1, 12, 0, 0, 0).toDate();
				publicationPeriod.setStartDate(startDate);
			}

			final boolean endDateChanged = ctx.isNew(publicationPeriod) || ctx.isModified(model, PublicationPeriodModel.ENDDATE);
			if (endDateChanged && publicationPeriod.getEndDate() == null)
			{
				final Date endDate = new DateTime(2099, 1, 1, 12, 0, 0, 0).toDate();
				publicationPeriod.setEndDate(endDate);
			}

			//if USERGROUPS list was changed
			if (ctx.isNew(publicationPeriod) || ctx.isModified(publicationPeriod, PublicationPeriodModel.USERGROUPS))
			{
				//if USERGROUPS list was changed to null or empty list set default user group
				if (publicationPeriod.getUserGroups() == null || publicationPeriod.getUserGroups().isEmpty())
				{
					final Set<UserGroupModel> userGroups = new HashSet<UserGroupModel>();
					final UserGroupModel userGroupModel = getUserService().getUserGroupForUID(CUSTOMERGROUP_UID);
					userGroups.add(userGroupModel);

					publicationPeriod.setUserGroups(userGroups);
					ctx.registerElement(publicationPeriod, getModelSource(ctx, publicationPeriod));
				}
			}
		}
	}

	private Object getModelSource(final InterceptorContext ctx, final ItemModel model)
	{
		if (ctx.isNew(model))
		{
			return null;
		}
		else
		{
			return ctx.getModelService().getSource(model);
		}
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}
}
