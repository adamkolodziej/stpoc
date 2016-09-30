ACC.productList = {
	bindAll: function ()
	{
		ACC.productList.bindUpdateNotesForm();
		ACC.productList.bindListUpdateProduct();
		ACC.productList.initUpdateButtons();
		plConfigureDraggable();
	},
	
	bindUpdateNotesForm: function ()
	{
		var updatedNotesForm = $('.update_productlist_notes_form');
		updatedNotesForm.ajaxForm({beforeSubmit: ACC.productList.trimNotes, success: ACC.productList.onNotesUpdate});
	},
	
	initUpdateButtons: function ()
	{
		$('.update_productlist_notes_form > .form-field-button  > :button').attr("disabled", "disabled");
		$('.update_productlist_notes_form > .form_field-input > .text').keyup(function() {
			var p = $(this).closest('.update_productlist_notes_form');
			$(p).find('.form-field-button > :button').removeAttr("disabled");
		});
	},
	
	onNotesUpdate: function (notesUpdateResult, statusText, xhr, formElement)
	{
		$(formElement).find('.form-field-button > :button').attr("disabled", "disabled");
	},
	
	trimNotes: function (arr, formElement, options)
	{
		var text = $(formElement).find('.form_field-input > .text');
		$(text).val($.trim(text.val()));
	},
	
	bindListUpdateProduct: function ()
	{

		$('.updateQuantityListProduct').on("click", function ()
		{
			var prodid = $(this).attr('id').split("_")
			prodid = prodid[1];
			$('#updateListQtyForm' + prodid).get(0).submit();

		});
	}

};

$(document).ready(function ()
{
	ACC.productList.bindAll();
	addToWishList();
});

//-----------------------------------------------------------------------------
function plConfigureDraggable() {
	$('#sortable').sortable({ handle: "span.dragndrop",  axis: "y" , cursor: "move", opacity: "1.0", placeholder: "dragndrop-placeholder", 
		stop: function( event, ui ) {
    	var guid = $('#productListDataGuid').val();
    	var productlistsReorderUrl = $('#productlistsReorderUrl').val();
    	var entry = ui.item.attr('id').replace(/^\D+/g, '');
    	var nextObjId = ui.item.next().attr('id');
    	var before = nextObjId != null ? nextObjId.replace(/^\D+/g, '') : null;
    	
    	$.get(productlistsReorderUrl, {
    		guid: guid,
    		before: before,
    		move: entry 
        }, function(data) {
        	//empty
    	});	
    }});
}

function plAddToProductList(plAddItemUrl, productCode) {
	var qty = $('#qty');
	qty = qty == null ? 1 : qty.val();
	if (!isInt(qty)) {
		$('#qty').val('1');
		qty = 1;
	}
	$.post(plAddItemUrl, {
		productCodePost: productCode,
		quantity: qty
    }, function(data) {
    	$('.wishlist').addClass( 'miniCartWishlistActive' );
    	setTimeout(function(){$('.wishlist').removeClass("miniCartWishlistActive")},1000);
		alert(ACC.addons.productlists['productlists.product.added']);
		$('#addToProdListButton').hide();
	});
}

function isInt(n) {
    var intRegex = /^\d+$/;
    return intRegex.test(n);
}

function addToWishList() {
    var $productList = $('.js-add-to-product-list');
    $productList.on("click", function() {
        var qty = $('#qty');
        qty = qty == null ? 1 : qty.val();
        if (!isInt(qty)) {
            $('#qty').val('1');
            qty = 1;
        }
        $.post($productList.attr('data-url'), {
            productCodePost: $productList.attr('data-product-code'),
            quantity: qty
        }, function(data) {
            $('.wishlist').addClass('miniCartWishlistActive');
            setTimeout(function() {
                $('.wishlist').removeClass("miniCartWishlistActive")
            }, 1000);
            alert(ACC.addons.productlists['productlists.product.added']);
            $productList.attr("disabled", true);
        });
    });
}
