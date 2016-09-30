package com.hybris.showcase.guidedselling.renderers.cms;

import com.hybris.showcase.data.ContractData;
import com.hybris.showcase.facades.ContractFacade;
import com.hybris.showcase.guidedselling.model.components.ContractTileAccountBodySubComponentModel;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.jsp.PageContext;
import java.util.Map;


public class ContractTileAccountBodySubComponentRenderer extends
		DefaultAddOnCMSComponentRenderer<ContractTileAccountBodySubComponentModel>
{

	private ContractFacade contractFacade;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext,
			final ContractTileAccountBodySubComponentModel component)
	{
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);

		variables.put("title", component.getTitle());
		variables.put("noContractMessage", component.getNoContractMessage());

		final ContractData contract = getContractFacade().getLatestContract();
		if (contract != null)
		{
			variables.put("noOfServices", contract.getNoOfServices());
			variables.put("targetUrl",
					String.format("/guidedselling/order/show/%s", contract.getCode()));
		}
		else
		{
			variables.put("noOfServices", 0);
			variables.put("targetUrl", String.format("/packages/%s", component.getBundleTemplate().getId()));
		}

		return variables;
	}

	protected ContractFacade getContractFacade()
	{
		return contractFacade;
	}

	@Required
	public void setContractFacade(final ContractFacade contractFacade)
	{
		this.contractFacade = contractFacade;
	}
}
