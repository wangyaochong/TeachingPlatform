var debounce = function (func, threshold, execAsap) {
    var timeout;
    return function debounced () {
        var obj = this, args = arguments;
        function delayed () {
            if (!execAsap)
                func.apply(obj, args);
            timeout = null;
        };
        if (timeout)
            clearTimeout(timeout);
        else if (execAsap)
            func.apply(obj, args);
        timeout = setTimeout(delayed, threshold || 100);
    };
}


var directiveCourseBlockWidth="20%";
var directivePictureBlockWidth="20%";


window.onresize =function () {
    $(".courseblockwrapper").each(function () {
        $(this).width(directiveCourseBlockWidth);
        //必须要多设置几次，不然会出错
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $(this).height($(this).width());//让高度等于宽度
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
        $("#newRollPictureDiv").css("height",$("#newRollPictureDiv").width())
        $("#newRollPictureDiv").css("height",$("#newRollPictureDiv").width())
        $("#newRollPictureDiv").css("height",$("#newRollPictureDiv").width())
        $("#newRollPictureDiv").css("height",$("#newRollPictureDiv").width())
        $("#newRollPictureDiv").css("height",$("#newRollPictureDiv").width())
        $("#newRollPictureDiv").css("height",$("#newRollPictureDiv").width())
    })
}


var webRootUrl = "/TeachingPlatform/"
var templateHtmlUrl = "/TeachingPlatform/view/3templateHtml/";
var directiveHtmlUrl = "/TeachingPlatform/view/4directiveHtml/";
var maxPageRowSize = 1000000000;
var templateUrls = {};//用来保存模版路径
//全局定义的itemEntity类型
var ItemType = {
    DOCUMENT: "DOCUMENT",
    EMAIL: "EMAIL",
    ANNOUNCEMENT: "ANNOUNCEMENT",
    VIDEO: "VIDEO",
    ASSIGNMENT: "ASSIGNMENT",
    SOLUTION: "SOLUTION",
    ROLLPICTURE: "ROLLPICTURE"
}
var PrivilegeType = {
    FRONT_MESSAGE: "FRONT_MESSAGE",
    DOCUMENT: "DOCUMENT",
    VIDEO: "VIDEO",
    ASSIGNMENT: "ASSIGNMENT",
    USER_MANAGEMENT: "USER_MANAGEMENT",
    GROUP_EDIT: "GROUP_EDIT",
    SUPER: "SUPER"
}
var PrivilegeMap={};
PrivilegeMap[PrivilegeType.FRONT_MESSAGE]="首页编辑";
PrivilegeMap[PrivilegeType.DOCUMENT]="文档编辑";
PrivilegeMap[PrivilegeType.VIDEO]="视频编辑";
PrivilegeMap[PrivilegeType.ASSIGNMENT]="作业编辑";
PrivilegeMap[PrivilegeType.USER_MANAGEMENT]="用户管理";
PrivilegeMap[PrivilegeType.GROUP_EDIT]="班级管理";
PrivilegeMap[PrivilegeType.SUPER]="超级权限";





//由于菜单数极少，所以不考虑使用侧边菜单
// var sideMenu=[
//     {
//         menuTitle:"用户中心",
//         menus:[
//             {
//                 name:'个人信息',
//                 url:'personalInfomation'
//             },
//             {
//                 name:'课程管理',
//                 url:'courseManage'
//             },
//             {
//                 name:'权限管理',
//                 url:'courseManage'
//             }
//         ]
//     }
// ]

//由于这个函数没有定义，所以手动定义
angular.isUndefinedOrNull = function (val) {
    return angular.isUndefined(val) || val === null
}
//以上定义的是全局js变量，不用放到rootScope中
// var app = angular.module('app', ['ngTouch','ngAnimate','ui.router', 'ngTable','ui.bootstrap']);
var app = angular.module('app', ['ui.bootstrap','ui.router', 'ngTable' ,'ui.select','ngSanitize']);
app.config(function ($stateProvider, $urlRouterProvider)
{"##begin##"})


