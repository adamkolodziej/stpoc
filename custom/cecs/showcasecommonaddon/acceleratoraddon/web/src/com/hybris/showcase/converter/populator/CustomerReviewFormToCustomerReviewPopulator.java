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
package com.hybris.showcase.converter.populator;

import com.hybris.showcase.forms.AddReviewForm;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerReviewFormToCustomerReviewPopulator implements Populator<AddReviewForm, CustomerReviewModel>
{
	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Override
	public void populate(AddReviewForm addReviewForm, CustomerReviewModel customerReview) throws ConversionException {
		customerReview.setHeadline(addReviewForm.getTitle());
		customerReview.setComment(addReviewForm.getDescription());
		customerReview.setRating((double) addReviewForm.getRating());
		customerReview.setUser(userService.getCurrentUser());
		customerReview.setCommentatorName(addReviewForm.getName());
		customerReview.setProduct(productService.getProductForCode(addReviewForm.getProduct()));
	}



	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
