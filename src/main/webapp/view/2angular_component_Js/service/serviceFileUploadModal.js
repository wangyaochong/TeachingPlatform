/**
 * Created by 【王耀冲】 on 【2016/12/22】 at 【15:22】.
 */
app.service('FileUploadModalService', function ($rootScope,$uibModal) {

    this.initFileUploadModal = function (uploadUrl, isMultiple, uploadSuccessCallBack) {
        //上传成功会会回调uploadSuccessCallBack函数，该函数只有一个参数，就是response，回调时会将该参数填入
        //尝试使用angular的ng-include加载模版，但是失败了，可能是由于动态加载，导致指令没有在angular框架中注册
        var templateModalUrl = templateHtmlUrl + "templateFileUploadModal.html";
        var modalInstance= $uibModal.open({
            templateUrl:templateModalUrl,
            size:'lg',
            controller:"controllerFileUploadModal",
            resolve:{//resolve必须要以function的形式注入，因为function上还可以有其他依赖的模块
                uploadSuccessCallBack:function () {
                    return uploadSuccessCallBack;
                },
                uploadUrl:function () {
                    return uploadUrl
                },
                isMultiple:function () {
                    return isMultiple
                }
            }
        });
        modalInstance.result.then(function (data) {
            console.log(data)
        },function (error) {
            console.log(error)
        })
    }
})








//     .on('fileuploaded',function (event, data, previewId, index) {
//     console.log(event);
//     console.log(data);
//     console.log(previewId);
//     console.log(index);
//     if(!angular.isNullOrUndefined(uploadSuccessCallBack)){
//         uploadSuccessCallBack(data.response);//如果回调函数不是null或者undefined
//     }
// });