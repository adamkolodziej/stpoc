/**
 *
 */
package com.hybris.showcase.guidedselling.controllers.pages.checkout.steps;

import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.CheckoutStep;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.storefront.controllers.pages.checkout.steps.AbstractCheckoutStepController;
import de.hybris.platform.yacceleratorstorefront.controllers.ControllerConstants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @author m.vidojkovic
 *
 */
@Controller
@RequestMapping(value = "/guidedsellingCheckoutStep")
public class GuidedsellingCheckoutStepController extends AbstractCheckoutStepController
{

	private final static String GUIDEDSELLING_CHECKOUT_STEP = "configure-packages";

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public String enterStep(final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException,
			CommerceCartModificationException
	{
		setCheckoutStepLinksForModel(model, getCheckoutStep());

		return ControllerConstants.Views.Fragments.CheckoutStep;
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	@Override
	public String back(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().previousStep();
	}

	@RequestMapping(value = "/next", method = RequestMethod.GET)
	@Override
	public String next(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().nextStep();
	}

	protected CheckoutStep getCheckoutStep()
	{
		return getCheckoutStep(GUIDEDSELLING_CHECKOUT_STEP);
	}

}
