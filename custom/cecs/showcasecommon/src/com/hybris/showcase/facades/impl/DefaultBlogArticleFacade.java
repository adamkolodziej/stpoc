/**
 *
 */
package com.hybris.showcase.facades.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.*;

import com.hybris.showcase.data.BlogArticleData;
import com.hybris.showcase.facades.BlogArticleFacade;
import com.hybris.showcase.model.components.BlogArticleModel;
import com.hybris.showcase.services.BlogArticleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;


/**
 * @author i327223
 *
 */
public class DefaultBlogArticleFacade implements BlogArticleFacade
{
	private Converter<BlogArticleModel, BlogArticleData> blogArticleConverter;
	private BlogArticleService blogArticleService;
	private static final Logger LOG = Logger.getLogger(DefaultBlogArticleFacade.class);
	@Override
	public BlogArticleModel getBlogArticleByCode(final String code)
	{
		return blogArticleService.findBlogByCode(code);
	}

	@Override
	public BlogArticleModel getNewestBlogArticleForProduct(String productCode) {
		List<BlogArticleModel> blogArticlesForProduct = blogArticleService.getBlogArticlesForProduct(productCode);
		if(CollectionUtils.isEmpty(blogArticlesForProduct)){
			LOG.warn("There isn`t any blog article related with selected product.");
			return null;
		}
		Optional<BlogArticleModel> article = blogArticlesForProduct.stream().sorted((e1, e2) -> e2.getPublishDate().compareTo(e1.getPublishDate())).findFirst();
		return article.get();
	}

	@Override
	public List<BlogArticleData> getBlogArticlesForPage(String pageID) {
		final List<BlogArticleModel> blogArticleModels = getBlogArticleService().getBlogArticlesForPage(pageID);
		return getProcessedBlogArticles(blogArticleModels);
	}

	/**
	 * @return the blogArticleConverter
	 */
	public Converter<BlogArticleModel, BlogArticleData> getBlogArticleConverter()
	{
		return blogArticleConverter;
	}

	/**
	 * @param blogArticleConverter
	 *           the blogArticleConverter to set
	 */
	public void setBlogArticleConverter(final Converter<BlogArticleModel, BlogArticleData> blogArticleConverter)
	{
		this.blogArticleConverter = blogArticleConverter;
	}

	@Override
	public List<BlogArticleData> getBlogArticlesData()
	{
		final List<BlogArticleModel> blogArticleModels = getBlogArticleService().findBlogArticles();
		return getProcessedBlogArticles(blogArticleModels);
	}

	private List<BlogArticleData> getProcessedBlogArticles(final List<BlogArticleModel> blogModels) {
		List<BlogArticleData> blogArticleData = new ArrayList<>();
		for (BlogArticleModel blogArticleModel : blogModels) {
			blogArticleData.add(blogArticleConverter.convert(blogArticleModel));
		}
		blogArticleData.sort((e1, e2) -> e2.getDate().compareTo(e1.getDate()));
		return blogArticleData;
	}

	/**
	 * @return the blogArticleService
	 */
	public BlogArticleService getBlogArticleService()
	{
		return blogArticleService;
	}

	/**
	 * @param blogArticleService
	 *           the blogArticleService to set
	 */
	public void setBlogArticleService(final BlogArticleService blogArticleService)
	{
		this.blogArticleService = blogArticleService;
	}

}
