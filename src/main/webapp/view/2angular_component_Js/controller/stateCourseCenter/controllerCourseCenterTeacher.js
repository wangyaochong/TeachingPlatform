/**
 * Created by wangy on 2017/1/1.
 */
app.controller("controllerCourseCenterTeacher", function ($scope, CRUDService,CRUDHtmlService,$timeout) {
    console.log("controllerCourseCenterTeacher")
    $scope.teacherCourseList = [];
    $scope.createNewCourse = function () {
        CRUDService.updateMethod("Group/updateGroup",  $scope.newCourse).then(function (response) {
            console.log(response)
            $scope.newCourse.id=response.data;
            $scope.teacherCourseList.unshift($scope.newCourse);
            $("#newCourseModal").modal("hide");
        }, function (error) {
            console.log(error)
        });
    }


    //获取当前所有教师的所有课程列表
    CRUDService.getMethod("Group/getCurrentTeacherClassGroup").then(function (response) {
        console.log(response)
        $scope.teacherCourseList=response.data;
    }, function (e) {
        console.log(e)
    });





    $scope.InitNewCourseModal=function () {
        $scope.newCourse={
            name: "",
            description: "",
            type: "CLASS"
        }
    }

    $timeout(function () {
        $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
    },100)
})