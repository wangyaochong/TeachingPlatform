/**
 * Created by wangy on 2016/12/18.
 */
app.controller("controllerRollPicture", function ($scope, $http, ItemEntityService,CRUDService) {

    CRUDService.getMethod("ItemEntity/getCurrentUserRollPicture",{}).then(function (response) {
        $scope.pictures=response.data;
        initDirectiveRollPicture($scope);//进行angularJS框架以外的初始化工作
    })

    // ItemEntityService.getItemEntityPage({type: ItemType.ROLLPICTURE}, 1, maxPageRowSize, "createDate")
    //     .then(function (data) {
    //         $scope.pictures = data.pageList
    //         initDirectiveRollPicture($scope);//进行angularJS框架以外的初始化工作
    //     }, function (data) {
    //         console.log(data)
    //     })
    // FileUploadModalService.initFileUploadModal(webRootUrl + "File/uploadListFile", true,function (data) {
    //     console.log(data)
    // });
})
function initDirectiveRollPicture($scope) {
    setTimeout(function () {
        autosize(document.querySelectorAll('textarea'));
        $('#carousel-example-generic').on('slide.bs.carousel', function () {
            autosize(document.querySelectorAll('textarea'));
        })
        $('#carousel-example-generic').on('slid.bs.carousel', function () {
            autosize(document.querySelectorAll('textarea'));
            var ta = document.querySelector('textarea');
            ta.style.display = 'none';
            autosize(ta);
            ta.style.display = '';
        })
    }, 50)
}