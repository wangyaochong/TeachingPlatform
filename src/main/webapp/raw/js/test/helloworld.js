var myApp = angular.module('app', ['ui.router']);
myApp.config(function($stateProvider,$urlRouterProvider) {
    $stateProvider.state('hello',{
        url: '/hello',
        templateUrl: 'index.hello.html',
        controller:function ($scope,$state) {
            $scope.name="wangyaochong";
            $scope.world="+++good";
            $state.go("about.test",{world:$scope.world})
    }});
    var aboutState ={
        name:'about',
        url: '/about?world',
        templateUrl: 'index.world.html',
        controller:function ($scope,$stateParams) {
            $scope.world=$stateParams.world;
        }
    };

    var testState ={
        name:'about.test',
        url: '/test',
        templateUrl: 'index.world.test.html',
    };

    var testState2={
        name:'about.test2',
        url: '/test2?world2',
        templateUrl: 'index.world.test2.html',
        controller:function ($scope,$stateParams) {
            $scope.world=$stateParams.world2;
        }
    };
    $stateProvider
       // .state(helloState)
        .state(aboutState)
        .state(testState)
        .state(testState2);

    $urlRouterProvider.when('','/hello')
});
$(document).ready(function () {
    angular.bootstrap(document,['app']);
});