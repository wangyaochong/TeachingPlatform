/**
 * Created by wangy on 2017/1/1.
 */
app.directive('directiveCourseBlock', function () {
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
                    //设置为一张固定图片
                    var backImageBaseUrl='/TeachingPlatform/view/img/courseBlockBackground.png';
                    $(this).css("background-image", "url(' "+ backImageBaseUrl+" ')");
                    var selfWidth = $("#DocumentBlockWrapper" + $scope.$index).width();
                    $(this).css("background-size", selfWidth + "px " + selfWidth + "px");

                    //设置随机颜色
                    // var r=(Math.random()*1000%100+150);
                    // r=parseInt(r);
                    // var g=(Math.random()*1000%100+150);
                    // g=parseInt(g);
                    // var b=(Math.random()*1000%100+150);
                    // b=parseInt(b);
                    // $(this).css("background-color", "rgb("+r+","+g+","+b+")");
                })


            }, 100)

            $scope.coursePersonClick=function () {
                $state.go("courseCenter.coursePerson",{groupId:$scope.$item.id})
            }
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


            window.onresize=function () {
                的
            }
            window.onresize =function () {
                $(".courseblockwrapper").each(function () {
                    $(this).width($scope.$width);
                    //必须要多设置几次，不然会出错
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $(this).height($(this).width());//让高度等于宽度
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                    $("#newCourseButtonDiv").css("height",$("#newCourseButtonDiv").width())
                })
            }
        }
    }
})

