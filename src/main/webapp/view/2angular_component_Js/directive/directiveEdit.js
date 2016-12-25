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
            $list: "=",
            showDelete: "@"//只有在列表中才需要这个showDelete属性，如果不设置，默认为不显示
        },
        controller: function ($scope) {
            $scope.beforeEditing = function (item) {
                item.dataCopy = {};
                angular.copy(item, item.dataCopy);//保存编辑前的内容

            }
            $scope.saveEditing = function (item) {
                angular.copy(item, item.dataCopy);
            }
            $scope.deleteItem = function (index, list) {
                if (!angular.isUndefinedOrNull(list)) {
                    list.splice(index, 1);
                }
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