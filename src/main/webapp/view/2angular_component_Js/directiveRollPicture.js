app.directive('directiveRollPicture', function () {
    return {
        restrict: 'E',
        templateUrl: directiveHtmlUrl + 'directiveRollPicture.html',
        replace: true,
        controller: function ($scope, $http, $timeout) {
            //当在controller中指定了controller的名字，或者函数，则在对应的html页面中，则不需要再使用ng-controller指令绑定controller
            $http.get(webRootUrl + "RollPicture/getRollPicture").then(function (data) {
                $scope.pictures = data.data
                initDirectiveRollPicture($scope);//进行angularJS框架以外的初始化工作
            })
        }
    }
})
function initDirectiveRollPicture($scope) {
    setTimeout(function () {
        autosize(document.querySelectorAll('textarea'));
        $('#carousel-example-generic').on('slide.bs.carousel', function () {
            autosize(document.querySelectorAll('textarea'));
        })
        $('#carousel-example-generic').on('slid.bs.carousel', function () {
            autosize(document.querySelectorAll('textarea'));
            var ta = document.querySelector('textarea');
            ta.style.display = 'none';
            autosize(ta);
            ta.style.display = '';
        })
        $('#slickContainer').css("width", $scope.pictures.pictures.length * 100 + "px");
        $('.slick').slick({
            // dots: true,
            infinite: false,
            zIndex: 0,
            slidesToShow: 6,
            arrows: true,
            autoplay: false,
            prevArrow: '<a class="left carousel-control" style="z-index: 1000;width: 50px!important;" role="button">' +
            ' <span' +
            ' class="glyphicon glyphicon-chevron-left"></span></a>',
            nextArrow: '<a class="right carousel-control" style="z-index: 1000;width: 50px!important;"role="button">' +
            ' <span' +
            ' class="glyphicon glyphicon-chevron-right"></span> </a>'
        });

    }, 50)
}

