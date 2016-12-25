/**
 * Created by 【王耀冲】 on 【2016/12/15】 at 【20:07】.
 */
app.directive('directiveGalleria', function () {
    return {
        restrict: 'E',
        templateUrl: directiveHtmlUrl+'directiveGalleria.html',
        replace: true,
        controller:function ($scope,$http,$timeout) {
            //由于指令需要angularJS加载完成，所以不能放在document.ready事件中，需要等angular加载完成才能进行
            //其他库的初始化
            //当在controller中指定了controller的名字，或者函数，则在对应的html页面中，则不需要再使用ng-controller指令绑定controller
            $http.get(webRootUrl+"RollPicture/getRollPicture").then(function (data) {
                $scope.pictures=data.data;
                $timeout(function () {
                    $('.slick').slick({
                        // dots: true,
                        infinite:false,
                        zIndex:0,
                        slidesToShow:5,
                        arrows:true,
                        autoplay:true,
                        prevArrow: '<a class="left carousel-control" style="z-index: 1000;width: 50px!important;" role="button">' +
                        ' <span' +
                        ' class="glyphicon glyphicon-chevron-left"></span></a>',
                        nextArrow:'<a class="right carousel-control" style="z-index: 1000;width: 50px!important;"role="button">' +
                        ' <span' +
                        ' class="glyphicon glyphicon-chevron-right"></span> </a>'
                    });
                })
            })

        }
    }
})