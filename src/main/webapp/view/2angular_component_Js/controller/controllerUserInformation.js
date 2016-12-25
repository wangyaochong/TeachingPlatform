/**
 * Created by wangy on 2016/12/24.
 */
app.controller("controllerUserInformation", function ($scope, UserService,$filter) {
    $scope.nameTranslation={
        number:"帐号",
        name:"姓名",
        gender:"性别",
        birthDate:"出生日",
        password:"密码",
        phoneNumber:"手机",
        email:"邮箱",
    }
    $scope.userInformation={};
    UserService.getCurrentUser().then(function (result) {
        $scope.userInformation=result;
        angular.forEach(result, function (data, index) {
            console.log(index)
            console.log(data)
        })
    });

})