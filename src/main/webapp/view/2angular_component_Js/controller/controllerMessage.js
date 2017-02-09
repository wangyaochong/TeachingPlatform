/**
 * Created by 【王耀冲】 on 【2017/2/7】 at 【18:57】.
 */
app.controller('controllerMessage',function ($scope,CRUDService) {
    console.log('controllerMessage');
    CRUDService.getMethod("Message/getCurrentUserMessage",{}).then(function (response) {
        console.log(response)
        $scope.messages = response.data;
        var countMap = _.countBy($scope.messages,function (row) {
            return row.hasRead;
        })
        $scope.unReadMessageCount = countMap['false'];
        console.log(countMap)
    });

    $scope.getMessageType = function (string) {
        var messageMap = {};
        messageMap['APPLY_JOIN_CLASS'] = '班级申请';
        messageMap['NORMAL'] = '普通消息';
        return messageMap[string];
    }
    $scope.updateMessage = function (agree,message) {
        message.hasRead = true;
        if (agree == false) {
            message.itemEntity.description+=":拒绝";
        }
        else if (agree == true) {
            message.itemEntity.description+=":同意";
        }
        CRUDService.updateMethod("ItemEntity/updateItemEntity",message.itemEntity);
        CRUDService.updateMethod("Message/updateMessage",message);
    }


})