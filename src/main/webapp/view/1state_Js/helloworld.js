
//如果是直接调用$stateProvider，则有两个参数，第一个是state的名字，第二个是state的定义
$stateProvider.state('hello',{
        url: '/hello',
        templateUrl:templateHtmlUrl+'index.hello.html',
        controller:function ($scope,$state,$rootScope) {
            $scope.name="wangyaochong";
            $scope.world="+++good";
            $rootScope.rootworld='rootWorld';
            $state.go("about.test",{world:$scope.world})
    }});
    var aboutState ={
        name:'about',
        url: '/about?world',
        templateUrl: templateHtmlUrl+'index.world.html',
        controller:function ($scope,$stateParams,$rootScope) {
            $scope.world=$stateParams.world;
            $rootScope.rootWorld='rootWorld'
        }
    };

    var testState ={
        name:'about.test',
        url: '/test',
        templateUrl: templateHtmlUrl+'index.world.test.html',
    };

    var testState2={
        name:'about.test2',
        url: '/test2?world2',
        templateUrl:templateHtmlUrl+'index.world.test2.html',
        controller:function ($scope,$stateParams) {
            $scope.world=$stateParams.world2;
        }
    };

    $stateProvider
       // .state(helloState)
        .state(aboutState)
        .state(testState)
        .state(testState2);

