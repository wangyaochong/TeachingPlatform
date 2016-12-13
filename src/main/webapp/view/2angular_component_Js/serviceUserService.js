/**
 * Created by 【王耀冲】 on 【2016/12/13】 at 【19:20】.
 */
app.service('UserService', function ($http,$q) {
    this.getCurrentUser = function () {
        return $http({
            url: webRootUrl + "getCurrentUser"
        }).then(function (data) {
            return data.data
        })
    }
    this.getCurrentUserPrivileages=function () {
        return this.getCurrentUser().then(function (data) {
            return data["privilegeEntity"];
        })
    }
    this.hasFrontMessagePriv=function () {
        return this.getCurrentUserPrivileages().then(function (data) {
            return data["frontMessage"];
        })
    }
    this.hasDocumentPriv=function () {
        return this.getCurrentUser().then(function (data) {
            return data["document"];
        })
    }
    this.hasVideoPriv=function () {
        return this.getCurrentUser().then(function (data) {
            return data["video"];
        })
    }
    this.hasAssignmentPriv=function () {
        return this.getCurrentUser().then(function (data) {
            return data["assignment"];
        })
    }
    this.hasPersonalInfomationPriv=function () {
        return this.getCurrentUser().then(function (data) {
            return data["personalInfomation"];
        })
    }
})