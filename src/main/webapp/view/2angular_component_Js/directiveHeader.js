app.directive('directiveHeader', function () {
    return {
        restrict: 'E',
        templateUrl: directiveHtmlUrl+'directiveHeader.html',
        replace: true,
        controller:function ($scope,$http,$q) {
            $http.get(webRootUrl+"Header/getHeaderUrl").then(function (data) {
                $scope.header=data.data
            })
        }
    }
})