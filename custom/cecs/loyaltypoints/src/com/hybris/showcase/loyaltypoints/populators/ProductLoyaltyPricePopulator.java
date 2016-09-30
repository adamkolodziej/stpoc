package com.hybris.showcase.loyaltypoints.populators;

import com.hybris.showcase.loyaltypoints.constants.LoyaltypointsConstants;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.subscriptionfacades.data.SubscriptionPricePlanData;
import de.hybris.platform.subscriptionservices.model.SubscriptionPricePlanModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.subscriptionservices.price.SubscriptionCommercePriceService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by mgolubovic on 27.3.2015..
 */
public class ProductLoyaltyPricePopulator <SOURCE extends ProductModel, TARGET extends ProductData> extends AbstractProductPopulator<SOURCE, TARGET>
{
    private static final Logger LOG = Logger.getLogger(ProductLoyaltyPricePopulator.class);

    private PriceDataFactory priceDataFactory;
    private SessionService sessionService;
    private CommonI18NService commonI18NService;

    private Populator<SubscriptionPricePlanModel, SubscriptionPricePlanData> pricePlanOneTimeChargePopulator;
    private Populator<SubscriptionPricePlanModel, SubscriptionPricePlanData> pricePlanRecurringChargePopulator;
    private Converter<SubscriptionPricePlanModel, SubscriptionPricePlanData> pricePlanUsageChargeConverter;
    private SubscriptionCommercePriceService commercePriceService;

    @Override
    public void populate(final SOURCE productModel, final TARGET productData) throws ConversionException
    {
        final CurrencyModel crdCurrency = getCreditCurrency();
        if(crdCurrency != null)
        {
            if (productModel instanceof SubscriptionProductModel)
            {
                final SubscriptionPricePlanData pricePlanData = getSessionService().executeInLocalView(
                    new SessionExecutionBody() {
                        @Override
                        public Object execute() {
                            getCommonI18NService().setCurrentCurrency(crdCurrency);
                            final SubscriptionPricePlanModel pricePlanModel =
                                    getCommercePriceService().getSubscriptionPricePlanForProduct((SubscriptionProductModel) productModel);

                            if (pricePlanModel != null && pricePlanModel.getCurrency().getIsocode().equals(LoyaltypointsConstants.CRD_CURRENCY_ISO)) {
                                final SubscriptionPricePlanData pricePlanData = (SubscriptionPricePlanData)
                                        getPricePlanUsageChargeConverter().convert(pricePlanModel);
                                pricePlanData.setName(pricePlanModel.getName());
                                getPricePlanOneTimeChargePopulator().populate(pricePlanModel, pricePlanData);
                                getPricePlanRecurringChargePopulator().populate(pricePlanModel, pricePlanData);
                                pricePlanData.setCurrencyIso(LoyaltypointsConstants.CRD_CURRENCY_ISO);

                                return pricePlanData;
                            }
                            else {
                                return null;
                            }
                        }
                    });
                if( pricePlanData != null
                    && CollectionUtils.isNotEmpty(pricePlanData.getRecurringChargeEntries())
                    && pricePlanData.getRecurringChargeEntries().get(0).getPrice() != null
                    && (pricePlanData.getRecurringChargeEntries().get(0).getPrice().getValue().compareTo(BigDecimal.ZERO) > 0))
                {
                    productData.setLoyaltyPointsPrice(pricePlanData);
                }
            }
            else if (hasLoyaltyPointsPrice(productModel))
            {
                final PriceInformation info = getSessionService().executeInLocalView(new SessionExecutionBody() {
                        @Override
                        public Object execute()
                        {
                            getCommonI18NService().setCurrentCurrency(crdCurrency);
                            return getCommercePriceService().getWebPriceForProduct(productModel);
                        }
                    });

                if (info != null && info.getPriceValue() != null && info.getPriceValue().getValue() > 0
                        && info.getPriceValue().getCurrencyIso().equals(LoyaltypointsConstants.CRD_CURRENCY_ISO))
                {
                    final PriceData priceData = getPriceDataFactory().create(PriceDataType.BUY,
                            BigDecimal.valueOf(info.getPriceValue().getValue()), LoyaltypointsConstants.CRD_CURRENCY_ISO);
                    productData.setLoyaltyPointsPrice(priceData);
                }
                else
                {
                    productData.setPurchasable(Boolean.FALSE);
                }
            }
        }
    }
    
    protected boolean hasLoyaltyPointsPrice(final ProductModel productModel)
    {
        final Collection<PriceRowModel> priceRows = productModel.getEurope1Prices();
        if(CollectionUtils.isNotEmpty(priceRows))
        {
            for(final PriceRowModel priceRowModel : priceRows)
            {
                if(priceRowModel.getCurrency().getIsocode().equals(LoyaltypointsConstants.CRD_CURRENCY_ISO))
                {
                    return true;
                }
            }
            return false;
        }
        else {
            return false;
        }
    }

    protected CurrencyModel getCreditCurrency()
    {
        try
        {
            return getCommonI18NService().getCurrency(LoyaltypointsConstants.CRD_CURRENCY_ISO);
        }
        catch (UnknownIdentifierException e)
        {
            LOG.error("Currency with code " + LoyaltypointsConstants.CRD_CURRENCY_ISO + " not found!");
            return null;
        }
    }

    public PriceDataFactory getPriceDataFactory() {
        return priceDataFactory;
    }

    @Required
    public void setPriceDataFactory(PriceDataFactory priceDataFactory) {
        this.priceDataFactory = priceDataFactory;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    @Required
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }

    @Required
    public void setCommonI18NService(CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }

    public Populator<SubscriptionPricePlanModel, SubscriptionPricePlanData> getPricePlanOneTimeChargePopulator() {
        return pricePlanOneTimeChargePopulator;
    }

    @Required
    public void setPricePlanOneTimeChargePopulator(Populator<SubscriptionPricePlanModel, SubscriptionPricePlanData> pricePlanOneTimeChargePopulator) {
        this.pricePlanOneTimeChargePopulator = pricePlanOneTimeChargePopulator;
    }

    public Populator<SubscriptionPricePlanModel, SubscriptionPricePlanData> getPricePlanRecurringChargePopulator() {
        return pricePlanRecurringChargePopulator;
    }

    @Required
    public void setPricePlanRecurringChargePopulator(Populator<SubscriptionPricePlanModel, SubscriptionPricePlanData> pricePlanRecurringChargePopulator) {
        this.pricePlanRecurringChargePopulator = pricePlanRecurringChargePopulator;
    }

    public Converter<SubscriptionPricePlanModel, SubscriptionPricePlanData> getPricePlanUsageChargeConverter() {
        return pricePlanUsageChargeConverter;
    }

    @Required
    public void setPricePlanUsageChargeConverter(Converter<SubscriptionPricePlanModel, SubscriptionPricePlanData> pricePlanUsageChargeConverter) {
        this.pricePlanUsageChargeConverter = pricePlanUsageChargeConverter;
    }

    public SubscriptionCommercePriceService getCommercePriceService() {
        return commercePriceService;
    }

    @Required
    public void setCommercePriceService(SubscriptionCommercePriceService commercePriceService) {
        this.commercePriceService = commercePriceService;
    }
}
