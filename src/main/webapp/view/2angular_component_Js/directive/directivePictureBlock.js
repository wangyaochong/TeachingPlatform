/**
 * Created by wangy on 2017/1/2.
 */
app.directive('directivePictureBlock', function () {
    return {
        restrict: 'E',
        templateUrl: templateUrls.directivePictureBlockUrl,
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
                $(".PictureBlockPaddingWrapper").each(function () {
                    $(this).css("padding", $scope.$padding);
                })

                $(".PictureBlockWrapper").each(function () {
                    $(this).width($scope.$width);
                    $(this).height($(this).width());//让高度等于宽度
                })
                var htmlFilePath=$scope.$item.resources[0].htmlAccessPath;
                htmlFilePath = htmlFilePath.replace(/\\/g, "/");

                $("#PictureBlockContent"+$scope.$index).css("background-image","url(' "+htmlFilePath+" ')")
                var selfWidth=$("#PictureBlockWrapper"+$scope.$index).width();
                $("#PictureBlockContent"+$scope.$index).css("background-size",selfWidth+"px "+selfWidth+"px")
                // $(".PictureBlockContent").each(function () {
                //     var r=(Math.random()*1000%100+150);
                //     r=parseInt(r);
                //     var g=(Math.random()*1000%100+150);
                //     g=parseInt(g);
                //     var b=(Math.random()*1000%100+150);
                //     b=parseInt(b);
                //     $(this).css("background-color", "rgb("+r+","+g+","+b+")");
                // })
            }, 100)


            $scope.courseResourceClick = function () {
                $state.go("courseCenter.teacherResource",{groupId:$scope.$item.id})
            }
            $scope.editCourse=function () {
                $scope.$item.dataCopy={};
                angular.copy($scope.$item,$scope.$item.dataCopy);
                $("#editCourseModal"+$scope.$index).modal("show");
            }

            $scope.updateItem=function () {
                CRUDService.updateMethod("ItemEntity/updateItemEntity",$scope.$item).then(function (response) {
                    $("#editCourseModal"+$scope.$index).modal("hide");
                });
            }

            $scope.deleteItem=function () {
                CRUDService.getMethod("ItemEntity/deleteItemEntity",{id:$scope.$item.id}).then(function (response) {
                    CRUDHtmlService.deleteObject($scope.$index,$scope.$list);
                })
            }

            $scope.revertEdit=function () {
                angular.copy($scope.$item.dataCopy,$scope.$item);
            }
        }
    }
})

