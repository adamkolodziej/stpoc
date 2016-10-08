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
package com.hybris.social.pinterest.component.renderer;

import de.hybris.platform.acceleratorcms.component.renderer.CMSComponentRenderer;
import de.hybris.platform.acceleratorservices.storefront.data.MetaElementData;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ImageDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.core.model.media.MediaModel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.tag.common.core.UrlSupport;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.addon.common.url.MediaAbsoluteUrlResolver;
import com.hybris.social.common.url.SharedPageUrlStrategy;
import com.hybris.social.pinterest.enums.PinCountLayoutStyle;
import com.hybris.social.pinterest.model.PinItButtonComponentModel;


/**
 * @author rmcotton
 * 
 */
public class PinItComponentRenderer implements CMSComponentRenderer<PinItButtonComponentModel>
{
	private SharedPageUrlStrategy urlStrategy;
	private MediaAbsoluteUrlResolver mediaAbsoluteUrlResolver;

	private String productImageFormat = "zoom";
	private String storeImageFormat = "store";

	private String urlEncoding = "UTF-8";
	private CommerceCommonI18NService commerceCommonI18nService;


	@Override
	public void renderComponent(final PageContext pageContext, final PinItButtonComponentModel component) throws ServletException,
			IOException
	{
		final JspWriter out = pageContext.getOut();
		out.write(buildButtonHTML(pageContext, component));
	}



	protected String buildButtonHTML(final PageContext pageContext, final PinItButtonComponentModel component)
	{
		final StringBuilder html = new StringBuilder();
		html.append("<a href=\"").append(getBaseHref());
		html.append("?url=").append(encodeUrl(getUrlToPin(pageContext, component)));

		final String image2pin = getImageToPin(pageContext, component);
		if (StringUtils.isNotBlank(image2pin))
		{
			html.append("&media=").append(encodeUrl(image2pin));
		}

		final String description = getDescription(pageContext, component);
		if (StringUtils.isNotBlank(description))
		{
			html.append("&description=").append(encodeUrl(description));
		}

		html.append("\" data-pin-do=\"buttonPin\"");
		html.append(" data-pin-config=\"").append(getPinCountLayout(pageContext, component).getCode()).append("\"");

		if (!getPinCountLayout(pageContext, component).equals(PinCountLayoutStyle.NONE))
		{
			// workaround for a bug where counter doesnt show in horizontal mode if 0 pins
			html.append(" always-show-count=\"1\"");
		}

		html.append(" >");

		html.append("</a>");
		return html.toString();
	}

	public void setUrlEncoding(final String urlEncoding)
	{
		this.urlEncoding = urlEncoding;
	}

	public String getUrlEncoding()
	{
		return this.urlEncoding;
	}

	protected String getBaseHref()
	{
		return "http://pinterest.com/pin/create/button/";
	}


	protected String getImageUrl()
	{
		return "//assets.pinterest.com/images/pidgets/pin_it_button.png";
	}

	protected String encodeUrl(final String url)
	{
		try
		{
			return URLEncoder.encode(url, getUrlEncoding());
		}
		catch (final UnsupportedEncodingException e)
		{
			throw new IllegalStateException(e);
		}
	}

	protected Locale getLocale()
	{
		return getCommerceCommonI18NService().getLocaleForLanguage(getCommerceCommonI18NService().getCurrentLanguage());
	}

	protected String resolveImageUrl(final ImageData image, final PageContext pageContext)
	{
		return getMediaAbsoluteUrlResolver().resolve((HttpServletRequest) pageContext.getRequest(), image,
				pageContext.getRequest().isSecure());
	}

	protected String resolveImageUrl(final MediaModel image, final PageContext pageContext)
	{
		return getMediaAbsoluteUrlResolver().resolve((HttpServletRequest) pageContext.getRequest(), image,
				pageContext.getRequest().isSecure());
	}

	protected String resolveUrl(final String url, final PageContext pageContext)
	{
		try
		{
			return UrlSupport.resolveUrl(url, null, pageContext);
		}
		catch (final JspException jspe)
		{
			throw new IllegalStateException(jspe);
		}
	}


	protected String getUrlToPin(final PageContext pageContext, final PinItButtonComponentModel component)
	{
		final String url;
		if (StringUtils.isNotBlank(component.getUrlToPin()))
		{
			url = resolveUrl(component.getUrlToPin(), pageContext);
		}
		else
		{
			url = getSharedPageUrlStrategy().getUrl(pageContext);
		}
		return url;
	}

	protected String getDescription(final PageContext pageContext, final PinItButtonComponentModel component)
	{
		if (StringUtils.isNotBlank(component.getDescription()))
		{
			return component.getDescription();
		}
		return getDescription(pageContext);
	}

	protected String getDescription(final PageContext pageContext)
	{
		final ProductData product = resolveProduct(pageContext);
		if (product != null)
		{
			if (StringUtils.isNotBlank(product.getSummary()))
			{
				return product.getSummary();
			}
		}

		final List<MetaElementData> metaElements = (List<MetaElementData>) pageContext.getRequest().getAttribute("metatags");
		for (final MetaElementData metaElement : metaElements)
		{
			if ("description".equalsIgnoreCase(metaElement.getName()))
			{
				return metaElement.getContent();
			}
		}
		return null;
	}

	public ImageData getPrimaryImageForProduct(final ProductData product)
	{

		final Collection<ImageData> images = product.getImages();
		if (images != null && !images.isEmpty())
		{
			for (final ImageData image : images)
			{
				if (ImageDataType.PRIMARY.equals(image.getImageType()) && getProductImageFormat().equals(image.getFormat()))
				{
					return image;
				}
			}
		}

		return null;
	}

	/**
	 * JSP EL Function to get an Image for a Store in a specific format
	 * 
	 * @param store
	 *           the store
	 * @param format
	 *           the desired image format
	 * @return the image
	 */
	public ImageData getImageForStore(final PointOfServiceData store)
	{

		final Collection<ImageData> images = store.getStoreImages();
		if (images != null && !images.isEmpty())
		{
			for (final ImageData image : images)
			{
				if (getStoreImageFormat().equals(image.getFormat()))
				{
					return image;
				}
			}
		}

		return null;
	}

	protected ProductData resolveProduct(final PageContext pageContext)
	{
		return (ProductData) pageContext.getRequest().getAttribute("product");
	}

	protected PointOfServiceData resolveStore(final PageContext pageContext)
	{
		return (PointOfServiceData) pageContext.getRequest().getAttribute("store");
	}

	protected ImageData resolveImage(final PageContext pageContext, final PinItButtonComponentModel component)
	{
		final ProductData product = resolveProduct(pageContext);
		if (product != null)
		{
			final ImageData image = getPrimaryImageForProduct(product);
			if (image != null)
			{
				return image;
			}
		}

		final PointOfServiceData store = resolveStore(pageContext);
		if (store != null)
		{
			final ImageData image = getImageForStore(store);
			if (image != null)
			{
				return image;
			}
		}

		return null;
	}

	protected String getImageToPin(final PageContext pageContext, final PinItButtonComponentModel component)
	{
		if (component.getImageToPin() != null)
		{
			return resolveImageUrl(component.getImageToPin(), pageContext);
		}

		final ImageData image = resolveImage(pageContext, component);
		if (image != null)
		{
			return resolveImageUrl(image, pageContext);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.pinterest.web.AbstractPinItTag#getPinCountLayout()
	 */
	protected PinCountLayoutStyle getPinCountLayout(final PageContext pageContext, final PinItButtonComponentModel component)
	{
		return component.getPinCountLayout();
	}

	@Required
	public void setSharedPageUrlStrategy(final SharedPageUrlStrategy sharedPageUrlStrategy)
	{
		this.urlStrategy = sharedPageUrlStrategy;
	}

	public SharedPageUrlStrategy getSharedPageUrlStrategy()
	{
		return this.urlStrategy;
	}


	/**
	 * @return the productImageFormat
	 */
	public String getProductImageFormat()
	{
		return productImageFormat;
	}


	/**
	 * @param productImageFormat
	 *           the productImageFormat to set
	 */
	public void setProductImageFormat(final String productImageFormat)
	{
		this.productImageFormat = productImageFormat;
	}

	public void setStoreImageFormat(final String storeImageFormat)
	{
		this.storeImageFormat = storeImageFormat;
	}

	/**
	 * @return the storeImageFormat
	 */
	public String getStoreImageFormat()
	{
		return storeImageFormat;
	}


	/**
	 * @return the commerceCommonI18nService
	 */
	public CommerceCommonI18NService getCommerceCommonI18NService()
	{
		return commerceCommonI18nService;
	}



	/**
	 * @param commerceCommonI18nService
	 *           the commerceCommonI18nService to set
	 */
	@Required
	public void setCommerceCommonI18NService(final CommerceCommonI18NService commerceCommonI18nService)
	{
		this.commerceCommonI18nService = commerceCommonI18nService;
	}



	/**
	 * @return the mediaAbsoluteUrlResolver
	 */
	public MediaAbsoluteUrlResolver getMediaAbsoluteUrlResolver()
	{
		return mediaAbsoluteUrlResolver;
	}



	/**
	 * @param mediaAbsoluteUrlResolver
	 *           the mediaAbsoluteUrlResolver to set
	 */
	@Required
	public void setMediaAbsoluteUrlResolver(final MediaAbsoluteUrlResolver mediaAbsoluteUrlResolver)
	{
		this.mediaAbsoluteUrlResolver = mediaAbsoluteUrlResolver;
	}

}
