/**
 * 
 */
package com.hybris.showcase.cecs.ymarketingintegration.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author npavlovic
 * 
 */
public class InterestedInSportsEmailContext extends AbstractEmailContext<StoreFrontCustomerProcessModel>
{
	private static final String INTERESTED_IN_SPORTS = "interestedInSports";
	private static final String INTERESTED_IN_SPORTS_URL = "guidedselling/instantEdit?productCode=Sport_trial&bundleTemplateId=TriCast-TV-Addons&bundleNo=1&removeOthers=true";

	@Override
	public void init(final StoreFrontCustomerProcessModel businessProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(businessProcessModel, emailPageModel);
		// Promotion for interested in sports customers - put static url into e-mail context
		put(INTERESTED_IN_SPORTS, INTERESTED_IN_SPORTS_URL);
	}

	@Override
	protected BaseSiteModel getSite(final StoreFrontCustomerProcessModel businessProcessModel)
	{
		return businessProcessModel.getSite();
	}

	@Override
	protected CustomerModel getCustomer(final StoreFrontCustomerProcessModel businessProcessModel)
	{
		return businessProcessModel.getCustomer();
	}

	@Override
	protected LanguageModel getEmailLanguage(final StoreFrontCustomerProcessModel businessProcessModel)
	{
		return businessProcessModel.getLanguage();
	}
}
