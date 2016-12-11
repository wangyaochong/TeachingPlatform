$urlRouterProvider.when('', '/hello');//设置默认的路由
{"##end##"}
$(document).ready(function () {
    angular.bootstrap(document, ['app']);
    // autosize($('textarea'));
    autosize(document.querySelectorAll('textarea'));
});