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
package com.hybris.social.common.url;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;


/**
 * Resolves the page url shared on a social network. It may not be as simple as just taking the fully qualifid url and
 * request params of the current page, since we may want to rollup the url of a page to aggregate information.
 * 
 * @author rmcotton
 * 
 */
public interface SharedPageUrlStrategy
{
	String getUrl(HttpServletRequest request, HttpServletResponse resonse);

	String getUrl(PageContext pageContext);
}
