/**
 * Created by wangy on 2016/12/15.
 */
app.service('ItemEntityService', function ($http) {
    this.updateItemEntity=function (data) {
        return $http({
            url: webRootUrl + "ItemEntity/updateItemEntity",
            params:{
                id:data.id,
                title:data.title,
                description:data.description,
                type:data.type,
                isOpen:data.isOpen,
                createDate:data.createDate
            },
            method:"get"
        }).then(function (data) {
            return data.data;
        },function (error) {
            console.log(error)
        })
    }
    this.deleteItemEntity=function (data) {
        return $http({
            url: webRootUrl + "ItemEntity/deleteItemEntity",
            params:{
                id:data.id,
            },
            method:"post"
        }).then(function (data) {
            return data.data;
        },function (error) {
            console.log(error)
        })
    }
})