package com.hybris.showcase.emsextras.populators.entitlements.criteria;

import com.hybris.services.entitlements.condition.CriterionData;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.beans.factory.annotation.Required;

import java.util.*;

/**
 * CECS-242 entitlement check
 *
 * Created by miroslaw.szot@sap.com on 2015-05-20.
 */
public class ProductPathCriterionPopulator implements Populator<ProductModel, List<CriterionData>> {
    private CategoryService categoryService;

    @Override
    public void populate(ProductModel productModel, List<CriterionData> criteria) throws ConversionException {

        final Collection<CategoryModel> categories = productModel.getSupercategories();

        for (CategoryModel category : categories) {

            final Collection<List<CategoryModel>> paths = categoryService.getPathsForCategory(category);

            for (List<CategoryModel> path : paths) {
                final StringBuilder builder = createCategoryPathBuilder(path);
                final String productPath = calculateProductPath(builder, productModel);

                criteria.add(createPathCriterion(productPath));
            }
        }
    }

    protected String calculateProductPath(StringBuilder categoryPath, ProductModel productModel) {
        return categoryPath.append('/').append(productModel.getCode()).toString();
    }

    private CriterionData createPathCriterion(String path) {
        final CriterionData c = new CriterionData();
        c.setType("path");
        c.setProperty("file", path);
        return c;
    }

    private StringBuilder createCategoryPathBuilder(List<CategoryModel> path) {
        StringBuilder b = new StringBuilder();
        for (CategoryModel categoryModel : path) {
            b.append("/").append(categoryModel.getCode());
        }
        return b;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    @Required
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
