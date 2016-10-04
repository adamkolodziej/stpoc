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

import com.restfb.FacebookClient;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.comments.model.CommentModel;
import de.hybris.platform.comments.model.CommentTypeModel;
import de.hybris.platform.comments.model.ComponentModel;
import de.hybris.platform.comments.model.DomainModel;
import de.hybris.platform.comments.services.CommentService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import org.springframework.util.PathMatcher;

import com.hybris.social.facebook.opengraphmine.model.FacebookCommentModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.dao.FacebookUserDao;


/**
 * @author javier.moreno
 * 
 */
public class FacebookCommentsPopulator implements Populator<FacebookClient, FacebookUserModel>
{
	private static final Logger LOG = Logger.getLogger(FacebookCommentsPopulator.class);
	private FacebookUserDao facebookUserDao;
	private ModelService modelService;

	private PathMatcher pathMatcher;
	private String pathMatchPattern = "/**/p/{code}";
	private ProductService productService;
	private BaseSiteService baseSiteService;
	private CatalogVersionService catalogVersionService;
	private SessionService sessionService;
	private UserService userService;
	private CMSSiteService cmsSiteService;
	private CommentService commentService;

	private int batchSize = 100;



	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.converter.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final FacebookClient source, final FacebookUserModel target) throws ConversionException
	{

//		// obtains domain
//		final DomainModel facebookDomain = getCommentService().getDomainForCode("facebookDomain");
//
//		// obtains component
//		final ComponentModel facebookComponent = getCommentService().getComponentForCode(facebookDomain, "facebookComponent");
//
//		// obtains comment types (only one actually)
//		final Collection<CommentTypeModel> listCommentTypes = getCommentService().getAvailableCommentTypes(facebookComponent);
//
//		// gets current existing comS
//		final List<CommentModel> listOfExistingComments = getCommentService().getComments(target,
//				Collections.singleton(facebookComponent), -1, -1);
//		int offset = 0;
//		boolean more = true;
//		do
//		{
//			final List<LinkPost> posts = source.getApi().feedOperations().getLinks(offset, batchSize);
//
//			for (final LinkPost post : posts)
//			{
//				if (post.getLink() != null)
//				{
//					try
//					{
//						final URL url = new URL(post.getLink());
//						final int paramsIndex = StringUtils.indexOfAny(url.getPath(), ";?&");
//						final String cleanedUrl = paramsIndex > -1 ? url.getPath().substring(0, paramsIndex) : url.getPath();
//
//						final Map<String, String> pathParams = getPathMatcher().extractUriTemplateVariables(getPathMatchPattern(),
//								cleanedUrl);
//
//						if (pathParams != null && pathParams.size() == 1)
//						{
//
//							getSessionService().executeInLocalView(new SessionExecutionBody()
//							{
//								@Override
//								public void executeWithoutResult()
//								{
//									//can get overridden in subclasses
//									getUserService().setCurrentUser(getUserService().getAnonymousUser());
//									try
//									{
//										final CMSSiteModel site = getCmsSiteService().getSiteForURL(url);
//										getCatalogVersionService().setSessionCatalogVersions(getActiveProductCatalogs(site));
//										final String code = pathParams.get(pathParams.keySet().iterator().next());
//										final ProductModel product = getProductService().getProductForCode(code);
//										// if related to one product
//										if (product != null)
//										{
//
//
//											FacebookCommentModel facebookCommentModelToUpdate = null;
//
//											for (final CommentModel existingComment : listOfExistingComments)
//											{
//												if (existingComment instanceof FacebookCommentModel)
//												{
//													final FacebookCommentModel existingfacebookComment = (FacebookCommentModel) existingComment;
//													if (existingfacebookComment.getId().equals(post.getId()))
//													{
//														facebookCommentModelToUpdate = existingfacebookComment;
//														break;
//													}
//												}
//											}
//
//											if (facebookCommentModelToUpdate == null)
//											{
//
//												facebookCommentModelToUpdate = getModelService().create(FacebookCommentModel.class);
//
//												facebookCommentModelToUpdate.setCode(post.getId());
//												facebookCommentModelToUpdate.setAuthor(target);
//												facebookCommentModelToUpdate.setFacebookCreatedTime(post.getCreatedTime());
//												facebookCommentModelToUpdate.setId(post.getId());
//												facebookCommentModelToUpdate.setText(post.getMessage());
//
//												facebookCommentModelToUpdate.setCommentType(listCommentTypes.iterator().next());
//												facebookCommentModelToUpdate.setComponent(facebookComponent);
//												facebookCommentModelToUpdate.setRelatedItems(Collections.singleton((ItemModel) product));
//												facebookCommentModelToUpdate.setLikes(Integer.valueOf(post.getLikeCount()));
//
//												getModelService().save(facebookCommentModelToUpdate);
//											}
//											else if (facebookCommentModelToUpdate.getLikes() != null
//													&& facebookCommentModelToUpdate.getLikes() != Integer.valueOf(post.getLikeCount()))
//											{
//												// likes attribute is always updated
//												facebookCommentModelToUpdate.setLikes(Integer.valueOf(post.getLikeCount()));
//												getModelService().save(facebookCommentModelToUpdate);
//											}
//
//
//
//										}
//
//									}
//									catch (final CMSItemNotFoundException e1)
//									{
//										LOG.warn("CMS Site not found for url [" + url + "]");
//									}
//									catch (final UnknownIdentifierException e)
//									{
//										LOG.error("product associated to comment not found");
//									}
//
//								}
//							});
//						}
//					}
//					catch (final MalformedURLException e)
//					{
//						// do nothing
//					}
//					catch (final Exception e)
//					{
//						// do nothing (raised when getPathMatcher().extractUriTemplateVariables fails)
//					}
//				}
//			}
//
//			if (posts.size() < batchSize)
//			{
//				more = false;
//			}
//			offset += batchSize;
//
//		}
//		while (more);

	}

	/**
	 * @param facebookUserDao
	 *           the facebookUserDao to set
	 */
	@Required
	public void setFacebookUserDao(final FacebookUserDao facebookUserDao)
	{
		this.facebookUserDao = facebookUserDao;
	}

	/**
	 * @return the facebookUserDao
	 */
	public FacebookUserDao getFacebookUserDao()
	{
		return facebookUserDao;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	protected List<CatalogVersionModel> getActiveProductCatalogs(final CMSSiteModel target)
	{
		final List<CatalogVersionModel> versions = new LinkedList<CatalogVersionModel>();
		for (final CatalogModel catalog : getBaseSiteService().getProductCatalogs(target))
		{
			versions.add(catalog.getActiveCatalogVersion());
		}
		return versions;
	}

	/**
	 * @param pathMatcher
	 *           the pathMatcher to set
	 */
	@Required
	public void setPathMatcher(final PathMatcher pathMatcher)
	{
		this.pathMatcher = pathMatcher;
	}

	/**
	 * @return the pathMatcher
	 */
	public PathMatcher getPathMatcher()
	{
		return pathMatcher;
	}

	/**
	 * @param pathMatchPattern
	 *           the pathMatchPattern to set
	 */
	public void setPathMatchPattern(final String pathMatchPattern)
	{
		this.pathMatchPattern = pathMatchPattern;
	}

	/**
	 * @return the pathMatchPattern
	 */
	public String getPathMatchPattern()
	{
		return pathMatchPattern;
	}

	/**
	 * @param productService
	 *           the productService to set
	 */
	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	/**
	 * @return the productService
	 */
	public ProductService getProductService()
	{
		return productService;
	}

	/**
	 * @param baseSiteService
	 *           the baseSiteService to set
	 */
	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
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
	 * @param sessionService
	 *           the sessionService to set
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
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

	/**
	 * @return the commentService
	 */
	public CommentService getCommentService()
	{
		return commentService;
	}

	/**
	 * @param commentService
	 *           the commentService to set
	 */
	public void setCommentService(final CommentService commentService)
	{
		this.commentService = commentService;
	}

	public int getBatchSize()
	{
		return batchSize;
	}

	public void setBatchSize(final int batchSize)
	{
		this.batchSize = batchSize;
	}

}
