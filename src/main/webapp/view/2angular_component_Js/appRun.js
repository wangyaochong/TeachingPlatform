/**
 * Created by 【王耀冲】 on 【2016/12/13】 at 【15:16】.
 */
app.run(function ($rootScope, ItemEntityService, $timeout) {
    
    $rootScope.stateRootUrl = "/TeachingPlatform/view/5html/index.html#!/";
    $rootScope.screenWidth = window.screen.width;
    $rootScope.screenHeight = window.screen.height;
    //定义全局itemType，后续会用到
    $rootScope.ItemType = {
        DOCUMENT: "DOCUMENT",
        EMAIL: "EMAIL",
        ANNOUNCEMENT: "ANNOUNCEMENT",
        VIDEO: "VIDEO",
        ASSIGNMENT: "ASSIGNMENT",
        SOLUTION: "SOLUTION"
    }


    // $timeout(function () {//触发一下滚动大屏
    //     $('.carousel').carousel('next')
    // },5000)
    $rootScope.revertItemEntity = function (data, list) {
        //取消编辑的内容
        angular.copy(data.dataCopy, data);
        if (data.id == undefined || data.id == "") {//如果是取消新增的内容，则回退
            list.shift();
        }
    }
    $rootScope.saveItemEntity = function (data) {
        //保存编辑的内容到副本
        var result = ItemEntityService.updateItemEntity(data)
        if (data.id == undefined || data.id == "") {
            result.then(function (response) {
                data.id = response.data;
            })
        }
        angular.copy(data, data.dataCopy);
    }
    $rootScope.deleteItemEntity = function (data, index, list) {
        list.splice(index, 1);
        ItemEntityService.deleteItemEntity(data);
    }
    $rootScope.addItemEntity = function (list, type) {
        var itemEntity = {
            id: "",//id
            title: "",//标题
            description: "",//描述
            type: type,
            isOpen: true,
            createDate: new Date().getTime(),
            dataCopy: {},
            isEditing: true
        }
        list.unshift(itemEntity);
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
    }
})