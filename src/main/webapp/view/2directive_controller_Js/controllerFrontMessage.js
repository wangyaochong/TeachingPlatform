app.controller("controllerFrontMessage", function ($scope, NgTableParams, $http,$filter,$timeout){

    $scope.frontMessageTableParams = new NgTableParams({count: 5}, {
        counts: [],//代表用户不可以切换每页显示的数量
        paginationMaxBlocks: 10,//最多显示的按钮
        paginationMinBlocks: 5,//最少显示的按钮
        getData: function (params) {
            var thisOrderBy;
            if(params.orderBy().length==0){
                thisOrderBy=null;
            }else{
                 thisOrderBy=(params.orderBy()[0]).substr(1);
            }
            return $http({
                url: webRootUrl+"getItemEntityPage",
                method: "get",
                params: {
                    pageCurrentIndex: params.page(),
                    pageRowSize: params.count(),
                    orderBy:"createDate"
                    // orderBy:params.sorting(),
                }
            }).then(function (data) {
                params.total(6)
                // params.
                var orderBy = params.orderBy();
                var sorting = params.sorting();
                var isSortBy = params.isSortBy();
                $timeout(function () {
                    autosize(document.querySelectorAll('textarea'));
                },0)

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
                return data.data.pageList;
            })

        }
    });
})