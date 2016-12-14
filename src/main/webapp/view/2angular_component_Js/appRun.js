/**
 * Created by 【王耀冲】 on 【2016/12/13】 at 【15:16】.
 */
app.run(function ($rootScope,ItemEntityService) {
    $rootScope.stateRootUrl="/TeachingPlatform/view/5html/index.html#!/";
    $rootScope.screenWidth=window.screen.width;
    $rootScope.screenHeight=window.screen.height;
    $rootScope.revertItemEntity = function (data) {
        //取消编辑的内容
        angular.copy(data.dataCopy, data);
    }
    $rootScope.saveItemEntity=function (data) {
        //保存编辑的内容到副本
        ItemEntityService.updateItemEntity(data)
        angular.copy(data, data.dataCopy);
    }
    $rootScope.deleteItemEntity=function (data,$index,list) {
        list.splice($index,1);
        ItemEntityService.deleteItemEntity(data);
    }
    // console.log("screenWidth:"+$rootScope.screenWidth);
    // console.log("screenHeight"+$rootScope.screenHeight);
    // window.onresize=function () {
    //     $rootScope.$apply(function () {
    //         $rootScope.screenWidth=window.screen.width;
    //         $rootScope.screenHeight=window.screen.height;
    //     })
    //     console.log("screenWidth:"+$rootScope.screenWidth);
    //     console.log("screenHeight"+$rootScope.screenHeight);
    // }
})