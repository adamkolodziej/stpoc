package com.hybris.showcase.facades;

import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.List;

/**
 * Created by miroslaw.szot@sap.com on 2016-04-18.
 */
public interface ChannelsFacade {
    List<ProductData> getChannels(List<ProductOption> productOptions);
    List<ProductData> getChannels(ProductOption... productOptions);
}
