package com.hybris.showcase.guidedselling.services.impl;

import com.hybris.showcase.guidedselling.model.ApprovalUserGroupModel;
import com.hybris.showcase.guidedselling.services.ApprovalService;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by admin on 05.10.2016.
 */
public class DefaultApprovalService implements ApprovalService {

    private ModelService modelService;
    private OrderService orderService;
    private UserService userService;
    private BaseStoreService baseStoreService;
    private CustomerAccountService customerAccountService;
    private FlexibleSearchService flexibleSearchService;

    @Override
    public OrderModel approveQuote(String code) throws InvalidCartException {
        Assert.notNull(code);

        OrderModel order = getOrderDetailsForCode(code);
        updateStatus(order);
        modelService.save(order);
        orderService.submitOrder(order);
        return order;
    }

    //TODO
    @Override
    public List<OrderModel> getOrdersToApprove() {
        UserModel currentUser = userService.getCurrentUser();
        Set<ApprovalUserGroupModel> approvalGroups = userService.getAllUserGroupsForUser(currentUser, ApprovalUserGroupModel.class);
        if (CollectionUtils.isNotEmpty(approvalGroups)) {
            ApprovalUserGroupModel approvalGroup = approvalGroups.iterator().next();
            final Double rangeFrom = approvalGroup.getRangeFrom();
            final Double rangeTo = approvalGroup.getRangeTo();
            List<OrderModel> orders = getAllOrders();
            return orders.stream().filter(orderModel ->orderModel.getParent() == null &&
                    orderModel.getStatus().equals(OrderStatus.PENDING_QUOTE) &&
                    (rangeFrom == null || rangeFrom <= orderModel.getTotalPrice()) &&
                    (rangeTo == null || rangeTo > orderModel.getTotalPrice()))
                            .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public boolean isCurrentUserApprover() {
        UserModel currentUser = userService.getCurrentUser();
        Set<ApprovalUserGroupModel> approvalGroups = userService.getAllUserGroupsForUser(currentUser, ApprovalUserGroupModel.class);
        return CollectionUtils.isNotEmpty(approvalGroups);
    }

    protected List<OrderModel> getAllOrders() {
        FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery("SELECT pk FROM {Order}");
        return flexibleSearchService.<OrderModel>search(flexibleSearchQuery).getResult();
    }

    private OrderModel getOrderDetailsForCode(final String code) {
        FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery("SELECT pk FROM {Order} where {code} = ?code");
        flexibleSearchQuery.addQueryParameter("code", code);
        final SearchResult<OrderModel> searchResult = flexibleSearchService.search(flexibleSearchQuery);
        final List<OrderModel> result = searchResult.getResult();
        return result.isEmpty() ? null : result.get(0);
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

    protected FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    @Required
    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
