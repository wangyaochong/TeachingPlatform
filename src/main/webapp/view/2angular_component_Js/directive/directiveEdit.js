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
            $scope.beforeEditing = function (item) {
                item.dataCopy = {};
                angular.copy(item, item.dataCopy);//保存编辑前的内容
                $scope.beforeCallback();
            }
            $scope.saveEditing = function (item) {
                angular.copy(item, item.dataCopy);
                $scope.saveCallback();
            }
            $scope.deleteItem = function (index, list) {
                if (!angular.isUndefinedOrNull(list)) {
                    list.splice(index, 1);
                }
                $scope.deleteCallback();
            }
            $scope.revertEditing = function (item, list) {
                angular.copy(item.dataCopy, item);
                if ( (!(angular.isUndefinedOrNull(list)))  || item.id == "") {//如果是取消新增的内容，则回退
                    list.shift();
                }
            }
        }
    }
})