/**
 * Created by 【王耀冲】 on 【2016/12/13】 at 【15:04】.
 */
$stateProvider.state('userCenter', {
    url: '/userCenter',
    templateUrl: templateHtmlUrl + 'stateUserCenter.html',
    controller: function ($rootScope, $state,UserService) {
        UserService.getCurrentUserPrivileages().then(function (data) {
            if($state.current.name=="userCenter"){
                $rootScope.HasAssignmentPriv = data[0].assignment;
                $rootScope.HasDocumentPriv = data[0].document;
                $rootScope.HasFrontMessagePriv = data[0].frontMessage;
                $rootScope.HasPersonalInfomationtPriv = data[0].personalInfomation;
                $rootScope.HasVideoPriv = data[0].video;
                $rootScope.HasSuperPriv = data[0].isSuper;
                if ($rootScope.HasPersonalInfomationtPriv) {
                    $state.go("userCenter.userList");
                }
                else {
                    $state.go("userCenter.userInformation")
                }
            }
        })
    }
});