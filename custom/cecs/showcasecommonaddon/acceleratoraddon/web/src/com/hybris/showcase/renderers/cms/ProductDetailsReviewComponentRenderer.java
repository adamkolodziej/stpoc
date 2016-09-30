package com.hybris.showcase.renderers.cms;

import com.hybris.showcase.facades.TVSeriesFacade;
import com.hybris.showcase.forms.AddReviewForm;
import com.hybris.showcase.model.components.ProductDetailsReviewSummaryComponentModel;
import de.hybris.platform.acceleratorstorefrontcommons.forms.ReviewForm;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.model.components.ProductDetailsReviewComponentModel;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * CECS-173 Product reviews component
 *
 * Created by mgolubovic on 6.3.2015..
 */
public class ProductDetailsReviewComponentRenderer extends DefaultAddOnCMSComponentRenderer<ProductDetailsReviewComponentModel>
{
    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Resource(name = "themeSource")
    private MessageSource messageSource;

    @Resource(name = "i18nService")
    private I18NService i18nService;

    @Resource(name = "tVSeriesFacade")
    private TVSeriesFacade tVSeriesFacade;

    private static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.REVIEW);

    @Override
    protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final ProductDetailsReviewComponentModel component)
    {
        final Map<String, Object> variables = new HashMap<String, Object>();

        final ProductModel productModel = getProductModel(pageContext);
        final Map<Integer, Integer> starsToPercentage = new HashMap<Integer, Integer>();
        final int starAmount = 5;

        for(int i=0; i < starAmount; i++) {
            int counter = 0;
            final int reviewsSum = productModel.getProductReviews().size();
            int average = 0;

            if(reviewsSum > 0) {
                for (final CustomerReviewModel customerReview : productModel.getProductReviews()) {
                    final int currRate = customerReview.getRating().intValue();
                    if (i + 1 == currRate) {
                        counter++;
                    }
                }

                if (counter > 0) {
                    average = 100 * counter / productModel.getProductReviews().size();
                }
            }

            starsToPercentage.put(i+1, average);
        }

        final List<CustomerReviewModel> sortedReviews = new ArrayList<>(productModel.getProductReviews());
        sortedReviews.sort((review1,review2) -> review2.getRating().compareTo(review1.getRating()));

        variables.put("sortedReviews", sortedReviews);
        variables.put("productReviewed", tVSeriesFacade.populate(productModel, PRODUCT_OPTIONS));
        variables.put("starsToPercentageMap", starsToPercentage);
        variables.put("starAmount", starAmount);

        final AddReviewForm addReviewFormSubmitted = sessionService.getCurrentSession().getAttribute("addReviewForm");
        final BindingResult bindingResultSubmitted = sessionService.getCurrentSession().getAttribute("bindingResult");
        if (bindingResultSubmitted != null && bindingResultSubmitted.hasErrors())
        {

            variables.put("addReviewForm", addReviewFormSubmitted);

            final Map<String, String> errorMessageMap = new HashMap<>();
            for(final ObjectError error: bindingResultSubmitted.getAllErrors()) {
                errorMessageMap.put(((FieldError)error).getField(), messageSource.getMessage(error.getCode(), null, i18nService.getCurrentLocale()));
            }

            variables.put("errors", errorMessageMap);
        }
        else {
            final AddReviewForm addReviewForm = new AddReviewForm();
            addReviewForm.setName(userService.getCurrentUser().getName());
            variables.put("addReviewForm", addReviewForm);

            final String submitFailed = sessionService.getCurrentSession().getAttribute("submitFailed");
            final String submitSuccessful = sessionService.getCurrentSession().getAttribute("submitSuccessful");
            if(submitFailed != null) {
                variables.put("submitFailed", messageSource.getMessage(submitFailed, null, i18nService.getCurrentLocale()));
                sessionService.getCurrentSession().removeAttribute("submitFailed");
            }
            else if(submitSuccessful != null){
                variables.put("submitSuccessful", messageSource.getMessage(submitSuccessful, null, i18nService.getCurrentLocale()));
                sessionService.getCurrentSession().removeAttribute("submitSuccessful");
            }
        }

        return variables;
    }

    protected ProductModel getProductModel(final PageContext pageContext)
    {
        final ProductModel requestProductModel = getRequestContextData((HttpServletRequest) pageContext.getRequest()).getProduct();
        final ProductModel showProductModel = tVSeriesFacade.getShowForProduct(requestProductModel);

        // When showProductModel is null then request product model is not TVShowProductModle nor TVSeasonProductModel nor TVEpisodeProductModel,
        // so it's not needed to get parent of if (it don't have any parent).
        return showProductModel == null ? requestProductModel : showProductModel;
    }
}
