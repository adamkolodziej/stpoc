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
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.data.ContractData;
import com.hybris.showcase.facades.ContractFacade;


/**
 * Created by mgolubovic on 28.4.2015..
 */
public class TcoPromotionEmailContext extends AbstractEmailContext<StoreFrontCustomerProcessModel>
{
	private static final String TCO_PROMOTION = "tcoPromotion";
	private static final String TCO_PROMOTION_URL = "/guidedselling/edit/%s/focus/TriCast-TV-Addons/preselect/TCO";

	private SessionService sessionService;
	private ContractFacade contractFacade;

	@Override
	public void init(final StoreFrontCustomerProcessModel businessProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(businessProcessModel, emailPageModel);

		final ContractData contract = sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public Object execute()
			{
				return contractFacade.getLatestContract();
			}
		}, businessProcessModel.getCustomer());

		if (contract != null)
		{
			put(TCO_PROMOTION, String.format(TCO_PROMOTION_URL, contract.getCode()));
		}
		else
		{
			put(TCO_PROMOTION, "#");
		}
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

	public SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public ContractFacade getContractFacade()
	{
		return contractFacade;
	}

	@Required
	public void setContractFacade(final ContractFacade contractFacade)
	{
		this.contractFacade = contractFacade;
	}
}
