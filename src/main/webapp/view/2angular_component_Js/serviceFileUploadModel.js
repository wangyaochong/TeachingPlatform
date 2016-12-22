/**
 * Created by 【王耀冲】 on 【2016/12/22】 at 【15:22】.
 */
app.service('FileUploadModelService', function ($http) {
    this.uploadUrl = ""//上传的路径
    this.isMultiple=false;//是否上传多个文件
    this.uploadResult="";
    this.initFileUploadModel = function (uploadUrl,isMultiple) {
        this.uploadUrl = uploadUrl;
        this.isMultiple=isMultiple;
        $("body").append("<div ng-include=''></div>")
    }
    this.uploadFile = function () {

    }
})