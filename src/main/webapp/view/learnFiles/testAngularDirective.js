angular.module('myApp', [])
    .directive('helloDirective', function () {
        return {
            restrict: 'E',
            templateUrl: 'directive.html',
            replace: true,
            controller:function ($scope) {
                $scope.sayHello=function () {
                    console.log($scope.name)
                    $scope.name='origin'
                }
            }
        }
    })
    .controller('testController', function ($rootScope, $scope) {

    })
