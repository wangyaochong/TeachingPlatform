/**
 * Created by 【王耀冲】 on 【2017/2/13】 at 【20:24】.
 */
app.filter("filterLocale",function ($rootScope) {
    return function (property) {
            return $rootScope.localeProperties[property];
    }
})