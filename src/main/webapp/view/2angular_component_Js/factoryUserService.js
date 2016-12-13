/**
 * Created by 【王耀冲】 on 【2016/12/13】 at 【16:30】.
 */
app.factory('UserService',function ($http) {
    return {
        getCurrentUser:function () {
             return $http({
                url:webRootUrl+"getCurrentUser"
            }).then(function (data) {
                 return data.data;
            })
        }
    }
})