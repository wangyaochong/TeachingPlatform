app.controller("controllerIndexMessage", function ($scope, NgTableParams, $filter, $timeout, UserService,ItemEntityService) {
    $scope.pageList=[];
    $scope.editable=false;
    $scope.frontMessageTableParams = new NgTableParams({count: 5}, {
        counts: [],//代表用户不可以切换每页显示的数量
        paginationMaxBlocks: 10,//最多显示的按钮
        paginationMinBlocks: 5,//最少显示的按钮
        getData: function (params) {
            var thisOrderBy;
            if (params.orderBy().length == 0) {
                thisOrderBy = null;
            } else {
                thisOrderBy = (params.orderBy()[0]).substr(1);
            }
            return  ItemEntityService.getItemEntityPage(
                {type:ItemType.ANNOUNCEMENT},
                params.page(),
                params.count(),
                "createDate"
            )
            // $http({
            //     url: webRootUrl + "ItemEntity/getItemEntityPage",
            //     method: "get",
            //     params: {
            //         type:ItemType.ANNOUNCEMENT,//获取所有关于首页消息的page
            //         pageCurrentIndex: params.page(),
            //         pageRowSize: params.count(),
            //         orderBy: "createDate"
            //         // orderBy:params.sorting(),
            //     }
            // })
                .then(function (data) {
                params.total(data.totalRowCount)
                // params.
                var orderBy = params.orderBy();
                var sorting = params.sorting();
                var isSortBy = params.isSortBy();
                $timeout(function () {
                    autosize(document.querySelectorAll('textarea'));
                }, 0)
                // var promise=UserService.getCurrentUser();
                // promise.then(function (data) {
                //     var test=data;
                // })
                // angular.forEach(data.data.pageList,function (data,index) {
                //     //要在另一个线程中给控件初始化
                //     // $timeout(function () {
                //     //     $("input").datepicker({
                //     //         maxViewMode: 1,//设置最多可以从月开始设置
                //     //         language:"zh-CN",//设置语言为中文
                //     //         autoclose:true,//设置选择日期后自动关闭
                //     //         todayHighlight: true,//设置高亮今日
                //     //         todayBtn: true,//显示今日按钮
                //     //     })
                //     //     $("#datePicker"+index).datepicker("update",new Date(data.createDate))
                //     // },0)
                //     // data.createDate=new Date(data.createDate)
                //     data.createDate=$filter('date')(data.createDate,'yyyy-MM-dd');//将数字日期转换成为字符串日期
                // })
                    angular.forEach(data.pageList, function (data) {
                        data.isEditing = false;//是否正在编辑为假
                        data.dataCopy = {};
                        angular.copy(data, data.dataCopy);//保存编辑前的内容
                    })
                $scope.pageList = data.pageList;
                return data.pageList;
            })
        }
    });
})