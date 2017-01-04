/**
 * Created by 【王耀冲】 on 【2017/1/4】 at 【21:07】.
 */
app.controller("controllerModalPictureBlock",function ($scope,$uibModalInstance,$timeout ) {











    $timeout(function () {
    },100)




    $scope.closeModal=function () {
        $uibModalInstance.dismiss("cancel")
    }
})