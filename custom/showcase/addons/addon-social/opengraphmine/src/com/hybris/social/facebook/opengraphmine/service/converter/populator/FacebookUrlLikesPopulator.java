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
package com.hybris.social.facebook.opengraphmine.service.converter.populator;

import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import com.restfb.types.User;
import de.hybris.platform.acceleratorservices.urldecoder.FrontendUrlDecoder;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.FacebookUrlLikeModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.dao.FacebookUrlLikeDao;


/**
 * @author rmcotton
 * 
 */
public class FacebookUrlLikesPopulator implements Populator<FacebookClient, FacebookUserModel>
{
	private static final Logger LOG = Logger.getLogger(FacebookUrlLikesPopulator.class);
	private FacebookUrlLikeDao urlLikeDao;
	private ModelService modelService;
	private CMSSiteService cmsSiteService;
	private SessionService sessionService;
	private UserService userService;
	private BaseSiteService baseSiteService;
	private CatalogVersionService catalogVersionService;
	private FrontendUrlDecoder<ProductModel> productUrlDecoder;
	private FrontendUrlDecoder<CategoryModel> categoryUrlDecoder;
	private int maxUrlSize = 255;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final FacebookClient source, final FacebookUserModel target) throws ConversionException
	{
		final List<FacebookUrl> result =  source.executeFqlQuery("SELECT url FROM url_like WHERE user_id = me()", FacebookUrl.class);
		final Iterator<FacebookUrl> i = result.iterator();
		final Set<String> likedUrls = new LinkedHashSet<String>();
		while (i.hasNext())
		{
			likedUrls.add(i.next().getUrl());
		}

		if (CollectionUtils.isNotEmpty(likedUrls))
		{
			final Set<FacebookUrlLikeModel> existingLikes = getUrlLikeDao().getUrlLikes(likedUrls);
			final Set<FacebookUrlLikeModel> newLikes = new HashSet<FacebookUrlLikeModel>();
			newLikes.addAll(existingLikes);
			if (existingLikes.size() < likedUrls.size())
			{
				likedUrls.removeAll(toUrlSet(existingLikes));
				for (final String remainingLikedUrl : likedUrls)
				{
					if (remainingLikedUrl.length() > getMaxUrlSize())
					{
						LOG.warn("filtered : " + remainingLikedUrl + " as size larger than : " + getMaxUrlSize());
						continue;
					}


					getSessionService().executeInLocalView(new SessionExecutionBody()
					{
						/**
						 * you can override this method if you do not need a result
						 */
						@Override
						public void executeWithoutResult()
						{
							getUserService().setCurrentUser(getUserService().getAnonymousUser());
							final FacebookUrlLikeModel newLike = newUrlLike(remainingLikedUrl);
							newLikes.add(newLike);
							newLike.setLinkedSite(tryResolveSite(remainingLikedUrl));
							if (newLike.getLinkedSite() != null)
							{
								getCatalogVersionService().setSessionCatalogVersions(getActiveProductCatalogs(newLike.getLinkedSite()));
								newLike.setLinkedProduct(tryResolveProduct(remainingLikedUrl));
								if (newLike.getLinkedProduct() == null)
								{
									newLike.setLinkedCategory(tryResolveCategory(remainingLikedUrl));
								}
							}
							getModelService().save(newLike);
						}
					});

				}
				target.setUrlLikes(newLikes);

			}
			else
			{
				target.setUrlLikes(existingLikes);
			}

		}
		else
		{
			// always save the latest state. User may have revoked out 'likes' access.
			target.setUrlLikes(Collections.EMPTY_SET);
		}
	}

	protected BaseSiteModel tryResolveSite(final String url)
	{
		try
		{
			return getCmsSiteService().getSiteForURL(new URL(url));
		}
		catch (final CMSItemNotFoundException e)
		{
			// flag page accordingly
		}
		catch (final MalformedURLException e)
		{
			// bad url
		}
		return null;
	}

	protected ProductModel tryResolveProduct(final String url)
	{
		return getProductUrlDecoder().decode(url);
	}

	protected CategoryModel tryResolveCategory(final String url)
	{
		return getCategoryUrlDecoder().decode(url);
	}

	protected List<CatalogVersionModel> getActiveProductCatalogs(final BaseSiteModel baseSite)
	{
		final List<CatalogVersionModel> versions = new LinkedList<CatalogVersionModel>();
		for (final CatalogModel catalog : getBaseSiteService().getProductCatalogs(baseSite))
		{
			versions.add(catalog.getActiveCatalogVersion());
		}
		return versions;
	}

	protected FacebookUrlLikeModel newUrlLike(final String url)
	{
		final FacebookUrlLikeModel protoype = getModelService().create(FacebookUrlLikeModel.class);
		protoype.setUrl(url);
		return protoype;
	}

	protected Set<String> toUrlSet(final Set<FacebookUrlLikeModel> likes)
	{
		final Set<String> result = new HashSet<String>();
		for (final FacebookUrlLikeModel like : likes)
		{
			result.add(like.getUrl());
		}
		return result;
	}

	public FacebookUrlLikeDao getUrlLikeDao()
	{
		return urlLikeDao;
	}

	@Required
	public void setUrlLikeDao(final FacebookUrlLikeDao urlLikeDao)
	{
		this.urlLikeDao = urlLikeDao;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
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

	public SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
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

	public FrontendUrlDecoder<ProductModel> getProductUrlDecoder()
	{
		return productUrlDecoder;
	}

	@Required
	public void setProductUrlDecoder(final FrontendUrlDecoder<ProductModel> productUrlDecoder)
	{
		this.productUrlDecoder = productUrlDecoder;
	}

	public FrontendUrlDecoder<CategoryModel> getCategoryUrlDecoder()
	{
		return categoryUrlDecoder;
	}

	@Required
	public void setCategoryUrlDecoder(final FrontendUrlDecoder<CategoryModel> categoryUrlDecoder)
	{
		this.categoryUrlDecoder = categoryUrlDecoder;
	}

	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	public int getMaxUrlSize()
	{
		return maxUrlSize;
	}

	public void setMaxUrlSize(final int maxUrlSize)
	{
		this.maxUrlSize = maxUrlSize;
	}

}
