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
            $timeout(function () {
                Galleria.loadTheme("/TeachingPlatform/view/js/themes/classic/galleria.classic.min.js");//加载主题
                Galleria.configure({
                    transitionSpeed: 500,
                    imageCrop: true,
                    thumbCrop: true,
                    carousel:false
                });
                Galleria.run("#galleria")
            }, 0)


            //当在controller中指定了controller的名字，或者函数，则在对应的html页面中，则不需要再使用ng-controller指令绑定controller
            $http.get(webRootUrl+"RollPicture/getRollPicture").then(function (data) {
                $scope.pictures=data.data

            })
            if(Galleria){
                console.log("galleria installed")
            }


        }
    }
})