
/**
 * Created by wangy on 2016/12/25.
 */
function generateUrl(scope, type, nameList) {
    angular.forEach(nameList, function (data) {
        data = type + data;
        scope[data + "Url"] = templateHtmlUrl + type + "/" + data + ".html"
    })
}
var controllerNames = [
    //顶部菜单
    'HeaderMenu',

    //滚动图片
    'RollPicture',

    //首页消息
    'IndexMessage',

    //用户信息
    'UserInformation',

    //用户列表
    'UserList',

    //学生课程中心
    'CourseCenterStudent',

    //教师课程中心
    'CourseCenterTeacher',

    //课程资源中心
    'CourseCenterTeacherResource',

    //课程人员列表
    'CourseCenterCoursePerson',
    'Assignment',
    'Document',
    'Message',
    'Video',
    'VideoPlay',
];
var directiveNames = [
    'Edit',
    'CourseBlock',
    'PictureBlock',
    'DocumentBlock',
    'VideoBlock'
]

generateUrl(templateUrls, "controller", controllerNames);
generateUrl(templateUrls, "directive", directiveNames);

//用来构建参数
function buildHttpParams(data) {
    var param = {};
    var filterParams=[
        'dataCopy',
    ]
    angular.forEach(data, function (value, key) {
        if (angular.isUndefinedOrNull(value) ||
            value == ""||
            filterParams.indexOf(key)!=-1
        ) {
        }else{
            param[key] = value;
        }
    })
    return param;
}