/**
 * Created by wangy on 2016/12/15.
 */
app.service('ItemEntityService', function (CRUDService) {
    this.getItemEntityPage = function (data,pageCurrentIndex,pageRowSize,orderBy,orderAsc) {
        var url="ItemEntity/getItemEntityPage";
        var params={
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
        };
        return CRUDService.getMethod(url,params).then(function (data) {
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
        var url="ItemEntity/updateItemEntity";
        var params={
            id: data.id,
            title: data.title,
            description: data.description,
            type: data.type,
            isOpen: data.isOpen,
            createDate: data.createDate
        }
        return CRUDService.updateMethod(url,params).then(function (data) {
            return data.data;
        })
    }
    this.deleteItemEntity = function (data) {
        var url="ItemEntity/deleteItemEntity";
        var params={
            id: data.id
        }
        return CRUDService.updateMethod(url,params).then(function (data) {
            return data.data;
        })
    }
})