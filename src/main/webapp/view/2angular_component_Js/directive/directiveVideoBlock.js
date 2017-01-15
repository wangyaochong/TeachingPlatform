/**
 * Created by 【王耀冲】 on 【2017/1/11】 at 【20:03】.
 */
app.directive('directiveVideoBlock', function () {
    return {
        restrict: 'E',
        templateUrl: templateUrls.directiveVideoBlockUrl,
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
            $timeout(function () {
                $(".VideoBlockPaddingWrapper").each(function () {
                    $(this).css("padding", $scope.$padding);
                })
                $(".VideoBlockWrapper").each(function () {
                    $(this).width($scope.$width);
                    $(this).height($(this).width());//让高度等于宽度
                })

                var htmlFilePath;
                if(!angular.isUndefinedOrNull($scope.$item.resources[0].htmlAccessPath)){
                    htmlFilePath = $scope.$item.resources[0].htmlAccessPath;
                    htmlFilePath = htmlFilePath.replace(/\\/g, "/");
                    var postfix=htmlFilePath.split(".");
                    var type=postfix[postfix.length-1];
                    var backImageBaseUrl='/TeachingPlatform/view/img/';
                    $("#VideoBlockContent" + $scope.$index).css("background-image", "url(' " + backImageBaseUrl+type+".png" + " ')")
                    var selfWidth = $("#VideoBlockWrapper" + $scope.$index).width();
                    $("#VideoBlockContent" + $scope.$index).css("background-size", selfWidth + "px " + selfWidth + "px")
                }
            }, 100)

            $scope.editVideoBlock = function () {
                console.log("editVideoBlock")
                var modalInstance = $uibModal.open({
                    controller: "controllerModalVideoBlock",
                    templateUrl: templateHtmlUrl + "modal/controllerModalVideoBlock.html",
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
