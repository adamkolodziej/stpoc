package com.hybris.productlists.component.renderer;

import com.hybris.cms.productlists.model.components.ProductListsAccountTileComponentModel;
import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.facades.ProductListsFacade;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.commercefacades.product.ProductOption;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.jsp.PageContext;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class ProductListsAccountTileComponentRenderer extends
		DefaultAddOnCMSComponentRenderer<ProductListsAccountTileComponentModel>
{

	private ProductListsFacade productListsFacade;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext,
			final ProductListsAccountTileComponentModel component)
	{
		final Map<String, Object> variablesToExpose = super.getVariablesToExpose(pageContext, component);
		final List<ProductListData> allProductLists = getProductListsFacade().getAllSessionProductLists(Collections
				.<ProductOption> emptyList());
		variablesToExpose.put("numberOfLists", allProductLists.size());
		return variablesToExpose;
	}

	public ProductListsFacade getProductListsFacade() {
		return productListsFacade;
	}

	@Required
	public void setProductListsFacade(final ProductListsFacade productListsFacade) {
		this.productListsFacade = productListsFacade;
	}
}
