'use strict';

angular.module('tricastApp', [
    "ngSanitize",
    "com.2fdevs.videogular",
    "com.2fdevs.videogular.plugins.controls",
    "com.2fdevs.videogular.plugins.poster",
    "ui.bootstrap"
]).config(function($locationProvider){
    $locationProvider.html5Mode({
        enabled: false,
        requireBase: false
    });
});
