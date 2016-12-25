/**
 * Created by 【王耀冲】 on 【2016/12/22】 at 【15:22】.
 */
app.service('FileUploadModalService', function ($rootScope) {
    this.initFileUploadModal = function (outUploadUrl, isMultiple, uploadSuccessCallBack) {
        //上传成功会会回调uploadSuccessCallBack函数，该函数只有一个参数，就是response，回调时会将该参数填入
        //尝试使用angular的ng-include加载模版，但是失败了，可能是由于动态加载，导致指令没有在angular框架中注册
        // $("body").prepend('<div ng-include="templateFileUploadModalUrl"></div>')
        // $rootScope.templateFileUploadModalUrl= templateHtmlUrl + "templateFileUploadModal.html";
        var templateUrl = templateHtmlUrl + "templateFileUploadModal.html";
        $("body").prepend('<div id="fileuploadModalDiv"></div>');
        $("#fileuploadModalDiv").load(templateUrl);
        setTimeout(function () {
            if (isMultiple == true) {
                $("#fileUploadInput").attr("multiple", "multiple");
            }
            $("#fileUploadInput").fileinput({
                language: "zh",
                showCaption: true,
                showDelete: true,
                showUpload: true,
                dropZoneEnabled: false,//禁用拖放功能
                uploadAsync: false,//不使用异步上传，这样就能以一个response返回多个结果，
                //点击总的上传按钮时，触发的是filebatchuploadsuccess事件
                //当uploadAsync=true时，不管点击预览上传还是总的上传按钮，都是异步的
                browseLabel: "选择",
                uploadUrl: outUploadUrl,
                //为了方便，仅使用filebatchuploadsuccess事件
            }).on('filebatchuploadsuccess', function (event, data, previewId, index) {
                console.log(event);
                console.log(data);
                if (!angular.isUndefinedOrNull(uploadSuccessCallBack)) {
                    uploadSuccessCallBack(data.response);//如果回调函数不是null或者undefined
                }
            })

            $("#fileUploadModal").modal({
                show: true
            });
        }, 500)
    }
    this.dismissFileUploadModal = function () {
        $("#fileuploadModalDiv").remove();//使用完毕后可以将该模态框解除
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