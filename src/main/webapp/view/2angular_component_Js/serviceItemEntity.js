/**
 * Created by wangy on 2016/12/15.
 */
app.service('ItemEntityService', function ($http) {
    this.getItemEntityPage = function (data,pageCurrentIndex,pageRowSize,orderBy,orderAsc) {
        return $http({
            url: webRootUrl + "ItemEntity/getItemEntityPage",
            params: {
                id: data.id,
                title: data.title,
                description: data.description,
                type: data.type,
                isOpen: data.isOpen,
                createDate: data.createDate,
                pageCurrentIndex:pageCurrentIndex,
                pageRowSize:pageRowSize,
                orderBy:orderBy,
                orderAsc:orderAsc
            },
            method: "get"
        }).then(function (data) {
            angular.forEach(data.data.pageList, function (data) {
                data.isEditing = false;//是否正在编辑为假
                data.dataCopy = {};
                angular.copy(data, data.dataCopy);//保存编辑前的内容
            })
            return data.data;
        }, function (error) {
            console.log(error)
        })
    }

    this.updateItemEntity = function (data) {
        return $http({
            url: webRootUrl + "ItemEntity/updateItemEntity",
            params: {
                id: data.id,
                title: data.title,
                description: data.description,
                type: data.type,
                isOpen: data.isOpen,
                createDate: data.createDate
            },
            method: "get"
        }).then(function (data) {
            return data.data;
        }, function (error) {
            console.log(error)
        })
    }
    this.deleteItemEntity = function (data) {
        return $http({
            url: webRootUrl + "ItemEntity/deleteItemEntity",
            params: {
                id: data.id,
            },
            method: "post"
        }).then(function (data) {
            return data.data;
        }, function (error) {
            console.log(error)
        })
    }
})