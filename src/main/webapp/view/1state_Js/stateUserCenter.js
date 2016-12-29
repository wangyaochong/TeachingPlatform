/**
 * Created by 【王耀冲】 on 【2016/12/13】 at 【15:04】.
 */
$stateProvider.state('userCenter', {
    url: '/userCenter',
    templateUrl: templateHtmlUrl + 'stateUserCenter.html',
    controller: function ($rootScope, $state, UserService) {
        UserService.getCurrentUserPrivileages().then(function (data) {
            if ($state.current.name == "userCenter") {
                $rootScope.HasSuperPriv = false;
                $rootScope.HasPersonalInformationtPriv = false;
                angular.forEach(data, function (privilege) {
                    if (privilege.type == PrivilegeType.SUPER) {
                        $rootScope.HasSuperPriv = true;
                    }
                    // if (privilege.type == PrivilegeType.USER_MANAGEMENT) {
                    //     $rootScope.HasPersonalInformationtPriv = true;
                    // }
                })
                //仅有超级用户可以查看所有用户列表，用来修改用户权限或者密码或者新建用户
                //教师点击班级查看对应用户列表
                if ($rootScope.HasSuperPriv ) {
                    $state.go("userCenter.userList");
                }
                else {
                    $state.go("userCenter.userInformation")
                }
            }
        })
    }
});
// $rootScope.HasAssignmentPriv = data[0].assignment;
// $rootScope.HasDocumentPriv = data[0].document;
// $rootScope.HasFrontMessagePriv = data[0].frontMessage;
// $rootScope.HasPersonalInformationtPriv = data[0].personalInformation;
// $rootScope.HasVideoPriv = data[0].video;
// $rootScope.HasSuperPriv = data[0].super;
