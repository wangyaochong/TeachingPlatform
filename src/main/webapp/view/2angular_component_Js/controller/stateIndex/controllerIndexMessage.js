app.controller("controllerIndexMessage",function ($scope,NgTableParams,$filter,$timeout,UserService,ItemEntityService,CRUDService) {
    $scope.pageList = [];
    $scope.editable = false;

    CRUDService.getMethod("ItemEntity/getCurrentUserIndexMessage",{}).then(function (response) {
        angular.forEach(response.data,function (data) {
            data.isEditing = false;//是否正在编辑为假
            data.dataCopy = {};
            angular.copy(data,data.dataCopy);//保存编辑前的内容
        })
        $scope.pageList = response.data;
        $scope.frontMessageTableParams = new NgTableParams({
            count: 5
        },{
            counts: [],
            paginationMaxBlocks: 10,//最多显示的按钮
            paginationMinBlocks: 5,//最少显示的按钮
            dataset: response.data
        })
    })


    // $scope.frontMessageTableParams = new NgTableParams({count: 5},{
    //     counts: [],//代表用户不可以切换每页显示的数量
    //     paginationMaxBlocks: 10,//最多显示的按钮
    //     paginationMinBlocks: 5,//最少显示的按钮
    //     getData: function (params) {
    //         var thisOrderBy;
    //         if (params.orderBy().length == 0) {
    //             thisOrderBy = null;
    //         } else {
    //             thisOrderBy = (params.orderBy()[0]).substr(1);
    //         }
    //         return ItemEntityService.getItemEntityPage(
    //             {type: ItemType.ANNOUNCEMENT},
    //             params.page(),
    //             params.count(),
    //             "createDate"
    //         )
    //             .then(function (data) {
    //                 params.total(data.totalRowCount)
    //                 // params.
    //                 var orderBy = params.orderBy();
    //                 var sorting = params.sorting();
    //                 var isSortBy = params.isSortBy();
    //                 $timeout(function () {
    //                     autosize(document.querySelectorAll('textarea'));
    //                 },0)
    //                 angular.forEach(data.pageList,function (data) {
    //                     data.isEditing = false;//是否正在编辑为假
    //                     data.dataCopy = {};
    //                     angular.copy(data,data.dataCopy);//保存编辑前的内容
    //                 })
    //                 $scope.pageList = data.pageList;
    //                 return data.pageList;
    //             })
    //     }
    // });
})