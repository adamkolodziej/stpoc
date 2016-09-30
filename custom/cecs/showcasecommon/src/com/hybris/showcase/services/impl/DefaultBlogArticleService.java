/**
 *
 */
package com.hybris.showcase.services.impl;

import java.util.List;

import com.hybris.showcase.dao.BlogArticleDao;
import com.hybris.showcase.model.components.BlogArticleModel;
import com.hybris.showcase.services.BlogArticleService;


/**
 * @author i327223
 *
 */
public class DefaultBlogArticleService implements BlogArticleService
{

	private BlogArticleDao blogArticleDao;

	@Override
	public BlogArticleModel findBlogByCode(final String code)
	{
		return blogArticleDao.getBlogArticleForCode(code);
	}

	@Override
	public List<BlogArticleModel> findBlogArticles()
	{
		return blogArticleDao.findBlogArticles();
	}

	@Override
	public List<BlogArticleModel> getBlogArticlesForProduct(String productCode) {
		return blogArticleDao.getBlogArticlesForProduct(productCode);
	}

	@Override
	public List<BlogArticleModel> getBlogArticlesForPage(String pageID) {
		return blogArticleDao.getBlogArticlesForPage(pageID);
	}

	/**
	 * @return the blogArticleDao
	 */
	public BlogArticleDao getBlogArticleDao()
	{
		return blogArticleDao;
	}

	/**
	 * @param blogArticleDao
	 *           the blogArticleDao to set
	 */
	public void setBlogArticleDao(final BlogArticleDao blogArticleDao)
	{
		this.blogArticleDao = blogArticleDao;
	}

}
