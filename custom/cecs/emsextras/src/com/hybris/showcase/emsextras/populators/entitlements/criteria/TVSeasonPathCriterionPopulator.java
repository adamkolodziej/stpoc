package com.hybris.showcase.emsextras.populators.entitlements.criteria;

import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.showcase.cecs.servicesshowcase.model.TVSeasonProductModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.List;

/**
 * CECS-242 entitlement check
 *
 * Created by miroslaw.szot@sap.com on 2015-05-20.
 */
public class TVSeasonPathCriterionPopulator extends ProductPathCriterionPopulator {
    @Override
    public void populate(ProductModel productModel, List<CriterionData> criteria) throws ConversionException {
        if( productModel instanceof TVSeasonProductModel) {
            super.populate(productModel, criteria);
        }
    }

    @Override
    protected String calculateProductPath(StringBuilder categoryPath, ProductModel productModel) {
        final TVSeasonProductModel season = (TVSeasonProductModel) productModel;
        final String show = season.getTvShow().getCode();

        return categoryPath
                .append('/').append(show)
                .append('/').append(season.getSeason())
                .toString();
    }
}
