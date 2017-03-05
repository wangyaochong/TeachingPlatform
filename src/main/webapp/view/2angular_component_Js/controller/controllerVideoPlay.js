/**
 * Created by wangy on 2017/1/15.
 */
app.controller("controllerVideoPlay",function ($scope,CRUDService,$timeout,$stateParams) {
    $scope.videoPlay={};
    $scope.item={};
    CRUDService.getMethod("ItemEntity/getItemEntityById",{id:$stateParams.itemId}).then(function (response) {
        $scope.videoPlay=response.data.resources[0];
        $scope.item=response.data;
        // $scope.videoPlay.htmlFilePath = $scope.videoPlay.htmlFilePath.replace(/\\/g, "/");
        $timeout(function () {
            //这个地方需要根据class获取对象初始化，如果根据id初始化，等第二次进入时会导致重复初始化。
            var player = videojs($(".video-js")[0], {
                "class":"video-js vjs-big-play-centered",
                 "techOrder":["flash","html5"],//优先使用flash
                // "playbackRates": [0.5, 1, 1.5, 2]
            }, function() {});
        },50)
        console.log(response);
        },function (error) {
            console.log(error)
        }
    )
    console.log('controllerVideoPlay')
})