/**
 * Created by wangy on 2017/1/1.
 */
$stateProvider.state('courseCenter', {
    url: '/courseCenter',
    templateUrl: templateHtmlUrl + 'stateCourseCenter.html',
    controller:function (UserService,$rootScope,$state) {
        UserService.getCurrentUserPrivileages().then(function (data) {
            if ($state.current.name == "courseCenter") {
                $rootScope.HasGroupPriv= false;
                angular.forEach(data, function (privilege) {
                    if (privilege.type == PrivilegeType.GROUP_EDIT) {
                        $rootScope.HasGroupPriv=true;
                    }
                })
                //仅有超级用户可以查看所有用户列表，用来修改用户权限或者密码或者新建用户
                //教师点击班级查看对应用户列表
                if ($rootScope.HasGroupPriv) {//如果具有班级权限，那么就进入教师的课程中心
                    $state.go("courseCenter.teacher");
                }
                else {
                    $state.go("courseCenter.student",{editType:"editPass"})//对于自己就只能编辑密码，如果是教师要编辑同学的权限，那也是通过不同的入口
                }
            }
        })
    }
})

$stateProvider.state('courseCenter.student',{
    url: '/student',
    templateUrl:templateUrls.controllerCourseCenterStudentUrl
});


$stateProvider.state('courseCenter.teacher',{
    url: '/teacher',
    templateUrl:templateUrls.controllerCourseCenterTeacherUrl
});


$stateProvider.state('courseCenter.teacherResource',{
    url: '/teacherResource?groupId&isStudent',
    templateUrl:templateUrls.controllerCourseCenterTeacherResourceUrl
});


$stateProvider.state('courseCenter.coursePerson',{
    url: '/coursePerson?groupId',
    templateUrl:templateUrls.controllerCourseCenterCoursePersonUrl
});














