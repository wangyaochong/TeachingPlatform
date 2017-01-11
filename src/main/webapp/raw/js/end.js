$urlRouterProvider.when('', '/index');//设置默认的路由
{"##end##"}


//由于这里的ready函数时监听事件，所以可以在代码中间声明。
$(document).ready(function () {
    angular.bootstrap(document, ['app']);//手动启动angular应用
    // autosize($('textarea'));
    // autosize(document.querySelectorAll('textarea'));
    
});