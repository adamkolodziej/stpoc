/**
 * 
 */
package de.hybris.platform.yacceleratorstorefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.OrderFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.subscriptionfacades.SubscriptionFacade;
import de.hybris.platform.subscriptionfacades.action.SubscriptionUpdateActionEnum;
import de.hybris.platform.subscriptionfacades.data.SubscriptionBillingData;
import de.hybris.platform.subscriptionfacades.data.SubscriptionData;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;
import de.hybris.platform.yacceleratorstorefront.controllers.ControllerConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @author npavlovic
 * 
 *         CECS-113: Migrate my-account/subscription/{subscriptionId} page from telco
 */
@Controller
@Scope("tenant")
@RequestMapping("/my-account")
public class AccountSubscriptionPageController extends AbstractSearchPageController
{
	private static final String REDIRECT_MY_ACCOUNT = REDIRECT_PREFIX + "/my-account";
	private static final String SUBSCRIPTION_ID_PATH_VARIABLE_PATTERN = "{subscriptionId:.*}";
	private static final String SUBSCRIPTION_DETAILS_CMS_PAGE = "subscription";
	private static final String REDIRECT_MY_ACCOUNT_SUBSCRIPTION = REDIRECT_PREFIX + "/my-account/subscription/";
	private static final String SUBSCRIPTION_BILLING_ACTIVITY_CMS_PAGE = "subscriptionBillingActivity";
	// CECS-111: Migrate my-account subscriptions page from telco
	private static final String SUBSCRIPTIONS_CMS_PAGE = "subscriptions";
	
	@Resource(name = "userFacade")
	protected UserFacade userFacade;

	@Resource(name = "subscriptionFacade")
	private SubscriptionFacade subscriptionFacade;

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	// CECS-111: Migrate my-account subscriptions page from telco
	@RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
	@RequireHardLogIn
	public String subscriptions(final Model model) throws CMSItemNotFoundException
	{
		List<SubscriptionData> sortedSubscriptions = new ArrayList<SubscriptionData>();

		try
		{
			final Collection<SubscriptionData> subscriptions = subscriptionFacade.getSubscriptions();

			if (subscriptions != null)
			{
				sortedSubscriptions = new ArrayList<SubscriptionData>(subscriptions);
			}
		}
		catch (final SubscriptionFacadeException e)
		{
			LOG.error("Error while retrieving subscriptions", e);
		}

		final List<CCPaymentInfoData> paymentInfoData = userFacade.getCCPaymentInfos(true);

		final Map<String, CCPaymentInfoData> paymentInfoMap = new HashMap<>();

		for (final CCPaymentInfoData paymentInfo : paymentInfoData)
		{
			paymentInfoMap.put(paymentInfo.getSubscriptionId(), paymentInfo);
		}

		storeCmsPageInModel(model, getContentPageForLabelOrId(SUBSCRIPTIONS_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SUBSCRIPTIONS_CMS_PAGE));
		model.addAttribute("subscriptionFacade", subscriptionFacade);
		model.addAttribute("paymentInfoMap", paymentInfoMap);
		model.addAttribute("subscriptions", sortedSubscriptions);
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.subscriptions"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		return getViewForPage(model);
	}

	@RequestMapping(value = "/subscription/" + SUBSCRIPTION_ID_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String subscription(@PathVariable("subscriptionId") final String subscriptionId, final Model model)
			throws CMSItemNotFoundException
	{
		try
		{
			model.addAttribute("paymentInfos", userFacade.getCCPaymentInfos(true));

			final SubscriptionData subscriptionDetails = subscriptionFacade.getSubscription(subscriptionId);
			model.addAttribute("subscriptionData", subscriptionDetails);

			final ProductData subscriptionProductData = getProductForSubscription(subscriptionDetails);
			model.addAttribute("subscriptionProductData", subscriptionProductData);

			final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
			breadcrumbs.add(new Breadcrumb("/my-account/subscriptions", getMessageSource().getMessage("text.account.subscriptions",
					null, getI18nService().getCurrentLocale()), null));
			breadcrumbs.add(new Breadcrumb("#", getMessageSource().getMessage("text.account.subscription.subscriptionBreadcrumb",
					new Object[]
					{ subscriptionDetails.getId() }, "Subscription {0}", getI18nService().getCurrentLocale()), null));
			model.addAttribute("breadcrumbs", breadcrumbs);

			final List<CCPaymentInfoData> paymentMethods = userFacade.getCCPaymentInfos(true);
			model.addAttribute("paymentInfos", paymentMethods);

			final List<ProductData> upsellingOptions = subscriptionFacade.getUpsellingOptionsForSubscription(subscriptionDetails
					.getProductCode());
			model.addAttribute("upgradable", Boolean.valueOf(CollectionUtils.isNotEmpty(upsellingOptions)));
		}
		catch (final UnknownIdentifierException | SubscriptionFacadeException e)
		{
			LOG.warn("Attempted to load a subscription that does not exist or is not visible", e);
			return REDIRECT_MY_ACCOUNT;
		}

		storeCmsPageInModel(model, getContentPageForLabelOrId(SUBSCRIPTION_DETAILS_CMS_PAGE));
		model.addAttribute("metaRobots", "no-index,no-follow");
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SUBSCRIPTION_DETAILS_CMS_PAGE));
		return getViewForPage(model);
	}

	private ProductData getProductForSubscription(final SubscriptionData subscriptionDetails)
	{
		try
		{
			final OrderData subscriptionOrderData = orderFacade.getOrderDetailsForCode(subscriptionDetails.getOrderNumber());
			final OrderData orderData = orderFacade.getOrderDetailsForCode(subscriptionOrderData.getCode());
			ProductData productData = null;

			for (final OrderEntryData orderEntry : orderData.getEntries())
			{
				if (orderEntry.getEntryNumber().intValue() == subscriptionDetails.getOrderEntryNumber().intValue())
				{
					productData = orderEntry.getProduct();
					final ProductData productExist = productFacade.getProductForCodeAndOptions(productData.getCode(), null);
					if (productExist != null)
					{
						return productData;
					}
				}
			}
		}
		catch (final ModelNotFoundException e)
		{
			LOG.warn("Attempted to load an order that does not exist", e);
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.warn("Attempted to load a product that does not exist", e);
		}

		return null;
	}

	@RequestMapping(value = "/changeSubscriptionState", method = RequestMethod.POST)
	@RequireHardLogIn
	public String changeSubscriptionState(@RequestParam(value = "newState", required = true) final String newState,
			@RequestParam(value = "subscriptionId", required = true) final String subscriptionId,
			final RedirectAttributes redirectAttributes)
	{
		try
		{
			subscriptionFacade.changeSubscriptionState(subscriptionId, newState, MapUtils.EMPTY_MAP);
			redirectAttributes.addFlashAttribute(GlobalMessages.CONF_MESSAGES_HOLDER,
					Collections.singletonList("text.account.subscription.changeState.success"));
		}
		catch (final SubscriptionFacadeException e)
		{
			redirectAttributes.addFlashAttribute(GlobalMessages.ERROR_MESSAGES_HOLDER,
					Collections.singletonList("text.account.subscription.changeState.unable"));
			LOG.error(String.format("Unable to change state to '%s' for subscription '%s'", newState, subscriptionId), e);
		}

		return REDIRECT_MY_ACCOUNT_SUBSCRIPTION + subscriptionId;
	}

	@RequestMapping(value = "/cancelsubscription/" + SUBSCRIPTION_ID_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String cancelsubscription(@PathVariable(value = "subscriptionId") final String subscriptionId,
			final RedirectAttributes redirectAttributes)
	{
		try
		{
			subscriptionFacade.updateSubscription(subscriptionId, true, SubscriptionUpdateActionEnum.CANCEL, null);
		}
		catch (final SubscriptionFacadeException e)
		{
			redirectAttributes.addFlashAttribute(GlobalMessages.ERROR_MESSAGES_HOLDER,
					Collections.singletonList("text.account.subscription.cancel.unable"));
			LOG.error("Unable to cancel subscription", e);
		}

		return REDIRECT_MY_ACCOUNT_SUBSCRIPTION + subscriptionId;
	}

	@RequestMapping(value = "/setAutorenewalStatus", method = RequestMethod.POST)
	@RequireHardLogIn
	public String setAutorenewalStatus(@RequestParam(value = "autorenew", required = true) final boolean autorenew,
			@RequestParam(value = "subscriptionId", required = true) final String subscriptionId,
			final RedirectAttributes redirectAttributes)
	{
		try
		{
			subscriptionFacade.updateSubscriptionAutorenewal(subscriptionId, autorenew, MapUtils.EMPTY_MAP);
			redirectAttributes.addFlashAttribute(GlobalMessages.CONF_MESSAGES_HOLDER,
					Collections.singletonList("text.account.subscription.changeAutorenew.success"));
		}
		catch (final SubscriptionFacadeException e)
		{
			redirectAttributes.addFlashAttribute(GlobalMessages.ERROR_MESSAGES_HOLDER,
					Collections.singletonList("text.account.subscription.changeAutorenew.unable"));
			LOG.error(String.format("Unable to change auto-renew status to '%s' for subscription '%s'", String.valueOf(autorenew),
					subscriptionId), e);
		}

		return REDIRECT_MY_ACCOUNT_SUBSCRIPTION + subscriptionId;
	}

	@RequestMapping(value = "/replaceSubscriptionPaymentMethod", method = RequestMethod.POST)
	@RequireHardLogIn
	public String replaceSubscriptionPaymentMethod(
			@RequestParam(value = "paymentMethodId", required = true) final String paymentMethodId,
			@RequestParam(value = "subscriptionId", required = true) final String subscriptionId,
			@RequestParam(value = "effectiveFrom", required = true) final String effectiveFrom,
			final RedirectAttributes redirectAttributes)
	{
		try
		{
			subscriptionFacade.replacePaymentMethod(subscriptionId, paymentMethodId, effectiveFrom, MapUtils.EMPTY_MAP);
			redirectAttributes.addFlashAttribute(GlobalMessages.CONF_MESSAGES_HOLDER,
					Collections.singletonList("text.account.subscription.replacePaymentMethod.success"));
		}
		catch (final SubscriptionFacadeException e)
		{
			redirectAttributes.addFlashAttribute(GlobalMessages.ERROR_MESSAGES_HOLDER,
					Collections.singletonList("text.account.subscription.replacePaymentMethod.unable"));
			LOG.error(String.format("Unable to replace payment method for subscription '%s'", subscriptionId), e);
		}

		return REDIRECT_MY_ACCOUNT_SUBSCRIPTION + subscriptionId;
	}

	@RequestMapping(value = "/subscriptionBillingActivity", method = RequestMethod.GET)
	public String viewSubscriptionBillingActivity(
			@RequestParam(value = "subscriptionId", required = true) final String subscriptionId, final Model model)
			throws CMSItemNotFoundException
	{
		try
		{
			// retrieve subscription
			final SubscriptionData subscriptionData = subscriptionFacade.getSubscription(subscriptionId);
			model.addAttribute("subscriptionData", subscriptionData);

			// create breadcrumbs
			final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
			breadcrumbs.add(new Breadcrumb("/my-account/subscriptions", getMessageSource().getMessage("text.account.subscriptions",
					null, getI18nService().getCurrentLocale()), null));
			breadcrumbs.add(new Breadcrumb("/my-account/subscription/" + subscriptionData.getId(), getMessageSource().getMessage(
					"text.account.subscription.subscriptionBreadcrumb", new Object[]
					{ subscriptionData.getId() }, "Subscription {0}", getI18nService().getCurrentLocale()), null));
			model.addAttribute("breadcrumbs", breadcrumbs);

			// retrieve and sort billing activities
			final List<SubscriptionBillingData> billingActivities = subscriptionFacade.getBillingActivityList(subscriptionId,
					MapUtils.EMPTY_MAP);

			Collections.sort(billingActivities, new Comparator<SubscriptionBillingData>()
			{
				@Override
				public int compare(final SubscriptionBillingData billingData1, final SubscriptionBillingData billingData2)
				{
					final DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
					try
					{
						final Date date1 = dateFormat.parse(billingData1.getBillingDate());
						final Date date2 = dateFormat.parse(billingData2.getBillingDate());
						return date1.compareTo(date2);
					}
					catch (final ParseException e)
					{
						LOG.warn("Unable to parse billing date. Billing activities will probably not be sorted properly", e);
						return 1;
					}
				}
			});

			model.addAttribute("billingActivities", billingActivities);
		}
		catch (final SubscriptionFacadeException e)
		{
			LOG.warn("Viewing billing activities for subscriptions failed. Returning to subscription details page.", e);
			return REDIRECT_MY_ACCOUNT_SUBSCRIPTION + subscriptionId;
		}

		storeCmsPageInModel(model, getContentPageForLabelOrId(SUBSCRIPTION_BILLING_ACTIVITY_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SUBSCRIPTION_BILLING_ACTIVITY_CMS_PAGE));
		model.addAttribute("metaRobots", "no-index,no-follow");
		return ControllerConstants.Views.Pages.Account.AccountSubscriptionBillingActivityPage;
	}

}
