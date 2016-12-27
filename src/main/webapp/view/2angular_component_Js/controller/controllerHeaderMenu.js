/**
 * Created by wangy on 2016/12/24.
 */
app.controller("controllerHeaderMenu", function ($scope, $http) {
    $http.get(webRootUrl + "Header/getHeaderUrl").then(function (data) {
        $scope.header = data.data
    },function (error) {
        console.log(error)
    })
})