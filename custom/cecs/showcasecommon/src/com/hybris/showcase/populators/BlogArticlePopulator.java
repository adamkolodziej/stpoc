/**
 *
 */
package com.hybris.showcase.populators;

import com.hybris.showcase.data.BlogArticleData;
import com.hybris.showcase.model.components.BlogArticleModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;


/**
 * @author i327223
 */
public class BlogArticlePopulator implements Populator<BlogArticleModel, BlogArticleData> {

    private FlexibleSearchService flexibleSearchService;

    @Override
    public void populate(final BlogArticleModel source, final BlogArticleData target) throws ConversionException {
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setContent(source.getContent());
        target.setSummary(source.getSummary());
        target.setImageURL(source.getBlogImage().getURL());
        target.setDate(source.getPublishDate());
        target.setCategoryName("");
        target.setCategoryCode("");
        
        if (source.getProduct() != null) {
            ComposedTypeModel comp = new ComposedTypeModel();
            String code = source.getProduct().getItemtype();
            
            target.setCategoryCode(code);
            comp.setCode(code);
            List<ComposedTypeModel> modelsByExample = flexibleSearchService.getModelsByExample(comp);
            if (CollectionUtils.isNotEmpty(modelsByExample)) {
                target.setCategoryName(modelsByExample.iterator().next().getName());
            }
        }
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

}