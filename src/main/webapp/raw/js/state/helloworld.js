
$stateProvider.state('hello',{
        url: '/hello',
        templateUrl:'view/html/index.hello.html',
        controller:function ($scope,$state,$rootScope) {
            $scope.name="wangyaochong";
            $scope.world="+++good";
            $rootScope.rootworld='rootWorld';
            $state.go("about.test",{world:$scope.world})
    }});
    var aboutState ={
        name:'about',
        url: '/about?world',
        templateUrl: 'view/html/index.world.html',
        controller:function ($scope,$stateParams,$rootScope) {
            $scope.world=$stateParams.world;
            $rootScope.rootWorld='rootWorld'
        }
    };

    var testState ={
        name:'about.test',
        url: '/test',
        templateUrl: 'view/html/index.world.test.html',
    };

    var testState2={
        name:'about.test2',
        url: '/test2?world2',
        templateUrl:'view/html/index.world.test2.html',
        controller:function ($scope,$stateParams) {
            $scope.world=$stateParams.world2;
        }
    };

    $stateProvider
       // .state(helloState)
        .state(aboutState)
        .state(testState)
        .state(testState2);

