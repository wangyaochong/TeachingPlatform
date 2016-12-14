app.directive('directiveRollPicture', function () {
    return {
        restrict: 'E',
        templateUrl: directiveHtmlUrl+'directiveRollPicture.html',
        replace: true,
        controller:function ($scope,$http) {
            //当在controller中指定了controller的名字，或者函数，则在对应的html页面中，则不需要再使用ng-controller指令绑定controller
            $http.get(webRootUrl+"RollPicture/getRollPicture").then(function (data) {
                $scope.pictures=data.data
            })
        }
    }
})

