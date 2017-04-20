/**
 * Created by wangy on 2017/4/20.
 */
app.service("ConfirmModalService",function ($uibModal) {
    this.showModal=function (title,content,yesCallBack) {
        var modalInstance=$uibModal.open({
            templateUrl:templateHtmlUrl+"modal/serviceConfirmModal.html",
            controller:function ($scope) {
                $scope.title=title;
                $scope.content=content;
                $scope.closeModal=function () {
                    modalInstance.dismiss();
                }
            }
        })
    }
})