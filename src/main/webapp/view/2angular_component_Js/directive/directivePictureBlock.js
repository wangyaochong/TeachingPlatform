/**
 * Created by wangy on 2017/1/2.
 */
app.directive('directivePictureBlock', function () {
    return {
        restrict: 'E',
        templateUrl: templateUrls.directiveCourseBlockUrl,
        replace: true,
        scope: {
            $list:"=",
            $item: "=",
            $index:"=",
            $width: "=",
            $padding: "="
        },
        controller: function ($scope, $timeout,CRUDService,CRUDHtmlService,$state) {
            $timeout(function () {
                $(".courseblockpaddingwrapper").each(function () {
                    $(this).css("padding", $scope.$padding);
                })
                $(".courseblockwrapper").each(function () {
                    $(this).width($scope.$width);
                    $(this).height($(this).width());//让高度等于宽度
                })
                $(".courseblockcontent").each(function () {
                    var r=(Math.random()*1000%100+150);
                    r=parseInt(r);
                    var g=(Math.random()*1000%100+150);
                    g=parseInt(g);
                    var b=(Math.random()*1000%100+150);
                    b=parseInt(b);
                    $(this).css("background-color", "rgb("+r+","+g+","+b+")");
                })
            }, 100)


            $scope.courseResourceClick = function () {
                $state.go("courseCenter.teacherResource",{groupId:$scope.$item.id})
            }
            $scope.editCourse=function () {
                $scope.$item.dataCopy={};
                angular.copy($scope.$item,$scope.$item.dataCopy);
                $("#editCourseModal"+$scope.$index).modal("show");
            }

            $scope.updateCourse=function () {
                CRUDService.updateMethod("Group/updateGroup",$scope.$item).then(function (response) {
                    $("#editCourseModal"+$scope.$index).modal("hide");
                });
            }

            $scope.deleteCourse=function () {
                CRUDService.updateMethod("Group/deleteGroup",$scope.$item).then(function (response) {
                    console.log("deleteGroup",response)
                    CRUDHtmlService.deleteObject($scope.$index,$scope.$list);
                })
            }

            $scope.revertEdit=function () {
                angular.copy($scope.$item.dataCopy,$scope.$item);
            }




        }
    }
})

