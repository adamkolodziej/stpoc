package com.hybris.showcase.facebookmock.controllers;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hybris.showcase.enums.ItemOfInterest;
import com.hybris.showcase.events.interaction.EventProps;
import com.hybris.showcase.events.interaction.InitiativeEvent;
import com.hybris.showcase.events.interaction.InteractionEvent;
import com.hybris.showcase.facebookmock.facades.LikeFacade;


/**
 * Created by mgolubovic on 23.4.2015..
 */
@Controller
public class FacebookMockController
{
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Resource(name = "timeService")
	private TimeService timeService;

	@Autowired
	private LikeFacade likeFacade;

	@Resource
	private EventService eventService;

	@RequestMapping(value = "/bigBangTheory")
	public String bigBangTheoryPage(@RequestParam(value = "userId", required = false) final String userId, final Model model)
	{
		setRelevantData(userId, model);
		return "bigBangTheory";
	}

	@RequestMapping(value = "/underworldtco")
	public String underworldtcoPage(@RequestParam(value = "userId", required = false) final String userId, final Model model)
	{
		setRelevantData(userId, model);
		return "underworldtco";
	}

	@RequestMapping(value = "/like/{wasLiked}")
	@ResponseBody
	public String like(@PathVariable final String wasLiked, final HttpServletRequest request)
	{
		final CustomerModel customer = (CustomerModel) userService.getUserForUID((String) sessionService.getAttribute("userId"));

		final String initiativeId = Config.getString("InitiativeId.FB", "Z1i");
		updateLocalCampaign(initiativeId, customer);

		//final String idOrigin = Config.getString("ContactIdOrigin.FB", "EMAIL");
		final String commMedium = Config.getString("CommunicationMedium.FB", "FB");
		final String interactionType = Config.getString("InteractionType.FB", "SOCIAL_POSTING");

		postInteraction(request, commMedium, interactionType, customer, ItemOfInterest.valueOf(wasLiked));

		return likeFacade.like(wasLiked);
	}


	@RequestMapping(method = RequestMethod.POST, value = "/session/time")
	@ResponseStatus(value = HttpStatus.OK)
	public void setCurrentTime(@RequestParam("currentTime") final long currentTimeMillis)
	{
		timeService.setCurrentTime(new Date(currentTimeMillis));
	}

	private void postInteraction(HttpServletRequest request, String commMedium, String interactionType, CustomerModel customer,
			ItemOfInterest itemOfInterest)
	{
		Map<String, Object> props = new HashMap<>();
		props.put(EventProps.INTERACTION_CUSTOMER_ID, customer.getUid());
		//props.put(EventProps.INTERACTION_ID_ORIGIN, idOrigin);
		props.put(EventProps.INTERACTION_COMM_MEDIUM, commMedium);
		//props.put(EventProps.INTERACTION_INITIATIVE_ID, initiativeId);
		props.put(EventProps.INTERACTION_INTERACTION_TYPE, interactionType);
		props.put(EventProps.INTERACTION_SOURCE_DATA_URL, request.getHeader("Referer"));
		props.put(EventProps.INTERACTION_SOURCE_OBJECT_ID, UUID.randomUUID().toString());
		props.put(EventProps.INTERACTION_CONTENT_TITLE, "Likes Branded Merchandise");
		props.put(EventProps.INTERACTION_CONTENT_DATA, "TCO Underworld Branded Merchandise");
		if (itemOfInterest != null)
		{
			props.put(EventProps.INTEREST_ITEM, itemOfInterest);
		}
		//props.put(EventProps.INTERACTION_VALUATION, 5);

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

	private void setRelevantData(final String userId, final Model model)
	{
		String userIdValue = "";

		if (null != userId)
		{
			userIdValue = userId;
		}
		else
		{
			userIdValue = "brown.joe.ny@gmail.com"; // TODO Maybe a local variable or controlpanel setting would be good here.
		}

		sessionService.setAttribute("userId", userIdValue);
		model.addAttribute("currentDate", timeService.getCurrentTime().getTime());
	}
}
