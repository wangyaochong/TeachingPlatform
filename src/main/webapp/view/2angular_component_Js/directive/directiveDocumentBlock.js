/**
 * Created by 【王耀冲】 on 【2017/1/11】 at 【20:03】.
 */
app.directive('directiveDocumentBlock', function () {
    return {
        restrict: 'E',
        templateUrl: templateUrls.directiveDocumentBlockUrl,
        replace: true,
        scope: {
            $list: "=",
            $item: "=",
            $index: "=",
            $width: "=",
            $padding: "=",
            $editable:"="
        },
        controller: function ($rootScope,$scope, $timeout, CRUDService, CRUDHtmlService, $uibModal) {
            $rootScope.$watch("localeProperties",function () {
                $scope.localeProperties=$rootScope.localeProperties;
            },true)
            $scope.localeProperties=$rootScope.localeProperties;

            $timeout(function () {
                $(".DocumentBlockPaddingWrapper").each(function () {
                    $(this).css("padding", $scope.$padding);
                })
                $(".DocumentBlockWrapper").each(function () {
                    $(this).width($scope.$width);
                    $(this).height($(this).width());//让高度等于宽度
                })

                var htmlFilePath;
                if(!angular.isUndefinedOrNull($scope.$item.resources[0].htmlAccessPath)){
                    htmlFilePath = $scope.$item.resources[0].htmlAccessPath;
                    htmlFilePath = htmlFilePath.replace(/\\/g, "/");//数据库存储的路径斜杠和前端的不一致
                    var postfix=htmlFilePath.split(".");
                    var type=postfix[postfix.length-1].toLowerCase();
                    var backImageBaseUrl='/TeachingPlatform/view/img/';
                    $("#DocumentBlockContent" + $scope.$index).css("background-image", "url(' " + backImageBaseUrl+type+".png" + " ')")
                    var selfWidth = $("#DocumentBlockWrapper" + $scope.$index).width();
                    $("#DocumentBlockContent" + $scope.$index).css("background-size", selfWidth + "px " + selfWidth + "px")
                }
            }, 100)

            $scope.editDocumentBlock = function () {
                console.log("editDocumentBlock")
                var modalInstance = $uibModal.open({
                    controller: "controllerModalDocumentBlock",
                    templateUrl: templateHtmlUrl + "modal/controllerModalDocumentBlock.html",
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
