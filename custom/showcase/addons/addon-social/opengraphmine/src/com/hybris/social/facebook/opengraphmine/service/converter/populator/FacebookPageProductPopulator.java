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

import com.restfb.types.Page;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.PathMatcher;

import com.hybris.social.facebook.opengraphmine.model.FacebookPageModel;


/**
 * @author rmcotton
 * 
 */
public class FacebookPageProductPopulator implements Populator<Page, FacebookPageModel>
{
	private static final Logger LOG = Logger.getLogger(FacebookPageProductPopulator.class);

	private PathMatcher pathMatcher;
	private String pathMatchPattern = "/**/p/{code}";
	private ProductService productService;
	private BaseSiteService baseSiteService;
	private CatalogVersionService catalogVersionService;
	private SessionService sessionService;
	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commerceservices.converter.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final Page source, final FacebookPageModel target) throws ConversionException
	{
		if (target.getLinkedSite() != null && StringUtils.isNotBlank(target.getLink()))
		{
			try
			{
				final URL url = new URL(target.getLink());
				final int paramsIndex = StringUtils.indexOfAny(url.getPath(), ";?&");
				final String cleanedUrl = paramsIndex > -1 ? url.getPath().substring(0, paramsIndex) : url.getPath();
				if (getPathMatcher().match(getPathMatchPattern(), cleanedUrl))
				{
					final Map<String, String> pathParams = getPathMatcher().extractUriTemplateVariables(getPathMatchPattern(),
							cleanedUrl);

					if (pathParams == null || pathParams.size() > 1)
					{
						LOG.error("unable to extract product id from path " + url.getPath() + " and pattern " + getPathMatchPattern());
						return;
					}

					getSessionService().executeInLocalView(new SessionExecutionBody()
					{
						@Override
						public void executeWithoutResult()
						{
							//can get overridden in subclasses
							getUserService().setCurrentUser(getUserService().getAnonymousUser());
							getCatalogVersionService().setSessionCatalogVersions(getActiveProductCatalogs(target));
							final String code = pathParams.get(pathParams.keySet().iterator().next());

							try
							{
								final ProductModel product = getProductService().getProductForCode(code);

								if (!target.getLinkedProducts().contains(product))
								{
									final Set<ProductModel> products = new LinkedHashSet<ProductModel>(target.getLinkedProducts());
									products.add(product);
									target.setLinkedProducts(products);
								}
							}
							catch (final UnknownIdentifierException e)
							{
								LOG.error("unable to locate visible product for code " + code);
							}

						}
					});
				}
			}
			catch (final MalformedURLException e)
			{
				//
			}
		}
	}

	protected List<CatalogVersionModel> getActiveProductCatalogs(final FacebookPageModel target)
	{
		final List<CatalogVersionModel> versions = new LinkedList<CatalogVersionModel>();
		for (final CatalogModel catalog : getBaseSiteService().getProductCatalogs(target.getLinkedSite()))
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



}
