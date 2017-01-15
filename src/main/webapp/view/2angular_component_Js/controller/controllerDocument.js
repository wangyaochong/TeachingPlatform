/**
 * Created by 【王耀冲】 on 【2017/1/7】 at 【18:10】.
 */
app.controller("controllerDocument",function ($scope,CRUDService,$timeout) {
    $scope.documentList=[]
    $scope.classGroupList=[]
    $scope.currentCollapse=-1;//初始没有打开的
    $scope.getAllAssignment=function () {
        CRUDService.getMethod("ItemEntity/getCurrentUserDocument")
            .then(function (response) {
                $scope.documentList=response.data;
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
        var test=$(".documentCollapse");
        $(".documentCollapse").each(function () {
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