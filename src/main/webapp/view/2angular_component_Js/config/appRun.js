/**
 * Created by 【王耀冲】 on 【2016/12/13】 at 【15:16】.
 */
var userCenterStates = [
    'userCenter.userList',
    'userCenter.userInformation',

]
var courseCenterStates=[
    'courseCenter.teacher',
    'courseCenter.student',
    'courseCenter.teacherResource',
]
app.run(function ($rootScope, ItemEntityService, UserService, CRUDHtmlService, CRUDService) {
    // CRUDService.getMethod("Locale/getLocale",{}).then(function (response) {
    //     $rootScope.localeLange=response.data;
    // })
    var language_en_us = "en-us";
    var language_zh_cn = "zh-cn";
    var currentLang;
    currentLang = navigator.language;
    if(!currentLang)
        currentLang = navigator.browserLanguage;
    if(currentLang.toLowerCase() == language_zh_cn)
    {
        $rootScope.localeLange="zh_CN"
    }
    else
    {
        $rootScope.localeLange="en_US"
    }

    CRUDService.getMethod("Locale/getLocaleProperties",{}).then(function (response) {
        $rootScope.localeProperties=response.data;
    },function (error) {
        console.log(error);
    })


    $rootScope.$on('$stateChangeStart',
        function (event, toState, toParams, fromState, fromParams) {
            if (toState.name == "userCenter") {
                if (userCenterStates.indexOf(fromState.name) != -1) {
                    event.preventDefault();//禁止切换
                }
            }
            if (toState.name=="courseCenter") {
                if (courseCenterStates.indexOf(fromState.name) != -1) {
                    event.preventDefault();//禁止切换
                }
            }
        })


    //每一个controllerXXX对应一个controllerXXX.html页面
    //directive也是类似的

    generateUrl($rootScope, "controller", controllerNames);
    generateUrl($rootScope, "directive", directiveNames);

    //注册模版路径，比如$rootScope.controllerRollPictureUrl
    //="/TeachingPlatform/view/3templateHtml/controllerRollPicture.html
    $rootScope.stateRootUrl = "/TeachingPlatform/view/5html/index.html#!/";
    $rootScope.templateHtmlUrl = "/TeachingPlatform/view/3templateHtml/";
    $rootScope.screenWidth = window.screen.width;
    $rootScope.screenHeight = window.screen.height;



    //定义全局变量，后续会用得到
    $rootScope.ItemType=ItemType;
    $rootScope.PrivilegeType=PrivilegeType;
    $rootScope.PrivilegeMap=PrivilegeMap;


    // $timeout(function () {//触发一下滚动大屏
    //     $('.carousel').carousel('next')
    // },5000)
    $rootScope.revertItemEntity = function (data, list) {
        CRUDHtmlService.revertObject(data, list);
    }
    $rootScope.saveItemEntity = function (data) {
        //保存编辑的内容到副本
        var response = ItemEntityService.updateItemEntity(data)
        CRUDHtmlService.saveObject(data, response);
    }

    $rootScope.deleteItemEntity = function (data, index, list) {
        CRUDHtmlService.deleteObject(index, list);
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
        if (type != ItemType.ANNOUNCEMENT) {//首页消息可以直接添加，但是带有附件的不能，需要上传附件才行
            CRUDHtmlService.addObject(itemEntity, list)
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