/**
 * Created by wangy on 2016/12/24.
 */
app.controller("controllerHeaderMenu", function ($scope, $http,CRUDService) {
    $http.get(webRootUrl + "Header/getHeaderUrl").then(function (data) {
        $scope.header = data.data
    },function (error) {
        console.log(error)
    })
    CRUDService.getMethod("Message/getCurrentUserMessage",{}).then(function (response) {
        console.log(response)
        $scope.messages=response.data;
        var countMap=_.countBy($scope.messages,function (row) {
            return row.hasRead;
        })
        $scope.unReadMessageCount=countMap['false'];
        console.log(countMap)
    });
    $scope.clearUnreadMessage=function () {
        // $scope.unReadMessageCount=0;
    }
})