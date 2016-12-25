/**
 * Created by wangy on 2016/12/24.
 */
app.controller("controllerUserInformation", function ($scope, UserService,$stateParams,CRUDService) {
    $scope.passwordCheck = "";
    $scope.passwordCheckModalCaption = "请输入用户密码"
    $scope.userInformation = {};
    $scope.editType="password";
    $scope.nameTranslation = {
        number: "帐号",
        name: "姓名",
        gender: "性别",
        birthDate: "出生日",
        password: "密码",
        phoneNumber: "手机",
        email: "邮箱",
    }

    if(angular.isUndefinedOrNull($stateParams.id)){
        UserService.getCurrentUser().then(function (result) {
            $scope.userInformation = result;
        });
    }else{
        if($stateParams.id=="new"){
            $scope.editType="new";
            $scope.userInformation = {
                number: "",
                name: "",
                gender: "",
                birthDate: "",
                password: "",
                phoneNumber: "",
                email: "",
                isEditing:true
            }
        }else{
            UserService.getUser({id:$stateParams.id}).then(function (result) {
                $scope.userInformation = result;
                $scope.userInformation.isEditing=true;
            })
        }
    }


    $scope.passwordCheckFunc = function () {
        if ($scope.passwordCheck == $scope.userInformation.password) {
            $("#passwordCheckModel").modal("hide");
            $scope.userInformation.isEditing = true;
            $scope.userInformation.password="";
        } else {
            $scope.passwordCheckModalCaption = "密码错误，请重新输入"
        }
        $scope.passwordCheck = "";
    }
    $scope.showPassCheckModal = function () {
        $("#passwordCheckModel").modal("show");
    }
    $scope.updateUser = function (data) {
        CRUDService.updateMethod("User/updateUser", data);
    }

})