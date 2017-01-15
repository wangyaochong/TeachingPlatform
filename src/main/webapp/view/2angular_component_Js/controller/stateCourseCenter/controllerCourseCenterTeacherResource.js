/**
 * Created by wangy on 2017/1/1.
 */
app.controller("controllerCourseCenterTeacherResource",function (CRUDService,$scope,$stateParams,$timeout,$uibModal) {
    $scope.isRollPicturePanelOpen = false;
    $scope.positionInited = false;//位置没有更新
    $scope.indexMessageList = [];
    $scope.rollPictureList = [];
    $scope.assignmentList = [];
    $scope.documentList = [];
    $scope.videoList = [];
    $scope.currentClassGroup = {};
    $scope.newItemEntity = {}
    $scope.hasPrivilege=angular.isUndefinedOrNull($stateParams.isStudent)?true:false;//是否是学生
    console.log("controllerCourseCenterTeacherResource")


    $scope.$on("resourceUpdated",function (event,param) {
        $scope.initResourceList();//当资源更新后，需要重新加载资源
    })

    $scope.initResourceList = function () {
        $scope.indexMessageList = [];
        $scope.rollPictureList = [];
        $scope.assignmentList = [];
        $scope.documentList = [];
        $scope.videoList = [];
        CRUDService.getMethod("ItemEntity/getItemEntityListByClassGroupId",{id: $stateParams.groupId}).then(function (response) {
            angular.forEach(response.data,function (one) {
                if (one.type == ItemType.ANNOUNCEMENT) {
                    $scope.indexMessageList.push(one);
                }
                if (one.type == ItemType.ROLLPICTURE) {
                    $scope.rollPictureList.push(one);
                }
                if (one.type == ItemType.ASSIGNMENT) {
                    $scope.assignmentList.push(one);
                }
                if (one.type == ItemType.DOCUMENT) {
                    $scope.documentList.push(one);
                }
                if (one.type == ItemType.VIDEO) {
                    $scope.videoList.push(one);
                }
            })
            console.log(response);
            $timeout(function () {
                autosize(document.querySelectorAll('textarea'));
            },100)
        })

    }

    $scope.initResourceList()


    CRUDService.getMethod("Group/getClassGroupById",{id: $stateParams.groupId}).then(function (response) {
        $scope.currentClassGroup = response.data;
    })


    $scope.updateItemEntity = function (data) {
        CRUDService.updateMethod("ItemEntity/updateItemEntity",data).then(function (response) {
            console.log(response)
            if (angular.isUndefinedOrNull(data.id) || data.id == "") {
                data.id = response.data;
            }
            $scope.initResourceList()
        })
    }

    $scope.addIndexMessage = function () {//添加首页消息
        var tmpMessage = {
            id: "",
            title: "",
            createDate: new Date().getTime(),
            type: ItemType.ANNOUNCEMENT,
            isOpen: true,
            classGroup: $scope.currentClassGroup,
            isEditing: true,
            dataCopy: {}
        }
        angular.copy(tmpMessage,tmpMessage.dataCopy);
        $scope.indexMessageList.unshift(tmpMessage);
    }
    $scope.addAssignment = function () {//添加作业
        var tmpMessage = {
            id: "",
            title: "",
            createDate: new Date().getTime(),
            type: ItemType.ASSIGNMENT,
            isOpen: true,
            classGroup: $scope.currentClassGroup,
            isEditing: true,
            dataCopy: {}
        }
        angular.copy(tmpMessage,tmpMessage.dataCopy);
        $scope.assignmentList.unshift(tmpMessage);
    }

    $scope.addDocument = function () {//添加文档
        var tmpMessage = {
            id: "",
            title: "",
            createDate: new Date().getTime(),
            type: ItemType.DOCUMENT,
            isOpen: true,
            classGroup: $scope.currentClassGroup,
            isEditing: true,
            dataCopy: {},
            resources: []
        }
        angular.copy(tmpMessage,tmpMessage.dataCopy);

        var modalInstance = $uibModal.open({
            controller: "controllerModalNewResource",
            templateUrl: templateHtmlUrl + "modal/controllerModalNewResource.html",
            resolve: {
                modalParam: function () {
                    return {
                        itemEntity: tmpMessage
                    }
                }
            }
        })
        modalInstance.result.then(function (result) {
            if(tmpMessage.resources.length!=0){
                $scope.documentList.unshift(tmpMessage);
            }
            console.log(result)
        },function (cancelResult) {
            if(tmpMessage.resources.length!=0){
                $scope.documentList.unshift(tmpMessage);
            }
            console.log(cancelResult)
        })
    }

    $scope.addVideo = function () {//添加文档
        var tmpMessage = {
            id: "",
            title: "",
            createDate: new Date().getTime(),
            type: ItemType.VIDEO,
            isOpen: true,
            classGroup: $scope.currentClassGroup,
            isEditing: true,
            dataCopy: {},
            resources: []
        }
        angular.copy(tmpMessage,tmpMessage.dataCopy);

        var modalInstance = $uibModal.open({
            controller: "controllerModalNewResource",
            templateUrl: templateHtmlUrl + "modal/controllerModalNewResource.html",
            resolve: {
                modalParam: function () {
                    return {
                        itemEntity: tmpMessage
                    }
                }
            }
        })
        modalInstance.result.then(function (result) {
            if(tmpMessage.resources.length!=0){
                $scope.videoList.unshift(tmpMessage);
            }
            console.log(result)
        },function (cancelResult) {
            if(tmpMessage.resources.length!=0){
                $scope.videoList.unshift(tmpMessage);
            }
            console.log(cancelResult)
        })
    }



    $scope.addFileToItem = function (item) {
        var modalInstance = $uibModal.open({
            controller: "controllerModalNewResource",
            templateUrl: templateHtmlUrl + "modal/controllerModalNewResource.html",
            resolve: {
                modalParam: function () {
                    return {
                        itemEntity: item
                    }
                }
            }
        })
        modalInstance.result.then(function (result) {
            console.log(result)
        },function (cancelResult) {
            console.log(cancelResult)
        })
    }

    //传入的是item，然后根据index删除资源
    $scope.deleteFileFromItem = function (item,index) {
        item.resources.splice(index,1);
        $scope.updateItemEntity(item);
    }


    $scope.InitNewRollPictureModal = function () {
        $scope.newItemEntity = {
            description: "",
            type: ItemType.ROLLPICTURE,
            createDate: new Date().getTime(),
            isOpen: true,
            classGroup: $scope.currentClassGroup,
            dataCopy: {}
        }
        $('#input-id').fileinput('clear');
        $(".fileUploadInput").fileinput("reset");
    }
    $scope.closeRollPictureModal = function () {
        $('#input-id').fileinput('clear');
        $(".fileUploadInput").fileinput("reset");
    }
    $scope.initPosition = function () {//因为是使用collapse，所以宽度需要等展开才能正确获取到
        $timeout(function () {
            $("#newRollPictureDiv").css("height",$("#newRollPictureDiv").width())
            $("#newDocumentDiv").css("height",$("#newDocumentDiv").width())
            $("#newVideoDiv").css("height",$("#newVideoDiv").width())
        },0)
    }

    $scope.isOpenCollapseTwo = function () {
        var test = $("#collapseTwo").attr('class');
        return $("#collapseTwo").attr('class') == "panel-collapse collapsing" || $("#collapseTwo").attr('class') == "panel-collapse collapse in";
    }
    $scope.isOpenCollapseFour=function () {
        var test = $("#collapseFour").attr('class');
        return $("#collapseFour").attr('class') == "panel-collapse collapsing" || $("#collapseFour").attr('class') == "panel-collapse collapse in";
    }
    $scope.isOpenCollapseFive=function () {
        return $("#collapseFive").attr('class') == "panel-collapse collapsing" || $("#collapseFive").attr('class') == "panel-collapse collapse in";
    }


    $timeout(function () {
        $(".fileUploadInput").fileinput({
            language: "zh",//使用中文
            showCaption: true,//显示标题
            showDelete: true,//显示删除按钮
            showUpload: true,//显示上传按钮（总按钮）
            dropZoneEnabled: false,//禁用拖放功能
            //不使用异步上传，这样就能以一个response返回多个结果，
            //点击总的上传按钮时，触发的是filebatchuploadsuccess事件
            //当uploadAsync=true时，不管点击预览上传还是总的上传按钮，都是异步的
            //也就是说一个文件对应一个response
            uploadAsync: false,
            browseLabel: "选择",//标签
            uploadUrl: webRootUrl + "File/uploadListFile",//上传URL
            //为了方便，仅使用filebatchuploadsuccess事件
        }).on('filebatchuploadsuccess',function (event,data,previewId,index) {
            // console.log(event);
            console.log('filebatchuploadsuccess',data.response);
            $scope.newItemEntity.resources = [];
            var oneResource = {
                id: data.response.data[0]
            }
            $scope.newItemEntity.resources.push(oneResource);//文件只允许一次上传一张，返回的是一张张图片的id列表
            $scope.updateItemEntity($scope.newItemEntity);
            // $scope.rollPictureList.unshift($scope.newItemEntity);
        })
    },100);
})