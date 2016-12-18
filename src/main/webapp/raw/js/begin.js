var webRootUrl="/TeachingPlatform/"
var templateHtmlUrl="/TeachingPlatform/view/3templateHtml/";
var directiveHtmlUrl="/TeachingPlatform/view/4directiveHtml/";
var maxPageRowSize=1000000000;
//全局定义的itemEntity类型
var ItemType={
    DOCUMENT:"DOCUMENT",
    EMAIL:"EMAIL",
    ANNOUNCEMENT:"ANNOUNCEMENT",
    VIDEO:"VIDEO",
    ASSIGNMENT:"ASSIGNMENT",
    SOLUTION:"SOLUTION",
    ROLLPICTURE:"ROLLPICTURE"
}




//以上定义的是全局js变量，不用放到rootScope中
var app = angular.module('app', ['ui.router','ngTable']);
app.config(function($stateProvider,$urlRouterProvider)
{"##begin##"})

