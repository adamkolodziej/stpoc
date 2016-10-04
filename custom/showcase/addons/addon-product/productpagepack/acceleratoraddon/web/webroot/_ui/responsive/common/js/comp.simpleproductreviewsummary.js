var SELECTOR_REVIEW_TAB = "#nav-tab-reviews";
var SELECTOR_PRODUCT_TABS = "#prod_tab";
var SELECTOR_BUTTON_WRITE = "#write_review_action_main";
var SELECTOR_BUTTON_BASED_ON = "#based_on_reviews";

$(document).ready(function() {
	
	if ($("#write_review_action_main").length) {
		$("#nav-tab-reviews").css('display','block');
	}
	
	$(SELECTOR_BUTTON_WRITE).click(function(e) {
		e.preventDefault();
		selectReviewTab();
		// TODO: ADD LOGIC
		//$('#reviewForm input[name=headline]').focus();
	});
	
	$(SELECTOR_BUTTON_BASED_ON).click( function(e) {
		e.preventDefault();
		selectReviewTab();
		
		// TODO: ADD LOGIC
//		$('#write_reviews').hide();
//		$('#nav-tab-reviews').tab("show");
//		$('#read_reviews_action').click();		
	});
	
	setTimeout(function() {
		if($.query.get('tab') == 'writereview') {
			$('#write_review_action_main').click();
		}
		if($.query.get('tab') == 'readreviews') {
			$('#based_on_reviews').click();
		}
	}, 100);
});

function selectReviewTab() {
	$.scrollTo(SELECTOR_PRODUCT_TABS, 300, {axis: 'y'});
	$(SELECTOR_REVIEW_TAB).tab("show");
}
