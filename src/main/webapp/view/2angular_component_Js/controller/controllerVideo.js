/**
 * Created by wangy on 2017/1/15.
 */
app.controller("controllerVideo",function ($scope,CRUDService,$timeout) {
    $scope.videoList=[]
    $scope.classGroupList=[]
    $scope.currentCollapse=-1;//初始没有打开的
    $scope.getAllAssignment=function () {
        CRUDService.getMethod("ItemEntity/getCurrentUserVideo")
            .then(function (response) {
                $scope.videoList=response.data;
                angular.forEach(response.data,function (row) {
                    //提取出所有的班级分组
                    var index=0;
                    for(index=0;index<$scope.classGroupList.length;index++){
                        if($scope.classGroupList[index].id==row.classGroup.id){
                            break;
                        }
                    }
                    if(index==$scope.classGroupList.length){
                        $scope.classGroupList.push(row.classGroup);
                    }
                })
            })
    }
    $scope.getAllAssignment();

    $scope.isOpenCollapse=function (index) {
        var test = $("#collapse"+index).attr('class');
        return $("#collapse"+index).attr('class') == "panel-collapse collapsing" || $("#collapse"+index).attr('class') == "panel-collapse collapse in";
    }
    $timeout(function () {
        var test=$(".videoCollapse");
        $(".videoCollapse").each(function () {
            $(this).on('show.bs.collapse',function () {
                var index=$(this).attr("id")
                $scope.$apply(function () {
                    $scope.currentCollapse=parseInt(index);
                })
                console.log(index);
            })
        })
    },100)

})