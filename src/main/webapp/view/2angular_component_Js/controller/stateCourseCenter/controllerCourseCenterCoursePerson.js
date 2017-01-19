/**
 * Created by wangy on 2017/1/1.
 */
app.controller("controllerCourseCenterCoursePerson",function (CRUDService,$scope,$stateParams,$timeout,$uibModal) {

   $scope.students=[]
   console.log("controllerCourseCenterCoursePerson.html")
   CRUDService.getMethod("Group/getStudentsFromGroup",{groupId:$stateParams.groupId}).then(function (response) {
      console.log(response)
      $scope.students=response.data;
   });
    $scope.removePersonFromGroup=function (person,index) {
        CRUDService.getMethod("Group/removePersonFromGroup",{personId:person.id,groupId:$stateParams.groupId});
        $scope.students.splice(index,1);
    }
})