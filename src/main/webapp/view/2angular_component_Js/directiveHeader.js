app.directive('directiveHeader', function () {
    return {
        restrict: 'E',
        templateUrl: directiveHtmlUrl+'directiveHeader.html',
        replace: true,
        controller:function ($scope,$http,$q) {
            $http.get(webRootUrl+"getHeader").then(function (data) {
                $scope.header=data.data
            })
        }
    }
})