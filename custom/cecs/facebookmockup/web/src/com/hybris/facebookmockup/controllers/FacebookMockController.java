package com.hybris.facebookmockup.controllers;


import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hybris.showcase.enums.ItemOfInterest;
import com.hybris.showcase.events.interaction.EventProps;
import com.hybris.showcase.events.interaction.InitiativeEvent;
import com.hybris.showcase.events.interaction.InteractionEvent;


@Controller
@RequestMapping(value = "/")
public class FacebookMockController
{
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Resource(name = "timeService")
	private TimeService timeService;

	@Resource
	private EventService eventService;

	@RequestMapping(method = RequestMethod.GET)
	public String mainPage(final Model model)
	{
		model.addAttribute("currentDate", timeService.getCurrentTime().getTime());
		model.addAttribute("customer", getCustomer());
		return "facebookMainPage";
	}

	@RequestMapping("/user/{userId:.*}")
	public String switchUser(@PathVariable("userId") final String userId)
	{
		sessionService.setAttribute("userId", userId);
		return "redirect:/";
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	@ResponseBody
	public String post(@RequestParam("messagePost") String message, @RequestParam("interestItem") String interest,
			final HttpServletRequest request)
	{
		final CustomerModel customer = getCustomer();

		final String commMedium = Config.getString("CommunicationMedium.FB", "FB");
		final String interactionType = Config.getString("InteractionType.FB", "SOCIAL_POSTING");
		final String sourceDataUrl = request.getHeader("Referer");
		final String contentTitle = customer.getName() + "' post";
		final String contentData = message;
		final String sourceObjectId = UUID.randomUUID().toString();
		final Integer valuation = null; // enable when needed: 5 means strong-positive, see more at http://help.sap.com/mkt

		final ItemOfInterest itemOfInterest = interest != null ? ItemOfInterest.valueOf(interest) : null;
		postInteraction(commMedium, interactionType, customer, itemOfInterest, sourceDataUrl, sourceObjectId, contentTitle,
				contentData, valuation);

		return message;
	}

	private CustomerModel getCustomer()
	{
		String userId = sessionService.getAttribute("userId");
		if (userId == null)
		{
			userId = "brown.joe.ny@gmail.com";
		}
		return (CustomerModel) userService.getUserForUID(userId);
	}


	@RequestMapping(value = "/like/{interest}")
	@ResponseBody
	public String like(@PathVariable final String interest, final HttpServletRequest request,
			@RequestParam(value = "contentTitle", required = false) final String contentTitle,
			@RequestParam(value = "contentData", required = false) final String contentData)
	{
		final CustomerModel customer = getCustomer();

		final String initiativeId = Config.getString("InitiativeId.FB", "Z1i");
		updateLocalCampaign(initiativeId, customer);

		final String commMedium = Config.getString("CommunicationMedium.FB", "FB");
		final String interactionType = Config.getString("InteractionType.FB", "SOCIAL_POSTING");
		final String sourceDataUrl = request.getHeader("Referer");
		final String sourceObjectId = UUID.randomUUID().toString();
		final Integer valuation = null; // 5

		final ItemOfInterest itemOfInterest = ItemOfInterest.valueOf(interest);
		postInteraction(commMedium, interactionType, customer, itemOfInterest, sourceDataUrl, sourceObjectId,
				contentTitle != null ? contentTitle : "Likes Branded Merchandise",
				contentData != null ? contentData : "TCO Underworld Branded Merchandise", valuation);

		return "true";
	}


	@RequestMapping(method = RequestMethod.POST, value = "/session/time")
	@ResponseStatus(value = HttpStatus.OK)
	public void setCurrentTime(@RequestParam("currentTime") final long currentTimeMillis)
	{
		timeService.setCurrentTime(new Date(currentTimeMillis));
	}

	private void postInteraction(String medium, String iaType, CustomerModel customer, ItemOfInterest itemOfInterest,
			String sourceDataUrl, String sourceObjectId, String contentTitle, String contentData, Integer valuation)
	{
		Map<String, Object> props = new HashMap<>();
		props.put(EventProps.INTERACTION_CUSTOMER_ID, customer.getUid());
		props.put(EventProps.INTERACTION_COMM_MEDIUM, medium);
		props.put(EventProps.INTERACTION_INTERACTION_TYPE, iaType);
		props.put(EventProps.INTERACTION_SOURCE_DATA_URL, sourceDataUrl);
		props.put(EventProps.INTERACTION_SOURCE_OBJECT_ID, sourceObjectId);
		props.put(EventProps.INTERACTION_CONTENT_TITLE, contentTitle);
		props.put(EventProps.INTERACTION_CONTENT_DATA, contentData);

		if (itemOfInterest != null)
		{
			props.put(EventProps.INTEREST_ITEM, itemOfInterest.getCode());
		}
		if (valuation != null)
		{
			props.put(EventProps.INTERACTION_VALUATION, valuation);
		}

		final InteractionEvent interactionEvent = new InteractionEvent((Serializable) props);
		eventService.publishEvent(interactionEvent);
	}

	private void updateLocalCampaign(String initiativeId, CustomerModel customer)
	{
		Map<String, Object> props = new HashMap<>();
		props.put(EventProps.CUSTOMER, customer);
		props.put(EventProps.INITIATIVE_CATEGORY_ID, initiativeId);

		final InitiativeEvent initiativeEvent = new InitiativeEvent((Serializable) props);
		eventService.publishEvent(initiativeEvent);
	}
}
