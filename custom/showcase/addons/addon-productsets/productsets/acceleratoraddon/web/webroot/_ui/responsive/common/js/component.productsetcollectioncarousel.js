$(function() {

    ProductSetCollectionCarouselCallback = {

        component_selector: "",
        component: null,
        DATA_ITEMS_PER_ROW: "data-items-row",
        SELECTOR_ITEM: ".item",
        SELECTOR_INNER: ".carousel-inner",
        CSS_CLASS_ACTIVE: "active",
        CSS_CLASS_ITEM: "item",

        init: function(component_selector) {
            this.component_selector = component_selector;
            this.component = $(this.component_selector);

            if(this.component.length > 0) {
                this.bindSmartphone();
            }
        },

        bindSmartphone: function() {
            var _this = this;
            enquire.register("only screen and (max-width: 767px)", {
                match: function () {
                    _this.buildForSmartphone();
                },

                unmatch: function () {
                    _this.buildDefault();
                }
            });
        },

        buildForSmartphone: function() {
            var $innerContainer = this.component.children(this.SELECTOR_INNER);
            var rows = $innerContainer.first().children(this.SELECTOR_ITEM);
            var itemsList = [];
            var is_first = true;
            var _this = this;

            $.each(rows,  function(index, row) {
                var items = $(row).children().each(function(index, item) {
                    itemsList.push(item);
                });
                items.remove();
            });

            $innerContainer.children().remove();

            $.each(itemsList, function(index, item) {
                var container = $("<div></div>");
                container.addClass(_this.CSS_CLASS_ITEM);
                if(is_first) {
                    is_first = false;
                    container.addClass(_this.CSS_CLASS_ACTIVE);
                };
                container.append(item);
                $innerContainer.append(container);
            });
        },

        buildDefault: function() {
            var _this = this;
            var perRow = this.component.attr(this.DATA_ITEMS_PER_ROW);
            var $innerContainer = this.component.children(this.SELECTOR_INNER);
            var columns = $innerContainer.first().children(this.SELECTOR_ITEM);
            var itemsList = [];

            $.each(columns,  function(index, column) {
                $(column).children().each(function(index, item) {
                    itemsList.push(item);
                });;
            });

            $innerContainer.children().remove();

            var iteration = Math.ceil(itemsList.length / perRow);

            for(var i = 0; i < iteration; i++) {
                var $row = $("<div></div>").addClass(_this.CSS_CLASS_ITEM);
                var counter = 0;
                if(i == 0) {
                    $row.addClass(_this.CSS_CLASS_ACTIVE);
                }
                $.each(itemsList,  function(index, item) {
                    if(counter >= perRow) {
                        return false;
                    }
                    var indexOfItem = counter + (perRow * i);
                    $row.append(itemsList[indexOfItem]);
                    counter++;
                });

                $innerContainer.append($row);
            }
        }

    };

});
