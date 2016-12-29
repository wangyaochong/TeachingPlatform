/**
 * Created by wangy on 2016/12/24.
 */
app.controller("controllerUserInformation", function ($scope, UserService, $stateParams, CRUDService, $timeout, $filter) {
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
        privilegeEntityList: "权限"
    }

    //如果是自己查看个人信息
    if (angular.isUndefinedOrNull($stateParams.id)) {
        UserService.getCurrentUser().then(function (result) {
            $scope.userInformation = result;
        });
    } else {
        //否则则是管理员或者老师
        //如果是管理员新建用户
        if ($stateParams.id == "new") {//管理员新建用户
            $scope.editType = "new";
            $scope.userInformation = {
                number: "",
                name: "",
                gender: "男",
                birthDate: new Date(),
                password: "",
                phoneNumber: "",
                email: "",
                isEditing: true,
                privilegeEntityList: ""
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
                $('.selectpicker').selectpicker({
                    // style: 'btn-primary',
                    size: 10
                });
                $("input").attr("autocomplete", "new-password")
            }, 100)
        } else {
            //管理员修改用户密码，或者修改权限
            UserService.getUser({id: $stateParams.id}).then(function (result) {
                $scope.userInformation = result;
                $scope.userInformation.isEditing = true;
                var chinesePrivilege = [];
                angular.forEach($scope.userInformation.privilegeEntityList, function (privilege) {
                    if (angular.isUndefinedOrNull(privilege.groupEntity)) {//如果groupEntity是空的，则说明是根组
                        chinesePrivilege.push( privilege.type);
                    }else{
                        chinesePrivilege.push(privilege.groupEntity.name+PrivilegeMap[ privilege.type]);
                    }
                })
                $timeout(function () {
                    $('.selectpicker').selectpicker({
                        // style: 'btn-primary',
                        size: 10
                    });
                    $('.selectpicker').selectpicker('val',chinesePrivilege);
                    $("input").attr("autocomplete", "new-password")
                }, 100)
            },function (error) {
                console.log(error);
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
        var tmpUser = {};//使用临时变量保存
        angular.copy($scope.userInformation, tmpUser);
        if (angular.isNumber(tmpUser.birthDate)) {
            tmpUser.birthDate = new Date(tmpUser.birthDate);
            tmpUser.birthDate = $filter('date')(tmpUser.birthDate, 'yyyy年MM月dd日');
        }
        var year = tmpUser.birthDate.split("年");
        var month = year[1].split("月");
        var day = month[1].split("日");
        tmpUser.birthDate = new Date(parseInt(year[0]), parseInt(month[0]) - 1, parseInt(day[0])).getTime();

        var privilegeIds=[];//后续用来保存用户的
        if(angular.isUndefinedOrNull( $stateParams.privilegeGroupEntityId)){//如果没有或者为null，则说明Privilege也没有group
            angular.forEach(userInformation.privilegeEntityList,function (privilege) {
                var tmpPriv={
                    type:privilege
                }
                CRUDService.updateMethod("Privilege/update",tmpPriv).then(function (response) {
                    privilegeIds.push(response.data);
                });
            })
        }


        var promiseUser = CRUDService.updateMethod("User/updateUser", tmpUser);

        if (angular.isUndefinedOrNull($scope.userInformation.id) || $scope.userInformation.id == "") {
            promiseUser.then(function (data) {
                $scope.userInformation.id = data.data;
            })
        }
    }
    //用来初始化
})
function getChinesePrivilege(privilegeName) {
    var privilegeMap={};
    privilegeMap[PrivilegeType.USER_MANAGEMENT]="用户管理";
    privilegeMap[PrivilegeType.USER_MANAGEMENT]="用户管理";
    privilegeMap[PrivilegeType.USER_MANAGEMENT]="用户管理";
    privilegeMap[PrivilegeType.USER_MANAGEMENT]="用户管理";
    privilegeMap[PrivilegeType.USER_MANAGEMENT]="用户管理";
    privilegeMap[PrivilegeType.USER_MANAGEMENT]="用户管理";
    privilegeMap[PrivilegeType.USER_MANAGEMENT]="用户管理";

}