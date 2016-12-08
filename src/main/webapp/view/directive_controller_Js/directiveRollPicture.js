app.directive('directiveRollPicture', function () {
    return {
        restrict: 'E',
        templateUrl: 'view/directiveHtml/directiveRollPicture.html',
        replace: true,
        controller:function ($scope,$http) {
            $http.get("rollPictureUrl").then(function (data) {
                $scope.pictures=data.data
            })
        }
    }
})

