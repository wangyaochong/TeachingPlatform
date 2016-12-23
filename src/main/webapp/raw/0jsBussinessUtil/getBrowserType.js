/**
 * Created by 【王耀冲】 on 【2016/12/22】 at 【11:01】.
 */
function getBrowserType() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = (userAgent.indexOf("Opera") > -1);
    if (userAgent.indexOf("Edge") > -1) {
        return "Edge";
    }
    if (userAgent.indexOf("Opera") > -1) {
        return "Opera";
    }
    if (userAgent.indexOf("Firefox") > -1) {
        return "Firefox";
    }
    if (userAgent.indexOf("Chrome") > -1) {
        return "Chrome";
    }
    if (userAgent.indexOf("Safari") > -1) {
        return "Safari";
    }
    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
        return "IE";
    }
}