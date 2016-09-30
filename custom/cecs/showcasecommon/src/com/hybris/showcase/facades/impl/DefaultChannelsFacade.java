package com.hybris.showcase.facades.impl;

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.servicesshowcase.model.ChannelProductModel;
import com.hybris.showcase.facades.ChannelsFacade;


/**
 * Created by miroslaw.szot@sap.com on 2016-04-18.
 */
public class DefaultChannelsFacade implements ChannelsFacade
{
	private ProductFacade productFacade;
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<ProductData> getChannels(final ProductOption... productOptions)
	{
		return getChannels(Arrays.asList(productOptions));
	}

	@Override
	public List<ProductData> getChannels(final List<ProductOption> productOptions)
	{
		final String query = "SELECT {pk} FROM {" + ChannelProductModel._TYPECODE + "} ORDER BY {code}";
		final List<ProductData> channels = flexibleSearchService.<ChannelProductModel>search(query) //
				.getResult() //
				.stream() //
				.map(channel -> {
					return productFacade.getProductForOptions(channel, productOptions);
				}) //
				.collect(Collectors.toList());

		return channels;
	}

	public ProductFacade getProductFacade()
	{
		return productFacade;
	}

	@Required
	public void setProductFacade(ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
