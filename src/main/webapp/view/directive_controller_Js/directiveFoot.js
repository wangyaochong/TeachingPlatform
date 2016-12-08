app.directive('directiveFoot', function () {
    return {
        restrict: 'E',
        templateUrl: 'view/directiveHtml/directiveFoot.html',
        replace: true,
        controller:function ($scope,$http) {
            $http.get("headerUrl").then(function (data) {
                $scope.header=data.data
            })
        }
    }
})