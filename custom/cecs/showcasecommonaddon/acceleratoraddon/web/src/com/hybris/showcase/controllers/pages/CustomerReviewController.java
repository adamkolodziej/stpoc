package com.hybris.showcase.controllers.pages;

import com.hybris.showcase.converter.populator.CustomerReviewFormToCustomerReviewPopulator;
import com.hybris.showcase.forms.AddReviewForm;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.http.HttpHeaders;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("/reviews")
public class CustomerReviewController extends AbstractPageController
{
	private static final String REDIRECT_PREFIX = "redirect:";
	private static final String REVIEW_FORM_ERROR_PREFIX = "review.form.error.";
	private static final String SUBMIT_FAILED_COMMENTED = "review.form.failed.commented";
	private  static final String REVIEW_SUBMIT_SUCCESSFUL_LOCALIZE = "review.form.success";
	private  static final String REVIEW_MARKED_HELPFUL = "review.marked.helpful";
	private  static final String REVIEW_VOTED_SUCCESSFULLY = "message.review.voted.successfully";
	private  static final String REVIEW_VOTED_ALREADY = "message.review.voted.already";

	@Resource(name = "userService")
	private UserService userService;

	@Resource
	private ProductService productService;

	@Resource
	private ModelService modelService;

	@Resource(name = "themeSource")
	private MessageSource messageSource;

	@Resource(name = "i18nService")
	private I18NService i18nService;

	@Resource(name = "customerReviewFormToCustomerReviewPopulator")
	private CustomerReviewFormToCustomerReviewPopulator customerReviewFormToCustomerReviewPopulator;

	@RequestMapping(value = "/vote", method = RequestMethod.POST)
	@ResponseBody
	public String voteForReview(@RequestParam(value = "rate") final boolean rate, @RequestParam("productCode") final String productCode, @RequestParam("customerCommentatorId") final String customerCommentatorId) throws CMSItemNotFoundException
	{
		String responseMessage = messageSource.getMessage(REVIEW_VOTED_ALREADY, null,
						i18nService.getCurrentLocale());
		final CustomerModel customerVoter = (CustomerModel) userService.getCurrentUser();

		ProductModel product = productService.getProductForCode(productCode);

		for (CustomerReviewModel customerReview : product.getProductReviews()) {
			if (customerReview.getCommentatorName().equals(customerCommentatorId)) {
				if (!customerReview.getUsersMarkedReviewUnhelpful().contains(customerVoter) && !customerReview.getUsersMarkedReviewHelpful().contains(customerVoter)) {
					if (rate) {
						Set<UserModel> usersMarkedReviewHelpful = new HashSet<>(customerReview.getUsersMarkedReviewHelpful());
						usersMarkedReviewHelpful.add(customerVoter);
						customerReview.setUsersMarkedReviewHelpful(usersMarkedReviewHelpful);
					} else {
						Set<UserModel> usersMarkedReviewUnhelpful = new HashSet<>(customerReview.getUsersMarkedReviewUnhelpful());
						usersMarkedReviewUnhelpful.add(customerVoter);
						customerReview.setUsersMarkedReviewUnhelpful(usersMarkedReviewUnhelpful);
					}
					modelService.save(customerReview);
					responseMessage = messageSource.getMessage(REVIEW_VOTED_SUCCESSFULLY, null,
							i18nService.getCurrentLocale());
				}
			}
		}

		return responseMessage;
	}

	@RequestMapping(value = "/vote", method = RequestMethod.GET)
	@ResponseBody
	public String getReviewVotesStatus(@RequestParam("productCode") final String productCode, @RequestParam("customerCommentatorId") final String customerCommentatorId) throws CMSItemNotFoundException {
		String message = "";
		Collection<CustomerReviewModel> customerReviews =  productService.getProductForCode(productCode).getProductReviews();
		for(CustomerReviewModel review: customerReviews){
			if(review.getCommentatorName().equals(customerCommentatorId)) {
				List<String> paramList = Arrays.asList(String.valueOf(review.getUsersMarkedReviewHelpful().size()), String.valueOf(review.getUsersMarkedReviewHelpful().size() + review.getUsersMarkedReviewUnhelpful().size()));
				message = messageSource.getMessage(REVIEW_MARKED_HELPFUL, paramList.toArray(), i18nService.getCurrentLocale());
				break;
			}
		}

		return message;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addReview(final Model model, final HttpServletRequest request, @ModelAttribute("addReviewForm") @Valid AddReviewForm addReviewForm, final BindingResult bindingResult) throws CMSItemNotFoundException {

		Session currSession = getSessionService().getCurrentSession();

		if (bindingResult.hasErrors())
		{
			currSession.setAttribute("addReviewForm", addReviewForm);
			currSession.setAttribute("bindingResult", bindingResult);

			validateReviewForm(bindingResult);
		}
		else {
			try {
				currSession.removeAttribute("addReviewForm");
				currSession.removeAttribute("bindingResult");
				CustomerReviewModel customerReview = new CustomerReviewModel();
				customerReviewFormToCustomerReviewPopulator.populate(addReviewForm, customerReview);
				modelService.save(customerReview);
			}catch (ModelSavingException ex) {
				currSession.setAttribute("submitFailed", SUBMIT_FAILED_COMMENTED);
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			currSession.setAttribute("submitSuccessful", REVIEW_SUBMIT_SUCCESSFUL_LOCALIZE);
		}

		return REDIRECT_PREFIX + request.getHeader(HttpHeaders.REFERER);
	}


	private void validateReviewForm(BindingResult bindingResult) {
		List<String> bindingResultList = new ArrayList<>();

		for(Iterator<ObjectError> errorIter = bindingResult.getAllErrors().iterator(); errorIter.hasNext();) {
			String fieldName = ((FieldError) errorIter.next()).getField();
			bindingResultList.add(fieldName);
		}

		bindingResultList.forEach(result -> bindingResult.rejectValue(result, REVIEW_FORM_ERROR_PREFIX + result));
	}
}
