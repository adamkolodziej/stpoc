/**
 *
 */
package com.hybris.showcase.controllers.cms;

import com.hybris.showcase.data.BlogArticleData;
import com.hybris.showcase.facades.BlogArticleFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author i327223
 *
 */
@Controller
public class BlogPageController
{
	private static final String BLOG_ARTICLES_PAGE_REQUESTPATH = "/blog/all/{pageID}";

	@Resource
	private BlogArticleFacade blogArticleFacade;

	@RequestMapping(value = BLOG_ARTICLES_PAGE_REQUESTPATH, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<BlogArticleData> blogArticles(@PathVariable("pageID") final String blogPageID)
	{
		return blogArticleFacade.getBlogArticlesForPage(blogPageID);
	}
}
