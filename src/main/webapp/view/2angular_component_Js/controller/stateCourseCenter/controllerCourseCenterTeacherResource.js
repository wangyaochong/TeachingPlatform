/**
 * Created by wangy on 2017/1/1.
 */
app.controller("controllerCourseCenterTeacherResource",function (CRUDService,$scope,$stateParams,$timeout) {
    $scope.indexMessageList=[];
    $scope.rollPictureList=[];
    $scope.assignmentList=[];
    $scope.documentList=[];
    $scope.videoList=[];
    $scope.currentClassGroup={};
    console.log("controllerCourseCenterTeacherResource")
    CRUDService.getMethod("ItemEntity/getItemEntityListByClassGroupId",{id:$stateParams.groupId}).then(function (response) {
        $scope.indexMessageList=response.data;
        console.log(response);
    })
    CRUDService.getMethod("Group/getClassGroupById",{id:$stateParams.groupId}).then(function (response) {
        $scope.currentClassGroup=response.data;
    })

    $scope.updateItemEntity=function (data) {
        CRUDService.updateMethod("ItemEntity/updateItemEntity",data).then(function (response) {
            console.log(response)
        })
    }
    $scope.addIndexMessage=function (data) {
        var tmpMessage={
            id:"",
            title:"",
            createDate:new Date().getTime(),
            type:ItemType.ANNOUNCEMENT,
            isOpen:true,
            classGroup:$scope.currentClassGroup,
            isEditing:true,
            dataCopy:{}
        }
        angular.copy(tmpMessage,tmpMessage.dataCopy);
        $scope.indexMessageList.unshift(tmpMessage);
    }







    $scope.initPosition=function () {//因为是使用collapse，所以宽度什么的需要等展开才能正确获取到
        $timeout(function () {
            $("#newRollPictureDiv").css("height",$("#newRollPictureDiv").width())
        },100)
    }
})