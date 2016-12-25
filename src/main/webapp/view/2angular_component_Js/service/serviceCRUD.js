/**
 * Created by wangy on 2016/12/24.
 */
app.service('CRUDService', function ($http) {
    this.updateMethod=function (url,params) {//create、delete、update都用updateMethod
        return  $http({
            url: webRootUrl +url,
            params: params,
            method: "post"
        }).then(function (data) {
            return data;
        },function (error) {
            console.log(error)
        })
    }
    this.getMethod=function (url, params) {
        return $http({
            url: webRootUrl +url,
            params: params,
            method: "get"
        }).then(function (data) {
            return data;
        },function (error) {
            console.log(error)
        })
    }
})