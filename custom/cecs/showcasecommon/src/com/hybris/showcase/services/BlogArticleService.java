/**
 *
 */
package com.hybris.showcase.services;

import java.util.List;

import com.hybris.showcase.model.components.BlogArticleModel;


/**
 * @author i327223
 *
 */
public interface BlogArticleService
{
	BlogArticleModel findBlogByCode(final String code);

	List<BlogArticleModel> findBlogArticles();

	List<BlogArticleModel> getBlogArticlesForProduct(final String productCode);

	List<BlogArticleModel> getBlogArticlesForPage(final String pageID);
}
