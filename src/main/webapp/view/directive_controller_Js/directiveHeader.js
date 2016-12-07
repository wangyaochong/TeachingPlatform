app.directive('directiveHeader', function () {
    return {
        restrict: 'E',
        templateUrl: 'view/directiveHtml/directiveHeader.html',
        replace: true,
        controller:function ($scope,$http) {
            $http.get("headerUrl").success(function (data) {
                $scope.header=data
            })
        }
    }
})