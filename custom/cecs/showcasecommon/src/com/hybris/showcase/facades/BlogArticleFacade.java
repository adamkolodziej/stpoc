/**
 *
 */
package com.hybris.showcase.facades;

import java.util.List;

import com.hybris.showcase.data.BlogArticleData;
import com.hybris.showcase.model.components.BlogArticleModel;


/**
 * @author i327223
 *
 */
public interface BlogArticleFacade
{
	List<BlogArticleData> getBlogArticlesData();

	BlogArticleModel getBlogArticleByCode(final String code);

	BlogArticleModel getNewestBlogArticleForProduct(final String productCode);

	List<BlogArticleData> getBlogArticlesForPage(String pageID);

}
