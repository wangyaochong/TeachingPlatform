/**
 * Created by wangy on 2017/1/2.
 */
app.directive('directivePictureBlock', function () {
    return {
        restrict: 'E',
        templateUrl: templateUrls.directivePictureBlockUrl,
        replace: true,
        scope: {
            $list: "=",
            $item: "=",
            $index: "=",
            $width: "=",
            $padding: "=",
            $editable:"="
        },
        controller: function ($scope, $timeout, CRUDService, CRUDHtmlService, $uibModal) {
            //传过来的是itementity，监听背景图片变化的消息
            $scope.$on("pictureChange",function (event,data) {
                if(data.id==$scope.$item.id){
                    var htmlFilePath = data.resources[0].htmlAccessPath;
                    htmlFilePath = htmlFilePath.replace(/\\/g, "/");
                    $("#PictureBlockContent" + $scope.$index).css("background-image", "url(' " + htmlFilePath + " ')")
                }
            })

            $timeout(function () {
                $(".PictureBlockPaddingWrapper").each(function () {
                    $(this).css("padding", $scope.$padding);
                })

                $(".PictureBlockWrapper").each(function () {
                    $(this).width($scope.$width);
                    $(this).height($(this).width());//让高度等于宽度
                })
                var htmlFilePath = $scope.$item.resources[0].htmlAccessPath;
                htmlFilePath = htmlFilePath.replace(/\\/g, "/");

                $("#PictureBlockContent" + $scope.$index).css("background-image", "url(' " + htmlFilePath + " ')")
                var selfWidth = $("#PictureBlockWrapper" + $scope.$index).width();
                $("#PictureBlockContent" + $scope.$index).css("background-size", selfWidth + "px " + selfWidth + "px")
            }, 100)


            $scope.editPictureBlock = function () {
                console.log("editPictureBlock")
                var modalInstance = $uibModal.open({
                    controller: "controllerModalPictureBlock",
                    templateUrl: templateHtmlUrl + "modal/controllerModalPictureBlock.html",
                    resolve: {
                        modalParam: function () {
                            return {
                                itemEntity: $scope.$item
                            }
                        }
                    }
                })
                modalInstance.result.then(function (result) {
                    console.log(result)
                }, function (cancelResult) {
                    console.log(cancelResult)
                })
            }

            $scope.updateItem = function () {
                CRUDService.updateMethod("ItemEntity/updateItemEntity", $scope.$item).then(function (response) {
                    $("#editCourseModal" + $scope.$index).modal("hide");
                });
            }

            $scope.deleteItem = function () {
                CRUDService.getMethod("ItemEntity/deleteItemEntity", {id: $scope.$item.id}).then(function (response) {
                    CRUDHtmlService.deleteObject($scope.$index, $scope.$list);
                })
            }

            $scope.revertEdit = function () {
                angular.copy($scope.$item.dataCopy, $scope.$item);
            }
        }
    }
})

