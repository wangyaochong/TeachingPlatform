app.directive('directiveFoot', function () {
    return {
        restrict: 'E',
        templateUrl: directiveHtmlUrl+'directiveFoot.html',
        replace: true,
        controller:function ($scope,$http) {
            $http.get(webRootUrl+"headerUrl").then(function (data) {
                $scope.header=data.data
            })
        }
    }
})