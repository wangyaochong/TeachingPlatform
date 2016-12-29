/**
 * Created by wangy on 2016/12/25.
 */
$stateProvider.state('userCenter.userInformation',{
    url: '/userInformation?id&privilegeGroupEntityId',
    //id是用户id，也就是要查看的用户的id
    //privilegeGroupEntityId，是要设置的权限所在的group

    templateUrl:templateUrls.controllerUserInformationUrl
});