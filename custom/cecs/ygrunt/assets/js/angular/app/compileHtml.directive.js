'use strict';

angular.module('tricastApp')
    .directive("compileHtml", function($parse, $sce, $compile) {
        return {
            restrict: "A",
            link: function (scope, element, attributes) {

                var expression = $sce.parseAsHtml(attributes.compileHtml);

                var getResult = function () {
                    return expression(scope);
                };

                scope.$watch(getResult, function (newValue) {
                    var linker = $compile(newValue);
                    element.html(linker(scope));
                });
            }
        }
    });
