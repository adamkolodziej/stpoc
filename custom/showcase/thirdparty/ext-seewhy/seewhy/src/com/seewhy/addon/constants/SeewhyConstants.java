/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.seewhy.addon.constants;


/**
 * Global class for all Seewhy constants. You can add global constants for your extension into this class.
 */
public final class SeewhyConstants extends GeneratedSeewhyConstants
{
	public static final String EXTENSIONNAME = "seewhy";

	private SeewhyConstants()
	{
		//empty to avoid instantiating this constant class
	}

	// implement here constants used by this extension

	public static final String SEEWHY_ACTION_ADD_TO_CART = "cyActionAddToCart";
	public static final String SEEWHY_ACTION_ORDER_PLACED = "cyActionOrderPlaced";
	public static final String SEEWHY_ACTION_PRODUCT_BROWSED = "cyActionProductBrowsed";
	public static final String SEEWHY_ACTION_PRODUCT_REVIEWED = "cyActionProductReviewed";
	public static final String SEEWHY_ACTION_EMAIL_CAPTURED = "cyActionEmailCaptured";
	public static final String SEEWHY_ACTION_GENERAL = "cyActionGeneral";

	public static final String[] SEEWHY_ACTIONS =
	{ SEEWHY_ACTION_ADD_TO_CART, SEEWHY_ACTION_ORDER_PLACED, SEEWHY_ACTION_PRODUCT_BROWSED, SEEWHY_ACTION_PRODUCT_REVIEWED,
			SEEWHY_ACTION_EMAIL_CAPTURED, SEEWHY_ACTION_GENERAL };

	public static final String SEEWHY_ACTION_GENERIC_PRODUCTCODE = "ItemID";
	public static final String SEEWHY_ACTION_GENERIC_PRODUCTDESC = "ItemDesc";
	public static final String SEEWHY_ACTION_GENERIC_BASEPRODUCTCODE = "ItemMasterID";
	public static final String SEEWHY_ACTION_GENERIC_PRODUCTNAME = "ItemName";
	public static final String SEEWHY_ACTION_GENERIC_PRODUCTPRICEVALUE = "ItemPrice";
	public static final String SEEWHY_ACTION_GENERIC_PRODUCTPRICECURRENCY = "ItemPriceCurrency";
	public static final String SEEWHY_ACTION_GENERIC_PRODUCTURL = "ItemPageURL";
	public static final String SEEWHY_ACTION_GENERIC_PRODUCTIMAGEURL = "ItemImageURL";
	public static final String SEEWHY_ACTION_GENERIC_CATEGORYNAME = "ItemCategory";
	//public static final String SEEWHY_ACTION_GENERIC_ORDERTOTALPRICE = "CartValue";
	public static final String SEEWHY_ACTION_GENERIC_PRODUCTAVGREVIEWRATING = "ItemReviewScore";

	public static final String SEEWHY_ACTION_GENERAL_PAGETYPE = "PageType";
	public static final String SEEWHY_ACTION_GENERAL_SERVERCONTEXT = "ServerContext";

	public static final String SEEWHY_ACTION_ORDER_PLACED_ORDERVALUE = "OrderValue";
	public static final String SEEWHY_ACTION_ORDER_PLACED_ORDERNUMBER = "OrderNumber";
	public static final String SEEWHY_ACTION_ORDER_PLACED_TOTALVALUE = "TotalValue";

	public static final String SEEWHY_ACTION_ADD_TO_CART_PRODUCTCODE = SEEWHY_ACTION_GENERIC_PRODUCTCODE;
	public static final String SEEWHY_ACTION_ADD_TO_CART_CARTVALUE = "CartValue";
	public static final String SEEWHY_ACTION_ADD_TO_CART_RETURNTOLINK = "ReturnToLink";
	public static final String SEEWHY_ACTION_ADD_TO_CART_PRODUCTQTY = "ItemQuantity";


	public static final String SEEWHY_ACTION_PRODUCT_BROWSED_PRODUCTCODE = SEEWHY_ACTION_GENERIC_PRODUCTCODE;
	public static final String SEEWHY_ACTION_PRODUCT_BROWSED_BROWSETYPE = "BrowseType";

	public static final String SEEWHY_ACTION_PRODUCT_REVIEWED_PRODUCTCODE = SEEWHY_ACTION_GENERIC_PRODUCTCODE;
	public static final String SEEWHY_ACTION_PRODUCT_REVIEWED_RATING = "ReviewScore";

	public static final String SEEWHY_ACTION_EMAIL_CAPTURED_ADDRESS = "EmailAddress";
	public static final String SEEWHY_ACTION_EMAIL_CAPTURED_FIRSTNAME = "FirstName";
	public static final String SEEWHY_ACTION_EMAIL_CAPTURED_LASTNAME = "LastName";

	public static final int SEEWHY_ACTION_PRODUCT_BROWSED_NONE = 0;
	public static final int SEEWHY_ACTION_PRODUCT_BROWSED_STANDARD = 1;
	public static final int SEEWHY_ACTION_PRODUCT_BROWSED_QUICKVIEW = 2;

	public static final String SEEWHY_JSADDONSVARIABLES_ENTRY_VALUE_TRUE = "true";

	public static final String MODEL_ENTRY_JSADDONSVARIABLES = "jsAddOnsVariables";
	public static final String MODEL_ENTRY_REQUEST = "request";
	public static final String MODEL_ENTRY_PAGETYPE = "pageType";
	public static final String MODEL_ENTRY_CATEGORYNAME = "categoryName";
	public static final String MODEL_ENTRY_BREADCRUMBS = "breadcrumbs";
	public static final String MODEL_ENTRY_PRODUCT = "product";

	public static final String CORE_URL_SUFFIX_CART = "/cart";

	public static final String SEEWHY_VIEWNAME_ADDTOCARTPOPUP = "addon:/seewhy/fragments/cart/addToCartPopup";
	public static final String SEEWHY_VIEWNAME_QUICKVIEWPOPUP = "addon:/seewhy/fragments/product/quickViewPopup";
	public static final String CORE_VIEWNAME_ADDTOCARTPOPUP = "fragments/cart/addToCartPopup";
	public static final String CORE_VIEWNAME_QUICKVIEWPOPUP = "fragments/product/quickViewPopup";

	public static final String CORE_REVIEW_PATTERN = "/.*?/p/([^/]*?)/review$";
	public static final String CORE_REVIEW_RATING = "rating";

	public static final String CORE_GUESTLOGIN_PATTERN = ".*?/login/checkout/guest$";
	public static final String CORE_GUESTLOGIN_EMAIL = "email";
}
