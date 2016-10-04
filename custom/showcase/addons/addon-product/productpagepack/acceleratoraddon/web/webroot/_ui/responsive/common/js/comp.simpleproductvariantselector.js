var SELECTOR_SIZE_FIELD = "#Size";
var SELECTOR_STYLE_FIELD = "#variant";
var SELECTOR_STYLE_OPTION = SELECTOR_STYLE_FIELD + " option:selected";
var SELECTOR_SIZE_OPTION = SELECTOR_SIZE_FIELD + " option:selected";

$(document).ready(function() {
	$(SELECTOR_SIZE_FIELD).change(function () {
		onVariantChangeEvent(SELECTOR_SIZE_OPTION);
	});

	$(SELECTOR_STYLE_FIELD).change(function () {
		onVariantChangeEvent(SELECTOR_STYLE_OPTION);
	});
});

function onVariantChangeEvent(selector_item) {
	var url = "";
	var selectedIndex = 0;
	$(selector_item).each(function () {
		url = $(this).attr('value');
		selectedIndex = $(this).attr("index");
	});
	if (selectedIndex != 0) {
		window.location.href=url;
	}
}