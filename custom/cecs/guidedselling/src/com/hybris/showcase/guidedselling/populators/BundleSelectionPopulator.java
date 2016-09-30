package com.hybris.showcase.guidedselling.populators;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.hybris.showcase.guidedselling.data.BundleComponentData;
import com.hybris.showcase.guidedselling.data.BundleComponentOptionData;
import com.hybris.showcase.guidedselling.data.BundleOfferData;
import com.hybris.showcase.guidedselling.data.BundleOfferPopulatorParameters;
import com.hybris.showcase.guidedselling.data.SelectionMode;
import com.hybris.showcase.guidedselling.model.OrderChangesModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**
 * Created by miroslaw.szot@sap.com on 2015-03-13.
 */
public class BundleSelectionPopulator implements Populator<BundleOfferPopulatorParameters, BundleOfferData> {

    @Override
    public void populate(final BundleOfferPopulatorParameters parameters, final BundleOfferData offerData) {
        final List<AbstractOrderEntryModel> bundleEntries = parameters.getBundleEntries();
        final List<BundleComponentData> components = offerData.getComponents();
        final Map<String, BundleComponentOptionData> selectedOptionsMap = createComponentOptionMap(components);

        for (final AbstractOrderEntryModel bundleEntry : bundleEntries) {
            updateSelection(selectedOptionsMap, bundleEntry, parameters);
        }

        for (final BundleComponentData component : components) {
            for (final BundleComponentOptionData option : component.getOptions()) {
                if (option.isSelected()) {
                    component.setAnythingSelected(true);
                    break;
                }
            }
        }

        // update SingleSelection on component - PickExactly1 components
        updateSingleSelection(components);
        updateRemovedOptions(parameters, offerData);
    }

    protected void updateRemovedOptions(final BundleOfferPopulatorParameters parameters, final BundleOfferData offerData) {
        final List<BundleComponentData> components = offerData.getComponents();
        final OrderChangesModel orderChanges = parameters.getOrder().getOrderChanges();

        if (orderChanges != null) {
            Collection<String> removedProductCodes = Collections2.transform(orderChanges.getProductsRemoved(),
                    new Function<ProductModel, String>() {
                        @Nullable
                        @Override
                        public String apply(final ProductModel productModel) {
                            return productModel.getCode();
                        }
                    });

            removedProductCodes = new HashSet<>(removedProductCodes);

            for (final BundleComponentData component : components) {
                for (final BundleComponentOptionData option : component.getOptions()) {
                    if (removedProductCodes.contains(option.getProduct().getCode())) {
                        option.setRemovedFromExistingContract(true);
                    }
                }
            }
        }
    }

    private void updateSelection(final Map<String, BundleComponentOptionData> selectedOptionsMap,
                                 final AbstractOrderEntryModel bundleEntry, final BundleOfferPopulatorParameters parameters) {
        final String componentId = bundleEntry.getBundleTemplate().getId();
        final String productCode = bundleEntry.getProduct().getCode();

        final BundleComponentOptionData optionData = selectedOptionsMap.get(String.format("/%s/%s", componentId, productCode));
        optionData.setSelected(true);
        optionData.setLoyaltyPayment(bundleEntry.isLoyaltyPayment());
        optionData.setOrderEntryNumber(bundleEntry.getEntryNumber().intValue());
        optionData.setAddedToExistingContract(bundleEntry.isAddedToExistingContract());
        optionData.setLockedByPackage(bundleEntry.getPackage() != null);
    }

    protected void updateSingleSelection(final List<BundleComponentData> components) {
        for (final BundleComponentData component : components) {
            if (component.getSelectionMode() == SelectionMode.ONE) {
                for (final BundleComponentOptionData optionData : component.getOptions()) {
                    if (optionData.isSelected()) {
                        component.setSingleSelection(optionData.getProduct().getCode());
                    } else if (isAnyOptionLockedByPackage(component)) {
                        optionData.setLockedByPackage(true);
                    }
                }
            } else if (component.getSelectionMode() == SelectionMode.ONEOPTIONAL && isAnyOptionLockedByPackage(component)) {
                for (BundleComponentOptionData optionData : component.getOptions()) {
                    optionData.setLockedByPackage(true);
                }
            }
        }
    }

    private boolean isAnyOptionLockedByPackage(final BundleComponentData component) {
        return Iterables.any(component.getOptions(), new Predicate<BundleComponentOptionData>() {
            @Override
            public boolean apply(@Nullable final BundleComponentOptionData option) {
                return option.isLockedByPackage();
            }
        });
    }

    private Map<String, BundleComponentOptionData> createComponentOptionMap(final List<BundleComponentData> components) {
        final Map<String, BundleComponentOptionData> map = new HashMap<>();
        for (final BundleComponentData component : components) {
            for (final BundleComponentOptionData optionData : component.getOptions()) {
                final String productCode = optionData.getProduct().getCode();
                final String componentId = component.getId();

                map.put(String.format("/%s/%s", componentId, productCode), optionData);
            }
        }
        return map;
    }
}
