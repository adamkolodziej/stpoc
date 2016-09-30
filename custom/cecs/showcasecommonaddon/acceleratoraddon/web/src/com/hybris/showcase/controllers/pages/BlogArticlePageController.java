package com.hybris.showcase.controllers.pages;

import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hybris.showcase.dao.BlogArticleDao;
import com.hybris.showcase.events.interaction.EventProps;
import com.hybris.showcase.events.interaction.InitiativeEvent;
import com.hybris.showcase.events.interaction.InteractionEvent;
import com.hybris.showcase.model.components.BlogArticleModel;
import com.hybris.showcase.services.RestrictedContentPagesCMSPageService;


/**
 * Created by m.golubovic on 22.6.2015..
 */
@Controller
@RequestMapping("/blogArticle")
public class BlogArticlePageController extends AbstractAddOnPageController
{
	private static final String BLOG_DETAILS_PAGE_ID = "blogArticleDetailsPage";

	@Resource(name = "blogArticleDao")
	private BlogArticleDao blogArticleDao;

	@Resource(name = "restrictedContentPagesCMSPageService")
	private RestrictedContentPagesCMSPageService restrictedContentPagesCMSPageService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource
	private EventService eventService;

	@RequestMapping(value = "/{blogArticleCode}", method = RequestMethod.GET)
	public String blogArticlePage(final Model model, final HttpServletRequest request,
			@PathVariable("blogArticleCode") final String blogArticleCode) throws CMSItemNotFoundException
	{
		final BlogArticleModel blogArticleModel = blogArticleDao.getBlogArticleForCode(blogArticleCode);
		if (blogArticleModel != null)
		{
			getRequestContextData(request).setBlogArticle(blogArticleModel);
			getRequestContextData(request).setProduct(blogArticleModel.getProduct());
		}

		final ContentPageModel page = getContentPageForLabel(BLOG_DETAILS_PAGE_ID, getRequestContextData(request));
		storeCmsPageInModel(model, page);
		setUpMetaDataForContentPage(model, page);


		final CustomerModel customer = (CustomerModel) userService.getCurrentUser();

		final String initiativeId = Config.getString("InitiativeId.BLOG", "Z2i");
		updateLocalCampaign(initiativeId, customer);

		//final String idOrigin = Config.getString("ContactIdOrigin.BLOG", "EMAIL");
		final String commMedium = Config.getString("CommunicationMedium.BLOG", "WEB");
		final String interactionType = Config.getString("InteractionType.BLOG", "CLICK_THROUGH");

		postInteraction(request, customer, commMedium, interactionType, blogArticleModel);

		return getViewForPage(model);
	}

	private void postInteraction(final HttpServletRequest request, final CustomerModel customer, final String commMedium,
			final String interactionType, final BlogArticleModel blogArticleModel)
	{
		final ProductModel product = blogArticleModel.getProduct();

		String contentTitle = null;
		String itemOfInterest = "-";
		if (blogArticleModel.getItemOfInterest() != null && blogArticleModel.getItemOfInterest().getCode() != null)
		{
			itemOfInterest = blogArticleModel.getItemOfInterest().getCode().toUpperCase();
		}

		if (product != null)
		{
			contentTitle = product.getName() + " Blog: " + blogArticleModel.getTitle();
		}

		final Map<String, Object> props = new HashMap<>();
		props.put(EventProps.INTERACTION_CUSTOMER_ID, customer.getUid());
		//props.put(EventProps.INTERACTION_ID_ORIGIN, idOrigin);
		props.put(EventProps.INTERACTION_COMM_MEDIUM, commMedium);
		// props.put(EventProps.INTERACTION_INITIATIVE_ID, initiativeId);
		props.put(EventProps.INTERACTION_INTERACTION_TYPE, interactionType);
		props.put(EventProps.INTERACTION_SOURCE_DATA_URL, request.getHeader("Referer"));
		props.put(EventProps.INTERACTION_SOURCE_OBJECT_ID, blogArticleModel.getCode());
		props.put(EventProps.INTERACTION_CONTENT_TITLE, contentTitle);
		props.put(EventProps.INTERACTION_CONTENT_DATA, blogArticleModel.getSummary());
		props.put(EventProps.INTEREST_ITEM, itemOfInterest);
		//props.put(EventProps.INTERACTION_VALUATION, 5);

		final InteractionEvent interactionEvent = new InteractionEvent((Serializable) props);
		eventService.publishEvent(interactionEvent);
	}

	private void updateLocalCampaign(final String initiativeId, final CustomerModel customer)
	{
		final Map<String, Object> props = new HashMap<>();
		props.put(EventProps.CUSTOMER, customer);
		props.put(EventProps.INITIATIVE_CATEGORY_ID, initiativeId);

		final InitiativeEvent initiativeEvent = new InitiativeEvent((Serializable) props);
		eventService.publishEvent(initiativeEvent);
	}

	protected ContentPageModel getContentPageForLabel(final String label, final RequestContextData requestContextData)
			throws CMSItemNotFoundException
	{
		return restrictedContentPagesCMSPageService.getPageForLabel(label, requestContextData);
	}
}
