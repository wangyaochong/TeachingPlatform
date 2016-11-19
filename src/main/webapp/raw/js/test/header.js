app.controller('headerController',function ($scope) {
    $scope.header={
        webTitle:{name:'百度',href:'http://www.baidu.com'},
        headerNav:[{name:'腾讯',href:'http://www.qq.com'},{name:'w3c学校',href:'http://www.w3school.com.cn/'}],
        headerDropdown:{name:'个人中心',menu:[{name:'百度',href:'http://www.baidu.com'},{name:'腾讯',href:'http://www.qq.com'}]}
    };
});