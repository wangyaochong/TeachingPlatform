/**
 * Created by 【王耀冲】 on 【2016/12/13】 at 【16:30】.
 */
app.factory('UserFactory', function ($http) {
    // var currentUser;
    // $http({
    //     url: webRootUrl + "getCurrentUser"
    // }).then(function (data) {
    //     currentUser = data.data;
    // })
    return {
        getCurrentUser:function () {
            $http({
                url: webRootUrl + "getCurrentUser"
            }).then(function (data) {
                return data.data;
            })
        }
        // return $http.get(webRootUrl+"getCurrentUser").then(function (data) {
        //     return data.data;
        // })
    }
})