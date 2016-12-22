/**
 * Created by 【王耀冲】 on 【2016/12/22】 at 【15:22】.
 */
app.service('FileUploadModalService', function ($http) {
    this.uploadResult = "";
    this.initFileUploadModal = function (outUploadUrl, isMultiple) {
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
                showUpload:true,
                dropZoneEnabled: false,//禁用拖放功能
                // uploadAsync:false,//不使用异步上传，这样就能以一个response返回多个结果，
                //点击总的上传按钮时，触发的是filebatchuploadsuccess事件
                //当uploadAsync=true时，不管点击预览上传还是总的上传按钮，都是异步的
                browseLabel: "选择",
                uploadUrl: outUploadUrl, // server upload action
            }).on('filebatchuploadsuccess', function (event, data, previewId, index) {
                console.log(event);
                console.log(data);
                console.log(previewId);
                console.log(index);
            }).on('fileuploaded',function (event, data, previewId, index) {
                console.log(event);
                console.log(data);
                console.log(previewId);
                console.log(index);
            });
            $("#fileUploadModal").modal({
                show: true
            });
        }, 500)
    }
    this.dismissFileUploadModal = function () {
        $("#fileuploadModalDiv").remove();
    }
    this.uploadFile = function () {

    }
})