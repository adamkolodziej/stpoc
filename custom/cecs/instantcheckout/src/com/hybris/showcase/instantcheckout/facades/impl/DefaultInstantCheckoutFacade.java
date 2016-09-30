/**
 *
 */
package com.hybris.showcase.instantcheckout.facades.impl;

import static com.hybris.showcase.instantcheckout.enums.InstantCheckoutStatus.FAIL;
import static com.hybris.showcase.instantcheckout.enums.InstantCheckoutStatus.MISSING_DELIVERY_INFO;
import static com.hybris.showcase.instantcheckout.enums.InstantCheckoutStatus.MISSING_PAYMENT_INFO;

import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.commerceservices.strategies.DeliveryModeLookupStrategy;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.order.impl.DefaultCartService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.subscriptionfacades.SubscriptionFacade;
import de.hybris.platform.subscriptionfacades.converters.SubscriptionXStreamAliasConverter;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.subscriptionservices.subscription.SubscriptionCommerceCartService;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.instantcheckout.facades.InstantCheckoutFacade;
import com.hybris.showcase.loyaltypoints.services.LoyaltyPointsService;


/**
 *
 * @author mgolubovic
 */
public class DefaultInstantCheckoutFacade implements InstantCheckoutFacade
{
	protected static final Collection<ProductOption> PRODUCT_XML_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE);
	public static final String TEMP_CART = "temp_cart";
	protected static final Logger LOG = Logger.getLogger(DefaultInstantCheckoutFacade.class);

	private UserService userService;
	private ProductService productService;
	private CommerceCheckoutService commerceCheckoutService;
	private SessionService sessionService;
	private CartService cartService;
	private SubscriptionCommerceCartService subscriptionCommerceCartService;
	private DeliveryModeLookupStrategy deliveryModeLookupStrategy;
	private CheckoutFacade checkoutFacade;
	private ModelService modelService;
	private Converter<CommerceCartModification, CartModificationData> cartModificationConverter;
	// CECS-220: Send product info to SBG on placing order
	private SubscriptionFacade subscriptionFacade;

	private Converter<OrderModel, OrderData> orderConverter;

	private ProductFacade productFacade;
	private SubscriptionXStreamAliasConverter subscriptionXStreamAliasConverter;

	private LoyaltyPointsService loyaltyPointsService;
	private CalculationService calculationService;

	@Override
	public CartModificationData purchase(final String productCode, final Boolean loyaltyPayment)
			throws CommerceCartModificationException
	{
		final ProductModel product = productService.getProductForCode(productCode);
		final CustomerModel currentCustomer = (CustomerModel) userService.getCurrentUser();

		if (currentCustomer.getDefaultShipmentAddress() == null && BooleanUtils.isNotTrue(product.getDigitalDelivery()))
		{
			final CommerceCartModification modification = createModificationStatus(product, MISSING_DELIVERY_INFO);

			return cartModificationConverter.convert(modification);
		}

		if (currentCustomer.getDefaultPaymentInfo() == null)
		{
			final CommerceCartModification modification = createModificationStatus(product, MISSING_PAYMENT_INFO);

			return cartModificationConverter.convert(modification);
		}

		final CartModificationData cartModificationData = sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public Object execute()
			{
				try
				{
					final CartModel newCart = createNewCart();

					CommerceCartModification modification = addToCart(currentCustomer, newCart, product);
					if (newCart.getDeliveryMode() == null && BooleanUtils.isNotTrue(product.getDigitalDelivery()))
					{
						modification = createModificationStatus(product, MISSING_DELIVERY_INFO);
						return cartModificationConverter.convert(modification);
					}

					if (loyaltyPayment.booleanValue())
					{
						final boolean success = loyaltyPointsService.selectLoyaltyPayment(product.getCode(),
								newCart.getEntries().get(0).getEntryNumber().intValue(), true);
						if (!success)
						{
							final CommerceCartModification failedModification = new CommerceCartModification();
							failedModification.setStatusCode(FAIL);
							return cartModificationConverter.convert(failedModification);
						}
					}

					checkoutFacade.authorizePayment("");
					final OrderModel order = placeOrder(newCart);

					// CECS-220: Send product info to SBG on placing order - START
					if (product instanceof SubscriptionProductModel)
					{
						final OrderData orderData = getOrderConverter().convert(order);
						getSubscriptionFacade().createSubscriptions(orderData, new HashMap<String, String>());
					}
					// CECS-220: Send product info to SBG on placing order - END

					final CartModificationData cartModificationData = cartModificationConverter.convert(modification);
					cartModificationData.getEntry().setOriginalOrderCode(order.getCode());

					afterPlaceOrder(newCart, order);

					return cartModificationData;
				}
				catch (final InvalidCartException | CommerceCartModificationException | SubscriptionFacadeException
						| CalculationException e)
				{
					e.printStackTrace();
					final CommerceCartModification modification = new CommerceCartModification();
					modification.setStatusCode(FAIL);
					return cartModificationConverter.convert(modification);
				}
			}
		});
		if (loyaltyPayment.booleanValue())
		{
			loyaltyPointsService.invalidateRemainingLoyaltyPointsCache();
		}
		return cartModificationData;
	}

	protected String getProductAsXML(final ProductModel product)
	{
		final ProductData productData = getProductFacade().getProductForOptions(product, PRODUCT_XML_OPTIONS);
		return getSubscriptionXStreamAliasConverter().getXStreamXmlFromSubscriptionProductData(productData);
	}

	protected CommerceCartModification createModificationStatus(final ProductModel product, final String statusCode)
	{
		final CommerceCartModification modification = new CommerceCartModification();
		modification.setProduct(product);
		final CartEntryModel entry = new CartEntryModel();
		entry.setProduct(product);
		modification.setEntry(entry);
		modification.setQuantity(1);
		modification.setQuantityAdded(0);
		modification.setStatusCode(statusCode);
		return modification;
	}

	protected CartModel createNewCart()
	{
		getSessionService().removeAttribute(DefaultCartService.SESSION_CART_PARAMETER_NAME);

		final CartModel newCart = cartService.getSessionCart();

		return newCart;
	}

	protected CommerceCartModification addToCart(final CustomerModel customer, final CartModel newCart, final ProductModel product)
			throws CommerceCartModificationException, CalculationException
	{
		final String xmlProduct = getProductAsXML(product);

		final CommerceCartModification modification = getSubscriptionCommerceCartService().addToCart(newCart, product, 1,
				product.getUnit(), false, xmlProduct);

		newCart.setDeliveryAddress(customer.getDefaultShipmentAddress());

		final List<DeliveryModeModel> deliveryModes = deliveryModeLookupStrategy.getSelectableDeliveryModesForOrder(newCart);

		DeliveryModeModel deliveryMode = null;
		if (!deliveryModes.isEmpty())
		{
			deliveryMode = deliveryModes.get(0);
		}

		newCart.setDeliveryMode(deliveryMode);
		newCart.setPaymentInfo(customer.getDefaultPaymentInfo());

		modelService.save(newCart);
		calculationService.calculate(newCart);

		return modification;
	}

	protected OrderModel placeOrder(final CartModel cartModel) throws InvalidCartException
	{
		final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(cartModel);
		parameter.setSalesApplication(SalesApplication.WEB);

		final CommerceOrderResult commerceOrderResult = getCommerceCheckoutService().placeOrder(parameter);
		return commerceOrderResult.getOrder();
	}

	protected void afterPlaceOrder(final CartModel cartModel, final OrderModel orderModel)
	{
		if (orderModel != null)
		{
			// Remove cart
			getCartService().removeSessionCart();
			getModelService().refresh(orderModel);
		}
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	protected CommerceCheckoutService getCommerceCheckoutService()
	{
		return commerceCheckoutService;
	}

	@Required
	public void setCommerceCheckoutService(final CommerceCheckoutService commerceCheckoutService)
	{
		this.commerceCheckoutService = commerceCheckoutService;
	}

	protected SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
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

	public SubscriptionCommerceCartService getSubscriptionCommerceCartService()
	{
		return subscriptionCommerceCartService;
	}

	@Required
	public void setSubscriptionCommerceCartService(final SubscriptionCommerceCartService subscriptionCommerceCartService)
	{
		this.subscriptionCommerceCartService = subscriptionCommerceCartService;
	}

	public DeliveryModeLookupStrategy getDeliveryModeLookupStrategy()
	{
		return deliveryModeLookupStrategy;
	}

	@Required
	public void setDeliveryModeLookupStrategy(final DeliveryModeLookupStrategy deliveryModeLookupStrategy)
	{
		this.deliveryModeLookupStrategy = deliveryModeLookupStrategy;
	}

	public CheckoutFacade getCheckoutFacade()
	{
		return checkoutFacade;
	}

	@Required
	public void setCheckoutFacade(final CheckoutFacade checkoutFacade)
	{
		this.checkoutFacade = checkoutFacade;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public Converter<CommerceCartModification, CartModificationData> getCartModificationConverter()
	{
		return cartModificationConverter;
	}

	@Required
	public void setCartModificationConverter(
			final Converter<CommerceCartModification, CartModificationData> cartModificationConverter)
	{
		this.cartModificationConverter = cartModificationConverter;
	}

	public Converter<OrderModel, OrderData> getOrderConverter()
	{
		return orderConverter;
	}

	@Required
	public void setOrderConverter(final Converter<OrderModel, OrderData> orderConverter)
	{
		this.orderConverter = orderConverter;
	}

	public ProductFacade getProductFacade()
	{
		return productFacade;
	}

	@Required
	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}

	protected SubscriptionXStreamAliasConverter getSubscriptionXStreamAliasConverter()
	{
		return subscriptionXStreamAliasConverter;
	}

	@Required
	public void setSubscriptionXStreamAliasConverter(final SubscriptionXStreamAliasConverter subscriptionXStreamAliasConverter)
	{
		this.subscriptionXStreamAliasConverter = subscriptionXStreamAliasConverter;
	}

	public SubscriptionFacade getSubscriptionFacade()
	{
		return subscriptionFacade;
	}

	@Required
	public void setSubscriptionFacade(final SubscriptionFacade subscriptionFacade)
	{
		this.subscriptionFacade = subscriptionFacade;
	}

	public LoyaltyPointsService getLoyaltyPointsService()
	{
		return loyaltyPointsService;
	}

	@Required
	public void setLoyaltyPointsService(final LoyaltyPointsService loyaltyPointsService)
	{
		this.loyaltyPointsService = loyaltyPointsService;
	}

	public CalculationService getCalculationService()
	{
		return calculationService;
	}

	@Required
	public void setCalculationService(final CalculationService calculationService)
	{
		this.calculationService = calculationService;
	}
}
