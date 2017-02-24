/**
 * Created by wangy on 2016/12/24.
 */
app.controller("controllerHeaderMenu", function ($scope, $http,CRUDService,$uibModal,$rootScope) {
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
    CRUDService.getMethod("Locale/getLocale",{}).then(function (response) {
        console.log(response);
        $scope.currentLocale=response.data;
    })
    $scope.openLanguageSettingModal=function () {
        var modalInstace=$uibModal.open({
            templateUrl:templateHtmlUrl+"templateLanguageSettingModal.html",
            resolve:{
                currentLocale:function () {
                    return $scope.currentLocale;
                }
            },
            controller:function ($scope,currentLocale) {
                 $scope.selected={};
                $scope.languageOption=[];
                $scope.languageOption.push({type:"zh_CN",name:"中文"});
                $scope.languageOption.push({type:"en_US",name:"English"});
                angular.forEach($scope.languageOption,function (one) {
                    if(one.type.toLowerCase()==currentLocale.toLowerCase()){
                        $scope.selected.value=one;
                    }
                })
                $scope.closeModal=function () {
                    modalInstace.close($scope.selected.value.type);
                }
                $scope.closeModalNoChange=function () {
                    modalInstace.dismiss();
                }
            }
        })

        modalInstace.result.then(function (data) {
            $scope.currentLocale=data;
            CRUDService.getMethod("Locale/updateLocale",{localeLanguage:data}).then(function (response) {
                CRUDService.getMethod("Locale/getLocaleProperties",{}).then(function (response) {
                    $rootScope.localeProperties=response.data;
                },function (error) {
                    console.log(error);
                })
                console.log(response);
            })
            console.log(data);
        },function (data) {
            console.log(data);
        })

    }
})