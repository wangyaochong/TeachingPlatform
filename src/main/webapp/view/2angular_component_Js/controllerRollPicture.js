/**
 * Created by wangy on 2016/12/18.
 */
app.controller("controllerRollPicture", function ($scope, $http, ItemEntityService, FileUploadModalService) {
    ItemEntityService.getItemEntityPage({type: ItemType.ROLLPICTURE}, 1, maxPageRowSize, "createDate")
        .then(function (data) {
            $scope.pictures = data.pageList
            initDirectiveRollPicture($scope);//进行angularJS框架以外的初始化工作
        }, function (data) {
            console.log(data)
        })
    FileUploadModalService.initFileUploadModal(webRootUrl + "File/uploadListFile", true,function (data) {
        console.log(data)
    });
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
        //暂时不使用slick插件
        // $('#slickContainer').css("width", $scope.pictures.length * 100 + "px");
        // $('.slick').slick({
        //     // dots: true,
        //     infinite: false,
        //     zIndex: 0,
        //     slidesToShow: 6,
        //     arrows: true,
        //     autoplay: false,
        //     prevArrow: '<a class="left carousel-control" style="z-index: 1000;width: 50px!important;" role="button">' +
        //     ' <span' +
        //     ' class="glyphicon glyphicon-chevron-left"></span></a>',
        //     nextArrow: '<a class="right carousel-control" style="z-index: 1000;width: 50px!important;"role="button">' +
        //     ' <span' +
        //     ' class="glyphicon glyphicon-chevron-right"></span> </a>'
        // });
    }, 50)
}