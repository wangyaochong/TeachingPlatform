app.directive('headerDirective', function () {
    return {
        restrict: 'E',
        templateUrl: 'view/directiveHtml/headerDirective.html',
        replace: true,
        controller:function ($scope,$http) {
            $http.get("headerUrl").success(function (data) {
                $scope.header=data
            })
        }
    }
})