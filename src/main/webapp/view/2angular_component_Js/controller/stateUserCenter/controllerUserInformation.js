/**
 * Created by wangy on 2016/12/24.
 */
app.controller("controllerUserInformation", function ($scope, UserService, $stateParams, CRUDService, $timeout) {
    $scope.passwordCheck = "";
    $scope.passwordCheckModalCaption = "请输入用户密码"
    $scope.userInformation = {};
    $scope.editType = "password";
    $scope.nameTranslation = {
        number: "帐号",
        name: "姓名",
        gender: "性别",
        birthDate: "出生日",
        password: "密码",
        phoneNumber: "手机",
        email: "邮箱",
    }

    if (angular.isUndefinedOrNull($stateParams.id)) {//自己查看个人信息
        UserService.getCurrentUser().then(function (result) {
            $scope.userInformation = result;
        });
    } else {
        if ($stateParams.id == "new") {//管理员新建用户
            $scope.editType = "new";
            $scope.userInformation = {
                number: "",
                name: "",
                gender: "女",
                birthDate: new Date(),
                password: "",
                phoneNumber: "",
                email: "",
                isEditing: true
            }
            $timeout(function () {
                $("#UserInfoDatePicker").datepicker({
                    maxViewMode: 2,//设置最多可以从月开始设置
                    language: "zh-CN",//设置语言为中文
                    autoclose: true,//设置选择日期后自动关闭
                    todayHighlight: true,//设置高亮今日
                    todayBtn: true,//显示今日按钮
                })
                $("#UserInfoDatePicker").datepicker("update", new Date())//传入当前日期
            }, 0)
        } else {//管理员修改用户密码
            UserService.getUser({id: $stateParams.id}).then(function (result) {
                $scope.userInformation = result;
                $scope.userInformation.isEditing = true;

                $timeout(function () {
                    $("input").datepicker({
                        maxViewMode: 1,//设置最多可以从月开始设置
                        language: "zh-CN",//设置语言为中文
                        autoclose: true,//设置选择日期后自动关闭
                        todayHighlight: true,//设置高亮今日
                        todayBtn: true,//显示今日按钮
                    })
                    $("#datePicker").datepicker("update", new Date(data.createDate))
                }, 0)

            })
        }
    }


    $scope.passwordCheckFunc = function () {
        if ($scope.passwordCheck == $scope.userInformation.password) {
            $("#passwordCheckModel").modal("hide");
            $scope.userInformation.isEditing = true;
            $scope.userInformation.password = "";
        } else {
            $scope.passwordCheckModalCaption = "密码错误，请重新输入"
        }
        $scope.passwordCheck = "";
    }
    $scope.showPassCheckModal = function () {
        //当是自己修改密码时需要确认密码
        if (angular.isUndefinedOrNull($stateParams.id)) {
            $("#passwordCheckModel").modal("show");
        }
    }
    $scope.updateUser = function () {
        var tmpUser={};//使用临时变量保存
        angular.copy($scope.userInformation,tmpUser);
        var year = tmpUser.birthDate.split("年");
        var month = year[1].split("月");
        var day = month[1].split("日");
        tmpUser.birthDate = new Date(parseInt(year[0]),parseInt(month[0]), parseInt(day[0]),0,0,0).getTime();
        var promise=CRUDService.updateMethod("User/updateUser", tmpUser);
        if(angular.isUndefinedOrNull($scope.userInformation.id)||$scope.userInformation.id==""){
            promise.then(function (data) {
                $scope.userInformation.id=data.data;
            })
        }
    }

})