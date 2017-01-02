/**
 * Created by wangy on 2016/12/24.
 */
app.directive('directiveEdit', function () {
    return {
        restrict: 'E',
        templateUrl: templateUrls.directiveEditUrl,
        replace: true,
        scope: {
            $item: "=",
            $index: "=",
            $list: "=",//近当有list的时候才显示delete按钮
            saveCallback:"&",
            deleteCallback:"&",
            beforeCallback:"&"
        },
        controller: function ($scope) {
            $scope.beforeEditing = function () {
                $scope.$item.dataCopy = {};
                angular.copy($scope.$item, $scope.$item.dataCopy);//保存编辑前的内容
                $scope.beforeCallback();
            }
            $scope.saveEditing = function () {
                angular.copy($scope.$item, $scope.$item.dataCopy);
                $scope.saveCallback();
            }
            $scope.deleteItem = function () {
                if (!angular.isUndefinedOrNull($scope.$list)) {
                    $scope.$list.splice($scope.$index, 1);
                }
                $scope.deleteCallback();
            }
            $scope.revertEditing = function () {
                angular.copy($scope.$item.dataCopy, $scope.$item);
                if ( angular.isUndefinedOrNull($scope.$list)|| $scope.$item.id == "") {//如果是取消新增的内容，则回退
                    $scope.$list.shift();
                }
            }
        }
    }
})