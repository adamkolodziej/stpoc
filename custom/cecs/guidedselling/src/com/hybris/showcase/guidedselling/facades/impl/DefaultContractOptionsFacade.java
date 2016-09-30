package com.hybris.showcase.guidedselling.facades.impl;

import com.hybris.showcase.guidedselling.facades.ContractOptionsFacade;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;

/**
 * @author Sebastian Weiner on 2016-02-01.
 */
public class DefaultContractOptionsFacade implements ContractOptionsFacade{

    private TimeService timeService;
    private CartService cartService;
    private ModelService modelService;

    @Override
    public boolean isStartDateSetForCart()
    {
        return getCartService().getSessionCart().getContractStartDate() != null;
    }

    @Override
    public Date getContractStartDate()
    {
        return getCartService().getSessionCart().getContractStartDate();
    }

    @Override
    public Date initContractStartDate()
    {
        final Date currentTime = getTimeService().getCurrentTime();

        final CartModel cart = getCartService().getSessionCart();
        cart.setContractStartDate(currentTime);
        modelService.save(cart);

        return currentTime;
    }

    @Override
    public void changeContractStartDate(final Date startDate)
    {
        final CartModel cart = getCartService().getSessionCart();
        cart.setContractStartDate(startDate);
        modelService.save(cart);
    }

    protected TimeService getTimeService()
    {
        return timeService;
    }

    @Required
    public void setTimeService(final TimeService timeService)
    {
        this.timeService = timeService;
    }

    protected CartService getCartService()
    {
        return cartService;
    }

    @Required
    public void setCartService(final CartService cartService)
    {
        this.cartService = cartService;
    }

    protected ModelService getModelService()
    {
        return modelService;
    }

    @Required
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }
}
