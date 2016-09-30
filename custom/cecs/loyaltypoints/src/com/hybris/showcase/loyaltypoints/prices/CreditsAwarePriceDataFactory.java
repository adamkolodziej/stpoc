package com.hybris.showcase.loyaltypoints.prices;

import de.hybris.platform.commercefacades.product.impl.DefaultPriceDataFactory;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;

public class CreditsAwarePriceDataFactory extends DefaultPriceDataFactory {

    private static final String CREDITS_ISOCODE = "CRD";

    @Override
    protected DecimalFormat adjustSymbol(DecimalFormat format, CurrencyModel currencyModel) {
        final DecimalFormat decimalFormat = super.adjustSymbol(format, currencyModel);

        if (CREDITS_ISOCODE.equals(currencyModel.getIsocode())) {
            decimalFormat.setPositivePrefix(StringUtils.EMPTY);
            decimalFormat.setNegativePrefix(StringUtils.EMPTY);

            final String newSuffix = " " + currencyModel.getSymbol();
            decimalFormat.setPositiveSuffix(newSuffix);
            decimalFormat.setNegativeSuffix(newSuffix);
        }

        return decimalFormat;
    }
}
