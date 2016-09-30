package com.hybris.showcase.renderers.cms;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.hybris.showcase.facades.TVSeriesFacade;
import com.hybris.showcase.forms.AddReviewForm;
import com.hybris.showcase.model.components.ProductDetailsReviewSummaryComponentModel;

/**
 * Created by mgolubovic on 12.3.2015..
 */
public class ProductDetailsReviewSummaryComponentRenderer extends DefaultAddOnCMSComponentRenderer<ProductDetailsReviewSummaryComponentModel>
{
    static final int starAmount = 5;

    @Override
    protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final ProductDetailsReviewSummaryComponentModel component) {

        final Map<String, Object> variables = new HashMap<String, Object>();

        final ProductModel productModel = getRequestContextData((HttpServletRequest) pageContext.getRequest()).getProduct();

        Double averageRating = (productModel.getAverageRating()==null) ? 0 : productModel.getAverageRating();

        variables.put("starAmount", starAmount);
        variables.put("averageRating", averageRating);

        return variables;
    }
}
