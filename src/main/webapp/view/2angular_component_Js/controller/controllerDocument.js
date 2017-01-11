/**
 * Created by 【王耀冲】 on 【2017/1/7】 at 【18:10】.
 */
app.controller("controllerDocument",function ($scope,CRUDService) {

    $scope.assignmentList=[]
    $scope.classGroupList=[]

    $scope.getAllAssignment=function () {
        CRUDService.getMethod("ItemEntity/getCurrentUserAssignment")
            .then(function (response) {
                $scope.assignmentList=response.data;
                angular.forEach(response.data,function (row) {
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
})