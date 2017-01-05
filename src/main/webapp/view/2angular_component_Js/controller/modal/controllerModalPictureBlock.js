/**
 * Created by 【王耀冲】 on 【2017/1/4】 at 【21:07】.
 */
app.controller("controllerModalPictureBlock",function ($scope,$uibModalInstance,$timeout,modalParam,CRUDService ,$rootScope) {

    console.log(modalParam.itemEntity);

    $scope.itemEntity=modalParam.itemEntity;
    
    $timeout(function () {
        $(".fileUploadInput").fileinput({
            language: "zh",//使用中文
            showCaption: true,//显示标题
            showDelete: true,//显示删除按钮
            showUpload: true,//显示上传按钮（总按钮）
            dropZoneEnabled: false,//禁用拖放功能
            //不使用异步上传，这样就能以一个response返回多个结果，
            //点击总的上传按钮时，触发的是filebatchuploadsuccess事件
            //当uploadAsync=true时，不管点击预览上传还是总的上传按钮，都是异步的
            //也就是说一个文件对应一个response
            uploadAsync: false,
            browseLabel: "选择",//标签
            uploadUrl: webRootUrl + "File/uploadListFile",//上传URL
            //为了方便，仅使用filebatchuploadsuccess事件
        }).on('filebatchuploadsuccess', function (event, data, previewId, index) {
            // console.log(event);
            console.log('filebatchuploadsuccess', data.response);
            var oneResource = {
                id: data.response.data[0]
            }
            $scope.itemEntity.resources=[];
            $scope.itemEntity.resources.push(oneResource);
            $scope.updateItemEntity($scope.itemEntity);

        })
    }, 100);

    $scope.closeModal=function () {
        $uibModalInstance.dismiss("cancel")
    }


    $scope.updateItemEntity = function (data) {
        CRUDService.updateMethod("ItemEntity/updateItemEntity", data).then(function (response) {
            console.log(response)
            if (angular.isUndefinedOrNull(data.id) || data.id == "") {
                data.id = response.data;
            }
            $rootScope.$broadcast("resourceUpdated",{});

        })
        $uibModalInstance.dismiss("cancel")
    }


})