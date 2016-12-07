app.directive('directiveRollPicture', function () {
    return {
        restrict: 'E',
        templateUrl: 'view/directiveHtml/directiveRollPicture.html',
        replace: true,
        controller:function ($scope,$http) {
            $http.get("rollPictureUrl").success(function (data) {
                $scope.pictures=data
            })
        }
    }
})

