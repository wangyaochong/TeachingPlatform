
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
    'HeaderMenu',
    'RollPicture',
    'IndexMessage',
    'UserInformation',
    'UserList',
    'CourseCenterStudent',
    'CourseCenterTeacher',
    'CourseCenterTeacherResource',
    'Assignment',
    'Document'
];
var directiveNames = [
    'Edit',
    'CourseBlock',
    'PictureBlock',
    'DocumentBlock'
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