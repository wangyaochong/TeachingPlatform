app.controller('header2Controller',function ($scope) {
    $scope.header={
        webTitle:{name:'headerController',href:'http://www.baidu.com'},
        headerNav:[{name:'headerController',href:'http://www.qq.com'},{name:'w3c学校',href:'http://www.w3school.com.cn/'}],
        headerDropdown:{name:'headerController',menu:[{name:'百度',href:'http://www.baidu.com'},{name:'腾讯',href:'http://www.qq.com'}]}
    };
});