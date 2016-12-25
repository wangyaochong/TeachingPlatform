/**
 * Created by 【王耀冲】 on 【2016/12/13】 at 【19:20】.
 */
app.service('UserService', function ($http) {
    this.getCurrentUser = function () {
        return $http({
            url: webRootUrl + "User/getCurrent"
        }).then(function (data) {
            return data.data
        })
    }
    this.getCurrentUserPrivileages=function () {
        return this.getCurrentUser().then(function (data) {
            return data["privilegeEntityList"];
        })
    }
    this.hasFrontMessagePriv=function () {
        return this.getCurrentUserPrivileages().then(function (data) {
            return data[0]["frontMessage"];
        })
    }
    this.hasDocumentPriv=function () {
        return this.getCurrentUser().then(function (data) {
            return data[0]["document"];
        })
    }
    this.hasVideoPriv=function () {
        return this.getCurrentUser().then(function (data) {
            return data[0]["video"];
        })
    }
    this.hasAssignmentPriv=function () {
        return this.getCurrentUser().then(function (data) {
            return data[0]["assignment"];
        })
    }
    this.hasPersonalInfomationPriv=function () {
        return this.getCurrentUser().then(function (data) {
            return data[0]["personalInfomation"];
        })
    }
})