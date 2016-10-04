var BUY_SET_BUTTON = "#buyTheSetButton";
var FIELD_PRODUCT_QUANTITY = ".prod_add_to_cart input[type=number].qty";
var OUT_OF_STOCK_STYLE = "out-of-stock";
var BUTTON_ADD_TO_CART = ".addToCartButtonSet";
var ADD_TO_CART = "#addToCartLayer";
var HEADER = "#header";
var BIND_STATE = false;

$(document).ready(function() {
	configureColorbox();
	configureBuyButton();
})

function configureColorbox() {
	$(".productSetPopup").colorbox({
		onComplete: function ()
		{
			ACC.common.refreshScreenReaderBuffer();
			$.colorbox.resize();
		},

		onClosed: function ()
		{
			ACC.common.refreshScreenReaderBuffer();
		}
	});
}

function chooseDisplayStyle(div) {
	var componentUid = $(div).data("componentuid");
	var productSetNarrowSelector = "#" + componentUid + "-productset-narrow";
	var productSetWideSelector = "#" + componentUid + "-productset-wide";
	var availableWidth = $(div).width();
	if(2 * getElementWidth() > availableWidth) {
		$(productSetNarrowSelector).css("display", "block");
		$(productSetWideSelector).remove();
	}
	else {
		$(productSetWideSelector).css("display", "block");
		$(productSetNarrowSelector).remove();
		layoutProductSetElements(componentUid, availableWidth);
	}
}

function layoutProductSetElements(componentUid, availableWidth) {
	var infoSelector = "#" + componentUid + "-productset-info";
	var info = $(infoSelector);
	var nSets = info.data("maxsetindex") + 1;

	var totalProductsInfo = [];
	var productsInRowInfo = [];
	var totalElementsInRow = 0;
	for(var i=0; i < nSets; i++) {
		var setInfoSelector = "#" + componentUid + "-productset-info-" + i;
		var nproducts = $(setInfoSelector).data("nproducts");
		totalProductsInfo.push(nproducts);
		var productsInRow = Math.ceil(Math.sqrt(nproducts));
		productsInRowInfo.push(productsInRow);
		totalElementsInRow += productsInRow;
	}
	
	var maxTotalElementsInRow = calculateMaxTotalElementsInRow(availableWidth);
	var shouldDisplayAllSets = totalElementsInRow * getElementWidth() <= availableWidth;	
	if(shouldDisplayAllSets) {
		$(".productset-left-arrow").hide();
		$(".productset-right-arrow").hide();
	}
	
	for(var i=0; i < nSets; i++) {
		if(productsInRow[i] > maxTotalElementsInRow || totalProductsInfo[i] <= maxTotalElementsInRow) {
			productsInRowInfo[i] = maxTotalElementsInRow;
		}
	}	
		
	for(var i=0; i < nSets; i++) {
		var parentDivSelector = "#" + componentUid + "-productset-div-" + i;
		var parentDiv = $(parentDivSelector)
		
		var rowDiv = $("<div style=\"display: table-row\"></div>");
		rowDiv.appendTo(parentDivSelector);
		for(var j=0; j < totalProductsInfo[i]; j++) {
			if(j % productsInRowInfo[i] == 0 ) {
				rowDiv = $("<div style=\"display: table-row\"></div>");
				rowDiv.appendTo(parentDivSelector);
			}
			var productElementSelector = "#" + componentUid + "-productset-info-" + i + "-" + j;
			$(productElementSelector).appendTo(rowDiv);
		}
	}
}

function changeDisplayedProductSet(change, componentUid) {
	var info = $("#" + componentUid + "-productset-info");
	var currentSetIndex = info.data("currentset");
	var maxSetIndex = info.data("maxsetindex");
	
	if(currentSetIndex + change < 0) {
		return;
	}
	
	if(currentSetIndex + change > maxSetIndex) {
		return;
	}

	var currentSetSelector = "#" + componentUid + "-productset-div-" + currentSetIndex;
	var displayStyle = $(currentSetSelector).css("display");
	$(currentSetSelector).css("display", "none");
	$("#buyTheSet-" + componentUid + "-" + currentSetIndex).hide();
	
	currentSetIndex = currentSetIndex + change;
	$("#" + componentUid + "-productset-div-" + currentSetIndex).css("display", displayStyle);
	$("#" + componentUid + "-productset-info").data("currentset", currentSetIndex);
	$("#buyTheSet-" + componentUid + "-" + currentSetIndex).show();
	
	var leftArrowSelector = "#" + componentUid + "-left-arrow";
	if(currentSetIndex == 0) {
		$(leftArrowSelector).removeClass("active").addClass("inactive");
	}
	else {
		$(leftArrowSelector).removeClass("inactive").addClass("active");
	}
	
	var rightArrowSelector = "#" + componentUid + "-right-arrow";
	if(currentSetIndex == maxSetIndex) {
		$(rightArrowSelector).removeClass("active").addClass("inactive");
	}
	else {
		$(rightArrowSelector).removeClass("inactive").addClass("active");
	}
}

function configureBuyButton() {
	$(".buyTheSetButton").colorbox({
		
		width: "950px",
		
		onComplete: function ()
		{
			var $cboxWindow = $('#cboxLoadedContent');
			var $mainProductContainer = $cboxWindow.find('.product_set_main');
			var $additionalProductsContainer = $cboxWindow.find('.product_set_additional');
			var height = $mainProductContainer.height();
			
			$additionalProductsContainer.css('max-height', height);
			$additionalProductsContainer.css('height', height);
			
			ACC.common.refreshScreenReaderBuffer();
			$.colorbox.resize();
		},

		onClosed: function ()
		{
			ACC.common.refreshScreenReaderBuffer();
		}
	});
}

function refreshShoppingBag() {
	if (typeof ACC.minicart.refreshMiniCartCount == 'function')
	{
		ACC.minicart.refreshMiniCartCount();
	}
}

function configureBuyTheSet() {
	var addToCartForm = $('.productSetAddToCartForm');
	addToCartForm.ajaxForm({
		success: function (data) {
					
			if (typeof ACC.minicart.refreshMiniCartCount == 'function')
			{
				ACC.minicart.refreshMiniCartCount();
			}
			
			$(HEADER).append(data.cartPopupHtml);
			
			var addToCart = $(ADD_TO_CART);
			addToCart.fadeIn(function(){
				if (typeof timeoutId != 'undefined')
				{
					clearTimeout(timeoutId);
				}
				timeoutId = setTimeout(function ()
				{
					addToCart.fadeOut(function(){
						addToCart.remove();
						
					});
				}, 5000);
				
			});
			$.colorbox.close();
		}
		});
	
	if(!BIND_STATE) {		
		$(BUTTON_ADD_TO_CART).live('click',function(e){		
			e.preventDefault();
			var productCode = $(this).data("productcode");
			var urlToCart = $(this).data("urltocart");
			var codeToSend = productCode;
			var quantity = $("#product_quantity_"+productCode).val();
			var variantCode = $("#variant_product_"+productCode);
			var $button = $(this);
			
			if(variantCode.length > 0) {
				codeToSend = variantCode.val();
			}
			
			if(codeToSend != "") {
				$button.attr("disabled", "disabled");			
				$.post(urlToCart, {quantity: quantity, productCode: codeToSend}, function(data) {
					$button.removeAttr("disabled");
					refreshShoppingBag();
				}).fail(function() {
					$button.removeAttr("disabled");
				});
			}
			return false;
		});
		
		$(FIELD_PRODUCT_QUANTITY).live('change',function(e) {
			e.preventDefault();
			toggleBuySetButton();
					
			var $this = $(this);
			var $parent = $(this).closest('.item_details');
			var isOutOfStock = $this.hasClass(OUT_OF_STOCK_STYLE);
			
			if($(this).val() == 0) {
				$parent.find('.addToCartButton').hide();
			} else if($(this).val() > 0 && !isOutOfStock) {
				$parent.find('.addToCartButton').show();
			}
		});
		BIND_STATE = true;
	}
	
	toggleBuySetButton();
	
}

function toggleBuySetButton() {
	var counter = 0;
	
	$(FIELD_PRODUCT_QUANTITY).each(function(index,item) {
		var $this = $(item);
		var value = parseInt($this.val());
		var isOutOfStock = $this.hasClass(OUT_OF_STOCK_STYLE);
		if(value > 0 && !isOutOfStock) {
			counter++;
		}
		if(isOutOfStock) {
			$(this).parent().children('.stock_message').hide();
		}
	});
	
	if(counter >= 2) {
		$(BUY_SET_BUTTON).removeAttr("disabled");
	} else {
		$(BUY_SET_BUTTON).attr("disabled", "disabled");
	}
}

function getElementWidth() {
	var dummyElem = $("<div id=\"productSetWidthDummyElem\"></div>").addClass("productset-elem-wide").appendTo("<body>");
	var elementWidth =  dummyElem.width()
	dummyElem.remove();
	return elementWidth;
}

function calculateMaxTotalElementsInRow(availableWidth) {
	return Math.floor(availableWidth / getElementWidth());	
}

function configureProductSetStyleSelectChange() {
	$(".leaf_change_select").live('change', function(e) {
		e.preventDefault();
		
		var variantCode = $(this).val();
		var currentProductCode = $(this).data("productcode");
		var RELOAD_VARIANT_URL = $(this).data("reloadurl");
		var baseProductCode = $(this).data("baseproductcode");
		var $button = $('#add_to_cart_'+baseProductCode);
		var CONTAINER_SELECTOR = "#product_container_" + baseProductCode;
		
		
		if(variantCode != "") {			
			togglePreloader(CONTAINER_SELECTOR, true);
			
			$.get(RELOAD_VARIANT_URL, {productCode: variantCode, baseProductCode: baseProductCode}, function(data) {
				$(CONTAINER_SELECTOR).html(data);
				if($button.length > 0) {
					if(!$button.hasClass("OUT_OF_STOCK_STYLE")) {
						$button.show();
					} else {
						$button.hide();
					}
				}
				togglePreloader(CONTAINER_SELECTOR, false);
				toggleBuySetButton();
				$.colorbox.resize();
			}).fail(function() {
				togglePreloader(CONTAINER_SELECTOR, false);
				toggleBuySetButton();
				$.colorbox.resize();
			});
			
		} else {
			$button.hide();
			hideStockLevel(baseProductCode);
			toggleBuySetButton();
		}
	});
}

function togglePreloader(container_id, show) {
	var PRELOADER_CLASS = "data-preloader";
	var $container = $(container_id);
	if(show) {
		var htmlData = "<div class=\""+PRELOADER_CLASS+"\"></div>";
		$container.append(htmlData);
	} else {
		$(container_id + "." + PRELOADER_CLASS).remove();
	}
}

function hideStockLevel(baseProductCode) {
	var $stock = $("#stock_info_" + baseProductCode);
	var $quantity = $("#product_quantity_" + baseProductCode);

	$stock.hide();
	$quantity.val(0);
	$quantity.prop('disabled', true);

	toggleBuySetButton();
}