package com.hybris.showcase.guidedselling.services.impl;

import com.hybris.showcase.guidedselling.services.ApprovalService;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

/**
 * Created by admin on 05.10.2016.
 */
public class DefaultApprovalService implements ApprovalService {

    private ModelService modelService;
    private OrderService orderService;
    private UserService userService;
    private BaseStoreService baseStoreService;
    private CustomerAccountService customerAccountService;

    @Override
    public OrderModel approveQuote(String code) throws InvalidCartException {
        Assert.notNull(code);

        OrderModel order = getOrderDetailsForCode(code);
        updateStatus(order);
        modelService.save(order);
        orderService.submitOrder(order);
        return order;
    }

    private OrderModel getOrderDetailsForCode(final String code) {
        final BaseStoreModel baseStoreModel = getBaseStoreService().getCurrentBaseStore();
        OrderModel orderModel = getCustomerAccountService().getOrderForCode((CustomerModel) getUserService().getCurrentUser(), code,
                baseStoreModel);
        return orderModel;
    }

    private void updateStatus(OrderModel order) {
        order.setStatus(OrderStatus.APPROVED);
        modelService.save(order);
    }

    @Required
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    protected ModelService getModelService() {
        return modelService;
    }

    protected OrderService getOrderService() {
        return orderService;
    }

    @Required
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    protected UserService getUserService() {
        return userService;
    }

    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected BaseStoreService getBaseStoreService() {
        return baseStoreService;
    }

    @Required
    public void setBaseStoreService(BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }

    protected CustomerAccountService getCustomerAccountService() {
        return customerAccountService;
    }

    @Required
    public void setCustomerAccountService(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }
}
