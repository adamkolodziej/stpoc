function addRecommendation(recoId) {
	return function(data){
		if (data !== ''){
			$("#" + recoId ).append(data);
			/*jQuery('#recommendationUL'+recoId).jcarousel({
			 vertical: false
			 });*/
			var addToCartForm = $("#" + recoId).find('.add_to_cart_form');
			addToCartForm.ajaxForm({success: ACC.product.displayAddToCartPopup});
		}
		else {
			$("#" + recoId ).removeClass();
		}
	}
}

function retrieveRecommendations(id,title,recoType,productCode,itemType,includeCart,componentId){
	ajaxUrl = '/yacceleratorstorefront/action/recommendations/';
	$.get(ajaxUrl,
		{
			id: id,
			title: title,
			recoType: recoType,
			productCode: productCode,
			itemType: itemType,
			includeCart: includeCart,
			componentId: componentId
		},
		addRecommendation(id));
}

function registerClickthrough(id, itemType, scenarioId, prodURL, prodImageURL){
	ajaxUrl = '/yacceleratorstorefront/action/interaction/';
	$.post(ajaxUrl, {
		id: id,
		itemType: itemType,
		scenarioId: scenarioId,
		prodURL: prodURL,
		prodImageURL: prodImageURL
	}, null);
}


function loadData() {
	function getElementsByClassName(node, classname) {
		var a = [];
		var re = new RegExp('(^| )' + classname + '( |$)');
		var els = node.getElementsByTagName("*");
		for (var i = 0, j = els.length; i < j; i++)
			if (re.test(els[i].className))
				a.push(els[i]);
		return a;
	}

	divs = getElementsByClassName(document.body, 'scroller');

	for (var i = 0; i < divs.length; i++) {
		if (divs[i].id.search("reco") > -1) {
			var $div = $("#" + divs[i].id);
			var title = $div.attr("data-title");
			var recoType = $div.attr("data-recotype");
			var productCode = $div.attr("data-prodcode");
			var itemType = $div.attr("data-itemtype");
			var includeCart = $div.attr("data-includecart");
			var componentId = $div.attr("data-component");

			retrieveRecommendations(divs[i].id, title, recoType, productCode, itemType, includeCart, componentId);
		}
	}
}

$(function () {
	loadData();
});