/**
 * Created by wangy on 2016/12/25.
 */
app.controller("controllerUserList",function ($scope,UserService,NgTableParams,$state,CRUDService) {
    $scope.userListTableParams = new NgTableParams({count: 5}, {
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
            return  UserService.getUserPage({
                pageCurrentIndex:params.page(),
                pageRowSize:params.count()
            })
                .then(function (data) {
                    params.total(data.totalRowCount)
                    var orderBy = params.orderBy();
                    var sorting = params.sorting();
                    var isSortBy = params.isSortBy();
                    angular.forEach(data.pageList, function (data) {
                        data.isEditing = false;//是否正在编辑为假
                    })
                    $scope.pageList = data.pageList;
                    return data.pageList;
                },function (error) {
                    console.log(error)
                })
        }
    });

    $scope.editUser=function (item) {
        if(angular.isUndefinedOrNull(item)){
            //传参，必须要在state定义的地方定义参数
            $state.go("userCenter.userInformation",{id:"new"})
        }else{
            $state.go("userCenter.userInformation",{id:item.id});
        }
    }
    $scope.deleteUser=function (data) {
        var promise= CRUDService.updateMethod("User/deleteUser", data);
        promise.then(function (data) {//响应回来后再重新加载ngTable
            $scope.userListTableParams.reload();
        })
    }
})