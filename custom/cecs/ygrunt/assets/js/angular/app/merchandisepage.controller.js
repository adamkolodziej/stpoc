'use strict';

angular.module('tricastApp')
    .controller('MerchandisePageCtrl', function ($scope) {
        $scope.highlightList = [
            {
                title:'Best Selling',
                subtitle:'Most Popular Highlights',
                merchandiseList:[
                    {
                        title:'Armageddon - Book to the movie',
                        rating:'5',
                        price:{
                            regular:19.99,
                            discount:0
                        },
                        promotion:false,
                        exclusive:false,
                        picture:'bs-1.jpg'
                    },
                    {
                        title:'Brother Roses Classic T-shirt',
                        rating:'5',
                        price:{
                            regular:9.99,
                            discount:0
                        },
                        promotion:false,
                        exclusive:false,
                        picture:'bs-2.jpg'
                    },
                    {
                        title:'Joy Coffee Mug',
                        rating:'5',
                        price:{
                            regular:21.99,
                            discount:0
                        },
                        promotion:false,
                        exclusive:true,
                        picture:'bs-4.jpg'
                    },
                    {
                        title:'Rebellion Womans T-Shirt',
                        rating:'5',
                        price:{
                            regular:19.99,
                            discount:9.99
                        },
                        promotion:true,
                        exclusive:false,
                        picture:'bs-5.jpg'
                    }
                ]

            }
        ]
    });
