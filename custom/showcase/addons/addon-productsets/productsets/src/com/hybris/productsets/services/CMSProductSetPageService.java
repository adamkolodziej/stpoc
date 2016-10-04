/**
 * 
 */
package com.hybris.productsets.services;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;

import com.hybris.productsets.model.ProductSetModel;
import com.hybris.productsets.model.pages.ProductSetPageModel;


/**
 * @author mkostic
 * 
 */
public interface CMSProductSetPageService
{

	ProductSetPageModel getPageForProductSet(ProductSetModel productSet) throws CMSItemNotFoundException;

	ProductSetPageModel getPageForProductSetCode(String productSetCode) throws CMSItemNotFoundException;

}
