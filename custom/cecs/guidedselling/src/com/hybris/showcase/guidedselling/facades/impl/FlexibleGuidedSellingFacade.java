/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.hybris.showcase.guidedselling.facades.impl;

import de.hybris.platform.b2ctelcofacades.bundle.impl.DefaultGuidedSellingFacade;
import de.hybris.platform.b2ctelcofacades.data.BundleTabData;
import de.hybris.platform.b2ctelcoservices.model.DeviceModel;
import de.hybris.platform.b2ctelcoservices.model.ServiceAddOnModel;
import de.hybris.platform.b2ctelcoservices.model.ServicePlanModel;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.configurablebundlefacades.order.BundleCartFacade;
import de.hybris.platform.configurablebundleservices.model.AutoPickBundleSelectionCriteriaModel;
import de.hybris.platform.configurablebundleservices.model.BundleSelectionCriteriaModel;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.InstanceofPredicate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.hybris.showcase.guidedselling.data.*;
import com.hybris.showcase.guidedselling.enums.BundleComponentType;
import com.hybris.showcase.guidedselling.facades.OnePageGuidedSellingFacade;
import com.hybris.showcase.guidedselling.model.BundlePackageModel;
import com.hybris.showcase.guidedselling.services.BundlePackageService;
import com.hybris.showcase.guidedselling.services.FlexibleBundleTemplateService;
import com.hybris.showcase.guidedselling.services.OrderChangesUnitOfWork;
import com.hybris.showcase.loyaltypoints.services.LoyaltyPointsService;


/**
 * 
 * @author miroslaw.szot
 */
public class FlexibleGuidedSellingFacade extends DefaultGuidedSellingFacade implements OnePageGuidedSellingFacade
{
	private static final Logger LOG = Logger.getLogger(FlexibleGuidedSellingFacade.class);

	private ProductFacade productFacade;
    private Converter<BundlePackageModel, BundlePackageData> bundlePackageConverter;
    private BundleCartFacade cartFacade;
    private BundlePackageService bundlePackageService;
    private Converter<BundleOfferPopulatorParameters, BundleOfferData> bundleOfferConverter;
    private CustomerAccountService customerAccountService;
    private BaseStoreService baseStoreService;
    private OrderChangesUnitOfWork orderChangesUnitOfWork;
    private LoyaltyPointsService loyaltyPointsService;

	@Override
	public String getComponentProductType(final String bundleTemplateId)
	{
		final BundleTemplateModel bundleModel = getBundleTemplateService().getBundleTemplateForCode(bundleTemplateId);
		if (CollectionUtils.isNotEmpty(bundleModel.getProducts()))
		{
			final BundleComponentType bundleComponentType = bundleModel.getBundleComponentType();
			if (bundleComponentType != null)
			{
				switch (bundleComponentType)
				{
					case TARGET:
						return DeviceModel._TYPECODE;
					case CONDITIONAL:
						return ServicePlanModel._TYPECODE;
					case ADDON:
						return ServiceAddOnModel._TYPECODE;
					default:
						LOG.warn("Unsupported component type for bundle: " + bundleTemplateId + " re-trying with telco implementation");
				}
			}
		}

		final String componentProductType = super.getComponentProductType(bundleTemplateId);

		return componentProductType;
	}

	@Override
	public List<BundleTabData> getBundleTabs(final String bundleTemplateId)
	{
		final BundleTemplateModel bundleTemplateModel = getBundleTemplateService().getBundleTemplateForCode(bundleTemplateId);

		final FlexibleBundleTemplateService bundleTemplateService = (FlexibleBundleTemplateService) getBundleTemplateService();

		final BundleTemplateModel firstComponentModel = bundleTemplateService
				.getAllComponentsOfType(bundleTemplateModel, EnumSet.of(BundleComponentType.CONDITIONAL)).iterator().next();
		final ServicePlanModel firstPlan = (ServicePlanModel) firstComponentModel.getProducts().iterator().next();

		final ProductData productData = productFacade.getProductForOptions(firstPlan, Arrays.asList(ProductOption.BASIC,
                ProductOption.PRICE, ProductOption.SUMMARY, ProductOption.DESCRIPTION, ProductOption.GALLERY,
                ProductOption.CATEGORIES, ProductOption.REVIEW, ProductOption.PROMOTIONS, ProductOption.CLASSIFICATION,
                ProductOption.VARIANT_FULL, ProductOption.STOCK, ProductOption.SERVICE_PLAN_BUNDLE_TABS));

		// change selected package to the one that is passed as a parameter
		final List<BundleTabData> bundleTabs = productData.getBundleTabs();

		return bundleTabs;
	}

	@Override
	public BundleOfferData getBundleOffer(final int bundleNo)
	{
        final CartModel cart = getCartService().getSessionCart();
		return getBundleOffer(cart, bundleNo);
	}

    @Override
    public BundleOfferData getBundleOffer(final String orderCode, final int bundleNo) {
        final BaseStoreModel baseStore = baseStoreService.getCurrentBaseStore();
        final OrderModel order = customerAccountService.getOrderForCode(orderCode, baseStore);
        return getBundleOffer(order, bundleNo);
    }

    protected BundleOfferData getBundleOffer(final AbstractOrderModel order, final int bundleNo) {
        final BundleOfferPopulatorParameters parameters = new BundleOfferPopulatorParameters();
        parameters.setOrder(order);
        parameters.setBundleNo(bundleNo);

        final BundleOfferData offerData = bundleOfferConverter.convert(parameters);
        return offerData;
    }

    // CECS-148: Packages Page
    @Override
    public List<BundlePackageData> getPackages(final String bundleTemplateId) {
        final BundleTemplateModel bundleTemplateModel = getBundleTemplateService().getBundleTemplateForCode(bundleTemplateId);
        final BundleTemplateModel rootBundleTemplate = getBundleTemplateService().getRootBundleTemplate(bundleTemplateModel);
        final List<BundlePackageModel> packages = rootBundleTemplate.getPackages();
        final List<BundlePackageData> bundlePackageDatas = Converters.convertAll(packages, getBundlePackageConverter());
        return bundlePackageDatas;
    }

    // CECS-148: Packages Page

    // CECS-213 Page template fix and basic components
    public BundlePackageData getPackageByCode(final String packageCode)
    {
        Assert.notNull(packageCode);
        final BundlePackageModel packageModel = bundlePackageService.getPackageByCode(packageCode);
        return getBundlePackageConverter().convert(packageModel);
    }
    // CECS-213 Page template fix and basic components

    @Override
    public List<ProductData> getConditionalProducts(final String bundleTemplateId) {
        final List<ProductOption> options = Arrays.asList(ProductOption.BASIC, ProductOption.DESCRIPTION);
        return getConditionalProducts(bundleTemplateId, options);
    }

    @Override
    public List<ProductData> getConditionalProducts(String bundleTemplateId, List<ProductOption> options) {
        final BundleTemplateModel bundleTemplateModel = getBundleTemplateService().getBundleTemplateForCode(bundleTemplateId);
        final BundleTemplateModel rootBundleTemplate = getBundleTemplateService().getRootBundleTemplate(bundleTemplateModel);


        final FlexibleBundleTemplateService bundleTemplateService = (FlexibleBundleTemplateService) getBundleTemplateService();
        final List<BundleTemplateModel> conditionalComponents = bundleTemplateService.getAllComponentsOfType(rootBundleTemplate, EnumSet.of(BundleComponentType.CONDITIONAL));
        final List<ProductData> productDataList = new ArrayList<>();

        // there should be only one
        for (final BundleTemplateModel conditionalComponent : conditionalComponents) {
            final List<ProductModel> products = conditionalComponent.getProducts();
            for (final ProductModel productModel : products) {
                final ProductData productData = productFacade.getProductForOptions(productModel, options);
                productDataList.add(productData);
            }
        }
        return productDataList;
    }

    @Override
    public BundleOfferData startWithPackage(final String bundleTemplateId, final String packageCode) {
        final BundlePackageModel packageModel = bundlePackageService.getPackageByCode(packageCode);

        final Predicate servicePlanOnly = new Predicate() {
            @Override
            public boolean evaluate(final Object o) {
                return o instanceof ServicePlanModel;
            }
        };

        int bundleNo = -1;
        final Set<ProductModel> selectedProducts = new HashSet<>(packageModel.getSelectedProducts());
        final List<ConfigMessage> messages = new ArrayList<>();
        final Map<String,List<ConfigMessage>> componentMessageMap = new HashMap<>();

        for (final BundleTemplateModel childTemplate : packageModel.getBundleTemplate().getChildTemplates()) {
            final BundleSelectionCriteriaModel selectionCriteria = childTemplate.getBundleSelectionCriteria();
            if(selectionCriteria instanceof AutoPickBundleSelectionCriteriaModel ) {
                // ingore auto-pick components
                continue;
            }

            final Set<ProductModel> preselectedProducts = new HashSet<>(selectedProducts);
            preselectedProducts.retainAll(childTemplate.getProducts());

            final BundleComponentType componentType = childTemplate.getBundleComponentType();

            final List<ConfigMessage> componentMessages = new ArrayList<>();
            if( componentType == BundleComponentType.CONDITIONAL )  {
                final ProductModel product = (ProductModel) CollectionUtils.find(preselectedProducts, servicePlanOnly);
                bundleNo = addToCart(bundleNo, childTemplate, product, componentMessages);
            } else {
                for (final ProductModel product : preselectedProducts) {
                    bundleNo = addToCart(bundleNo, childTemplate, product, componentMessages);
                }
            }
            if(CollectionUtils.isNotEmpty(componentMessages))
            {
                componentMessageMap.put(childTemplate.getId(), componentMessages);
                messages.addAll(componentMessages);
            }
        }

        final CartModel cart = getCartService().getSessionCart();
        bundlePackageService.setPackageOnCartEntries(packageModel, cart, selectedProducts);

        final BundleOfferData offer = getBundleOffer(bundleNo);
        for(final String componentId : componentMessageMap.keySet())
        {
            final List<ConfigMessage> componentMessages = componentMessageMap.get(componentId);
            addMessagesForComponent(componentMessages, componentId, offer);
        }
        offer.getMessages().addAll(messages);

        return offer;
    }

    protected int addToCart(int bundleNo, final BundleTemplateModel childTemplate, final ProductModel product, final List<ConfigMessage> configMessages) {
        try {
            final List<CartModificationData> cartModifications = cartFacade.addToCart(product.getCode(), 1, bundleNo, childTemplate.getId(), false);
            bundleNo = cartModifications.get(0).getEntry().getBundleNo();
        } catch ( final CommerceCartModificationException e ) {
            LOG.error(e.getMessage(), e);
            final ConfigMessage msg = new ConfigMessage();
            msg.setSeverity(Severity.ERROR);
            msg.setContent(e.getMessage());
            configMessages.add(msg);
        }
        return bundleNo;
    }

    @Override
    public BundleOfferData startWithConditionalProduct(final String bundleTemplateId, final String productCode, final int bundleNo) throws CommerceCartModificationException {
        final FlexibleBundleTemplateService bundleTemplateService = (FlexibleBundleTemplateService) getBundleTemplateService();
        final BundleTemplateModel bundleTemplateModel = bundleTemplateService.getBundleTemplateForCode(bundleTemplateId);
        String componentId = bundleTemplateId;
        if( bundleTemplateModel.getParentTemplate() == null ) {
            final List<BundleTemplateModel> conditionalComponents = bundleTemplateService.getAllComponentsOfType(bundleTemplateModel, EnumSet.of(BundleComponentType.CONDITIONAL));
            componentId = conditionalComponents.iterator().next().getId();
        }

        return chooseProduct(componentId, productCode, bundleNo);
    }

    @Override
    public BundleOfferData chooseProduct(final String bundleTemplateId, final String productCode, int bundleNo) throws CommerceCartModificationException {
		final List<CartModificationData> cartModifications = cartFacade.addToCart(productCode, 1, bundleNo, bundleTemplateId, true);

		bundleNo = cartModifications.get(0).getEntry().getBundleNo();

		final BundleOfferData offerData = getBundleOffer(bundleNo);
        return offerData;
    }


    @Override
    public boolean canReusePackageOffer(final String bundleTemplateId, final String packageCode) {
        final List<AbstractOrderEntryModel> entries = getCartService().getSessionCart().getEntries();
        if( entries.isEmpty() ) {
            return false;
        }

        final Set<String> rootBundles = new HashSet<>();
        String bundlePackageCode = null;
        for (final AbstractOrderEntryModel entry : entries) {
            final BundleTemplateModel bundleTemplate = entry.getBundleTemplate();

            if( bundleTemplate == null ) {
                return false; // non-bundle entry inside (standalone)
            }

            final BundlePackageModel pkg = entry.getPackage();
            if( pkg != null ) {
                if( ! pkg.getCode().equals(packageCode) ) {
                   return false; // different package
                } else {
                    bundlePackageCode = pkg.getCode();
                }
            }

            final BundleTemplateModel rootBundle = getBundleTemplateService().getRootBundleTemplate(bundleTemplate);
            rootBundles.add(rootBundle.getId());
        }

        final boolean sameBundleTemplate = rootBundles.iterator().next().equals(bundleTemplateId);
        final boolean oneBundleInside = rootBundles.size() == 1;
        final boolean createdFromPackage = bundlePackageCode != null;

        if( ! oneBundleInside ) {
            return false; // two bundles are not supported
        }

        if( ! sameBundleTemplate ) {
            return false;
        }

        if( ! createdFromPackage ) {
            return false;
        }

        return true;
    }

    @Override
    public boolean canReuseProductOffer(final String bundleTemplateId, final String productCode) {
        final List<AbstractOrderEntryModel> entries = getCartService().getSessionCart().getEntries();
        if( entries.isEmpty() ) {
            return false;
        }

        final Set<String> rootBundles = new HashSet<>();

        for (final AbstractOrderEntryModel entry : entries) {
            final BundleTemplateModel bundleTemplate = entry.getBundleTemplate();

            if( bundleTemplate == null ) {
                return false; // non-bundle entry inside (standalone)
            }

            final BundlePackageModel pkg = entry.getPackage();
            if( pkg != null ) {
                return false; // created from package
            }

            final BundleTemplateModel rootBundle = getBundleTemplateService().getRootBundleTemplate(bundleTemplate);
            rootBundles.add(rootBundle.getId());
        }

        final boolean sameBundleTemplate = rootBundles.iterator().next().equals(bundleTemplateId);
        final boolean oneBundleInside = rootBundles.size() == 1;

        if( ! oneBundleInside ) {
            return false; // two bundles are not supported
        }

        if( ! sameBundleTemplate ) {
            return false;
        }

        return true;
    }

    private static Set<ProductModel> getBoughtProducts(final OrderModel order) {
        final Set<ProductModel> products = new HashSet<>();
        for (final AbstractOrderEntryModel entry : order.getEntries()) {
            products.add(entry.getProduct());
        }
        return products;
    }

    @Override
    public BundleOfferData loadOrder(final String orderCode) {
        final OrderModel order = getOrder(orderCode);
        final Set<ProductModel> selectedProducts = getBoughtProducts(order);
        final BundleTemplateModel rootBundleTemplate = getRootBundleTemplate(order);

        int bundleNo = -1;
        final List<ConfigMessage> messages = new ArrayList<>();
        final Map<String,List<ConfigMessage>> componentMessageMap = new HashMap<>();

        for (final BundleTemplateModel childTemplate : rootBundleTemplate.getChildTemplates()) {
            final BundleSelectionCriteriaModel selectionCriteria = childTemplate.getBundleSelectionCriteria();
            if (selectionCriteria instanceof AutoPickBundleSelectionCriteriaModel) {
                continue;
            }

            final Collection<ProductModel> preselectedProducts = new HashSet<>(selectedProducts);
            preselectedProducts.retainAll(childTemplate.getProducts());

            final BundleComponentType componentType = childTemplate.getBundleComponentType();

            final List<ConfigMessage> componentMessages = new ArrayList<>();
            if (componentType == BundleComponentType.CONDITIONAL) {
                final ProductModel product = (ProductModel) CollectionUtils.find(preselectedProducts, InstanceofPredicate.getInstance(ServicePlanModel.class));
                bundleNo = addToCart(bundleNo, childTemplate, product, componentMessages);
            } else {
                for (final ProductModel product : preselectedProducts) {
                    bundleNo = addToCart(bundleNo, childTemplate, product, componentMessages);
                }
            }

            if(CollectionUtils.isNotEmpty(componentMessages))
            {
                componentMessageMap.put(childTemplate.getId(), componentMessages);
                messages.addAll(componentMessages);
            }
        }

        preselectLoyaltyPaymentFromOrder(order);

        final BundleOfferData offer = getBundleOffer(bundleNo);
        for(final String componentId : componentMessageMap.keySet())
        {
            final List<ConfigMessage> componentMessages = componentMessageMap.get(componentId);
            addMessagesForComponent(componentMessages, componentId, offer);
        }
        offer.getMessages().addAll(messages);

        orderChangesUnitOfWork.enterEditingMode(order);
        offer.setChangeOrder(true);

        return offer;
    }

    protected void preselectLoyaltyPaymentFromOrder(final OrderModel order)
    {
        final CartModel cart = getCartService().getSessionCart();
        for(final AbstractOrderEntryModel abstractOrderEntryModel : order.getEntries())
        {
            if(abstractOrderEntryModel.isLoyaltyPayment())
            {
                final ProductModel orderProductModel = abstractOrderEntryModel.getProduct();
                for(final AbstractOrderEntryModel cartEntryModel : cart.getEntries())
                {
                    if(cartEntryModel.getProduct().getCode().equals(orderProductModel.getCode()))
                    {
                        cartEntryModel.setLoyaltyPayment(true);
                        cartEntryModel.setCalculated(Boolean.FALSE);
                        cart.setCalculated(Boolean.FALSE);
                        for(final AbstractOrderEntryModel childCartEntryModel : cartEntryModel.getChildEntries())
                        {
                            childCartEntryModel.setLoyaltyPayment(true);
                            childCartEntryModel.setCalculated(Boolean.FALSE);
                            childCartEntryModel.getOrder().setCalculated(Boolean.FALSE);
                        }
                    }
                }
            }
        }

        getCartService().calculateCart(cart);
        for(final AbstractOrderModel childCartModel : cart.getChildren())
        {
            if(!childCartModel.getCalculated())
            {
                getCartService().calculateCart((CartModel)childCartModel);
            }
        }
    }

    @Override
    public BundleTemplateModel getRootBundleTemplate(String orderCode) {
        return getRootBundleTemplate(getOrder(orderCode));
    }

    private OrderModel getOrder(String orderCode) {
        return getCustomerAccountService().getOrderForCode(orderCode, getBaseStoreService().getCurrentBaseStore());
    }

    public BundleTemplateModel getRootBundleTemplate(final OrderModel order) {
        for (final AbstractOrderEntryModel entry : order.getEntries()) {
            if (entry.getBundleTemplate() != null) {
                return entry.getBundleTemplate().getParentTemplate();
            }
        }
        throw new IllegalArgumentException(String.format("Order %s does not contain entries with bundle template.", order.getCode()));
    }

    public void addMessagesForComponent(final List<ConfigMessage> messages, final String componentId, final BundleOfferData offer)
    {
        if(CollectionUtils.isNotEmpty(messages))
        {
            for(final BundleComponentData componentData : offer.getComponents())
            {
                if(componentData.getId().equals(componentId))
                {
                    List<ConfigMessage> mergedMessages = new ArrayList<>();
                    if(CollectionUtils.isNotEmpty(componentData.getMessages()))
                    {
                        mergedMessages.addAll(componentData.getMessages());
                    }
                    mergedMessages.addAll(messages);
                    componentData.setMessages(mergedMessages);
                }
            }
        }
    }

    @Override
    public boolean selectLoyaltyPayment(String productCode, int orderEntryNumber, boolean isLoyaltyPayment)
    {
        return loyaltyPointsService.selectLoyaltyPayment(productCode,orderEntryNumber,isLoyaltyPayment);
    }

    @Override
    public Integer getRemainingLoyaltyPoints()
    {
        return loyaltyPointsService.getUsersRemainingLoyaltyPoints();
    }

    @Required
	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}

	public ProductFacade getProductFacade()
	{
		return productFacade;
	}

    @Required
    public void setBundlePackageConverter(final Converter<BundlePackageModel, BundlePackageData> bundlePackageConverter) {
        this.bundlePackageConverter = bundlePackageConverter;
    }

    public Converter<BundlePackageModel, BundlePackageData> getBundlePackageConverter() {
        return bundlePackageConverter;
    }

    @Required
    public void setCartFacade(final BundleCartFacade cartFacade) {
        this.cartFacade = cartFacade;
    }

    public BundleCartFacade getCartFacade() {
        return cartFacade;
    }

    public BundlePackageService getBundlePackageService() {
        return bundlePackageService;
    }

    @Required
    public void setBundlePackageService(final BundlePackageService bundlePackageService) {
        this.bundlePackageService = bundlePackageService;
    }

    public Converter<BundleOfferPopulatorParameters, BundleOfferData> getBundleOfferConverter() {
        return bundleOfferConverter;
    }

    @Required
    public void setBundleOfferConverter(final Converter<BundleOfferPopulatorParameters, BundleOfferData> bundleOfferConverter) {
        this.bundleOfferConverter = bundleOfferConverter;
    }

    public CustomerAccountService getCustomerAccountService() {
        return customerAccountService;
    }

    @Required
    public void setCustomerAccountService(final CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    public BaseStoreService getBaseStoreService() {
        return baseStoreService;
    }

    @Required
    public void setBaseStoreService(final BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }

    public OrderChangesUnitOfWork getOrderChangesUnitOfWork() {
        return orderChangesUnitOfWork;
    }

    @Required
    public void setOrderChangesUnitOfWork(OrderChangesUnitOfWork orderChangesUnitOfWork) {
        this.orderChangesUnitOfWork = orderChangesUnitOfWork;
    }

    public LoyaltyPointsService getLoyaltyPointsService() {
        return loyaltyPointsService;
    }

    @Required
    public void setLoyaltyPointsService(LoyaltyPointsService loyaltyPointsService) {
        this.loyaltyPointsService = loyaltyPointsService;
    }

}
