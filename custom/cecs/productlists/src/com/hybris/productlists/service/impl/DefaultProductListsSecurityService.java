/**
 * 
 */
package com.hybris.productlists.service.impl;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.productlists.model.PrincipalListOwnerModel;
import com.hybris.productlists.model.ProductListModel;
import com.hybris.productlists.service.ProductListsSecurityService;


/**
 * @author simonhuggins
 * 
 */
public class DefaultProductListsSecurityService implements ProductListsSecurityService
{


	private ModelService modelService;
	private KeyGenerator keyGenerator;
	private UserService userService;

	@Override
	public String generateGuid()
	{
		final String guid = generateRawGuid(getKeyGenerator());
		return encodeBase64(guid.getBytes()).toString();
	}

	public boolean checkPassword(final ProductListModel productList, final byte[] password)
	{

		Registry.getCurrentTenant().getJaloConnection().getPasswordEncoder("MD5");

		return false;
	}

	protected String generateRawGuid(final KeyGenerator generator)
	{
		return generator.generate().toString();
	}

	/**
	 * @see com.hybris.productlists.service.ProductListsSecurityService#makeOwner(com.hybris.productlists.model.ProductListModel)
	 */
	@Override
	public boolean transferAnonymousList(final ProductListModel productList)
	{
		if (CollectionUtils.isEmpty(productList.getListOwners()))
		{
			if (getUserService().isAnonymousUser(getUserService().getCurrentUser()))
			{
				return false;
			}
			else
			{
				final PrincipalListOwnerModel principalListOwner = new PrincipalListOwnerModel();
				principalListOwner.setPrincipal(getUserService().getCurrentUser());
				final Collection owners = Collections.singletonList(principalListOwner);
				productList.setListOwners(owners);
				getModelService().save(principalListOwner);
				getModelService().save(productList);
				return true;
			}
		}
		return false;
	}

	public KeyGenerator getKeyGenerator()
	{
		return keyGenerator;
	}

	@Required
	public void setKeyGenerator(final KeyGenerator keyGenerator)
	{
		this.keyGenerator = keyGenerator;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
