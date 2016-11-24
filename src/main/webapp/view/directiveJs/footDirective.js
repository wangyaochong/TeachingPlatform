app.directive('footDirective', function () {
    return {
        restrict: 'E',
        templateUrl: 'view/directiveHtml/footDirective.html',
        replace: true,
        controller:function ($scope,$http) {
            $http.get("headerUrl").success(function (data) {
                $scope.header=data
            })
        }
    }
})