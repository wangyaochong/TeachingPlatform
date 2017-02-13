/**
 * Created by 【王耀冲】 on 【2017/2/13】 at 【20:24】.
 */
app.filter("filterLocale",function ($rootScope,CRUDService) {
    return function (property) {
        // if(angular.isUndefinedOrNull( $rootScope.localeLange)){
        //     return  CRUDService.getMethod("Locale/getLocale",{}).then(function (response) {
        //         $rootScope.localeLange=response.data;
        //         return appLocale[$rootScope.localeLange][property];
        //     })
        // }else{
            return appLocale[$rootScope.localeLange][property];
        // }
    }
})