package com.hybris.showcase.btg.outputaction.impl;

import com.hybris.showcase.cecs.servicesshowcase.model.BTGRemoveFromGroupDefinitionModel;
import de.hybris.platform.btg.outputaction.OutputActionContext;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.action.impl.ActionPerformable;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.model.action.AbstractActionModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * CECS-192 promotion campaign for sports channel
 *
 * Created by mgolubovic on 14.4.2015..
 */
public class RemoveFromGroup implements ActionPerformable<OutputActionContext>
{
	private ModelService modelService;
	private UserService userService;

	@Override
	public void performAction(AbstractActionModel action, OutputActionContext argument)
	{
		final Collection userGroupsToRemove = ((BTGRemoveFromGroupDefinitionModel) argument.getActionDefinition()).getUserGroups();
		final UserModel user = argument.getUser();
		if (!userService.isAnonymousUser(user) && CollectionUtils.isNotEmpty(userGroupsToRemove))
		{
			final Set<PrincipalGroupModel> userGroups = new HashSet<>(user.getGroups());
			userGroups.removeAll(userGroupsToRemove);
			user.setGroups(userGroups);
			modelService.save(user);
		}
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
}
