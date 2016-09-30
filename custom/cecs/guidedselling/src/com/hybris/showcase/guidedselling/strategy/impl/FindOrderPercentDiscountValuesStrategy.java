package com.hybris.showcase.guidedselling.strategy.impl;

import com.hybris.showcase.guidedselling.model.ChangeProductPercentPriceBundleRuleModel;
import de.hybris.platform.configurablebundleservices.enums.BundleRuleTypeEnum;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.order.strategies.calculation.FindDiscountValuesStrategy;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.util.DiscountValue;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Miroslaw Radwanski on 06.04.16.
 */
public class FindOrderPercentDiscountValuesStrategy extends AbstractBusinessService implements FindDiscountValuesStrategy {

    private static final Logger LOG = Logger.getLogger(FindOrderPercentDiscountValuesStrategy.class);

    @Override
    public List<DiscountValue> findDiscountValues(final AbstractOrderEntryModel entry) throws CalculationException {

        AbstractOrderEntryModel entryBundle;
        entryBundle = (entry.getMasterEntry() != null) ? entry.getMasterEntry() : entry;

        if (entryBundle.getBundleTemplate() == null || entryBundle.getBundleNo() == null || entryBundle.getBundleNo() < 1)
            return Collections.EMPTY_LIST;

        List<DiscountValue> result = new ArrayList<DiscountValue>();
        final AbstractOrderModel order = findOrderRoot(entry);

        //List of entries with the same bundleNo as entry.getBundleNo()
        List<AbstractOrderEntryModel> entryFiltered = order.getEntries().stream().filter(orderEntry -> orderEntry.getBundleNo().equals(entryBundle.getBundleNo())).collect(Collectors.toList());
        //List of products with the same bundleNo as entry.getBundleNo()
        Collection<ProductModel> filteredProducts = new ArrayList<>();
        for (AbstractOrderEntryModel ent : entryFiltered) {
            filteredProducts.add(ent.getProduct());
        }

        for (ChangeProductPercentPriceBundleRuleModel percentRule : entryBundle.getBundleTemplate().getChangeProductPercentPriceBundleRules()) {

            /*// Is entry's product on bundle's rule target products list?
            if (!percentRule.getTargetProducts().stream().filter(o -> o.equals(entry.getProduct())).findFirst().isPresent())
                continue; //This product is not on the target list - skip this iteration*/

            if (percentRule.getBillingEvent() != null) {
                if (!percentRule.getBillingEvent().getCode().equals(entryBundle.getOrder().getBillingTime().getCode()))
                    continue;
            }

            //Is the condition met?
            boolean isConditionMet = false;
            if (percentRule.getRuleType() == BundleRuleTypeEnum.ANY) {
                isConditionMet = CollectionUtils.containsAny(percentRule.getConditionalProducts(), filteredProducts);
            }

            if (percentRule.getRuleType() == BundleRuleTypeEnum.ALL) {
                isConditionMet = filteredProducts.containsAll(percentRule.getConditionalProducts());
            }

            if (isConditionMet) {

                DiscountValue discountValue = new DiscountValue(percentRule.getName(), percentRule.getPercentageDiscount(), false, entry.getOrder().getCurrency().getIsocode());
                result.add(discountValue);
            }

        }

        return result;
    }

    @Override
    public List<DiscountValue> findDiscountValues(final AbstractOrderModel order) throws CalculationException {
        return Collections.EMPTY_LIST;
    }

    private AbstractOrderModel findOrderRoot(final AbstractOrderEntryModel entry) {
        AbstractOrderModel order = entry.getOrder();

        while (order.getParent() != null) {
            order = order.getParent();
        }

        return order;
    }

}
