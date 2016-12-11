app.directive('directiveRollPicture', function () {
    return {
        restrict: 'E',
        templateUrl: directiveHtmlUrl+'directiveRollPicture.html',
        replace: true,
        controller:function ($scope,$http) {
            $http.get(webRootUrl+"getRollPicture").then(function (data) {
                $scope.pictures=data.data
            })
        }
    }
})

