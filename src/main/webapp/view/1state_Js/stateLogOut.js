/**
 * Created by 【王耀冲】 on 【2016/12/13】 at 【15:02】.
 */
$stateProvider.state('logOut',{
    url: '/logOut',
    controller:function () {
        window.location.href="/TeachingPlatform/logOut";//登出
    }
});