/**
 * Created by 【王耀冲】 on 【2016/12/23】 at 【12:30】.
 */
//适用于angularJS的文件上传
$scope.uploadFile = function () {
    var fd = new FormData();
    var file = document.querySelector('input[type=file]').files;
    fd.append('files', file);
    $http({
        method: 'POST',
        url: "/TeachingPlatform/File/uploadListFile",
        // data: fd,
        headers: {'Content-Type': undefined},
        transformRequest: function() {
            var formData = new FormData();
            var files=$('#testFile')[0].files;
            for(var i=0;i<files.length;i++)
                formData.append('files', files[i]);
            return formData;
        }
    }).then(function (response) {
        //上传成功的操作
        alert("uplaod success");
    },function (data) {
        console.log(data)
    });
}