/**
 * Created by wangy on 2017/1/1.
 */
app.controller("controllerCourseCenterStudent",function (CRUDService,$scope,$q,$state) {
    console.log("controllerCourseCenterStudent")
    $scope.allCourse=[];
    $scope.currentUser={};
    var qCurrent= CRUDService.getMethod("User/getCurrent",{}).then(function (response) {
        $scope.currentUser=response;
        console.log(response)
        return true;
    })
    var qAll= CRUDService.getMethod("Group/getAllClassGroup",{}).then(function (response) {
        $scope.allCourse=response.data;
        console.log(response)
        return true;
    })
    $q.all([qCurrent,qAll]).then(function (data) {
        removeUserCoursrFromAllCourse();
    })
    $scope.applyCurrentUserToGroup=function (groupId) {
        CRUDService.getMethod("Message/sendGroupApplyMessage",{groupId:groupId});
    }
    $scope.goToCourseResource=function (group) {
        $state.go("courseCenter.teacherResource",{groupId:group.id,isStudent:true});
    }


    function removeUserCoursrFromAllCourse() {
        for(var i=0;i<$scope.currentUser.groupEntityList.length;i++){
            for(var j=0;j<$scope.allCourse.length;j++){
                if($scope.currentUser.groupEntityList[i].id==$scope.allCourse[j].id){
                    $scope.allCourse.splice(j,1);
                    j=-1;//如果删除了一个则再次重头开始
                }
            }
        }
    }
})