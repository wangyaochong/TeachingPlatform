/**
 * Created by wangy on 2016/12/24.
 */
app.service('CRUDHtmlService',function () {
    //当编辑取消时
    this.revertObject=function (data,list) {
        angular.copy(data.dataCopy, data);
        if (data.id == undefined || data.id == "") {//如果是取消新增的内容，则回退
            list.shift();
        }
    }
    //保存编辑时
    this.saveObject=function (data,response) {
        if (angular.isUndefinedOrNull(data.id) || data.id == "") {
            response.then(function (resp) {
                data.id = resp.data;
            })
        }
        angular.copy(data, data.dataCopy);
    }
    //删除对象
    this.deleteObject=function (index,list) {
        if(!angular.isUndefinedOrNull(list)){
            list.splice(index, 1);
        }
    }
    //从数组的开头插入一个元素
    this.addObject=function (obj,list) {
        list.unshift(obj)
    }
})