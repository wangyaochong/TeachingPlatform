/**
 * Created by wangy on 2016/12/24.
 */
app.controller("controllerHeaderMenu", function ($scope, $http,CRUDService,$uibModal) {
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
    $scope.openLanguageSettingModal=function () {
        var modalInstace=$uibModal.open({
            templateUrl:templateHtmlUrl+"templateLanguageSettingModal.html",
            controller:function ($scope) {
                 $scope.selected={};
                $scope.languageOption=[];
                $scope.languageOption.push({type:"zh_CN",name:"中文"});
                $scope.languageOption.push({type:"en_US",name:"English"});
                $scope.closeModal=function () {
                    modalInstace.close($scope.selected.value.type);
                }
                $scope.closeModalNoChange=function () {
                    modalInstace.dismiss();
                }
            }
        })
        modalInstace.result.then(function (data) {
            console.log(data);
        },function (data) {
            console.log(data);
        })

    }
})