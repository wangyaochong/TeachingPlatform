app.directive('directiveHeader', function () {
    return {
        restrict: 'E',
        templateUrl: 'view/directiveHtml/directiveHeader.html',
        replace: true,
        controller:function ($scope,$http,$q) {
            $http.get("headerUrl").then(function (data) {
                $scope.header=data.data
            })
        }
    }
})