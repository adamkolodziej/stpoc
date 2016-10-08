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

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import com.hybris.social.facebook.opengraphmine.facade.data.FacebookSuggestedProductData;
import com.hybris.social.facebook.opengraphmine.facade.data.FacebookUserData;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;
import com.hybris.social.facebook.opengraphmine.service.FacebookSuggestionService;


public class FacebookSuggestedProductDataPopulator<SOURCE extends ProductModel, TARGET extends FacebookSuggestedProductData>
		implements Populator<SOURCE, TARGET>
{
	@Resource(name = "facebookAuthenticationService")
	FacebookAuthenticationService facebookAuthenticationService;
	@Resource(name = "facebookUserDataConverter")
	Converter<FacebookUserModel, FacebookUserData> facebookUserDataConverter;
	@Resource(name = "facebookSuggestionService")
	FacebookSuggestionService facebookSuggestionService;
	@Resource(name = "productFacade")
	ProductFacade productFacade;

	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		final List<ProductOption> productOptions = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE,
				ProductOption.CATEGORIES, ProductOption.GALLERY);
		final FacebookUserModel loggedFacebookUser = facebookAuthenticationService.getCurrentFacebookUser();
		final FacebookUserData loggedFacebookUserData = facebookUserDataConverter.convert(loggedFacebookUser);
		loggedFacebookUserData.toString();

		final ProductData productData = productFacade.getProductForOptions(source, productOptions);
		// creates new wrapper of productdata
		target.setProduct(productData);
		target.setLoggedUser(loggedFacebookUserData);

		target.setUsersThatLikeIt(new HashSet<FacebookUserData>());
		target.setUsersThatCommentedIt(new HashSet<FacebookUserData>());
		target.setUsersThatPurchasedIt(new HashSet<FacebookUserData>());
	}
}
