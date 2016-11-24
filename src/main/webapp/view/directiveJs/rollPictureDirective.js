app.directive('rollPictureDirective', function () {
    return {
        restrict: 'E',
        templateUrl: 'view/directiveHtml/rollPictureDirective.html',
        replace: true,
        controller:function ($scope,$http) {
            $http.get("rollPictureUrl").success(function (data) {
                $scope.pictures=data
            })
        }
    }
})

