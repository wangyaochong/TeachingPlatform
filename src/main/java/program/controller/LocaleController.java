package program.controller;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.service.LocaleService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by 【王耀冲】 on 【2017/2/13】 at 【19:55】.
 */
@Controller
@RequestMapping("Locale")
public class LocaleController {

    @Resource
    LocaleService localeService;

    @RequestMapping("getLocale")
    @ResponseBody
    public ResponseInfo getLocale(HttpServletRequest request, HttpServletResponse response){
        Locale locale1 = request.getLocale();
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals("CookieLanguage")){
                Locale locale=new Locale(cookie.getValue());
                return new ResponseInfo(ResponseFlag.STATUS_OK,null,locale) ;
            }
        }
        //如果前面没有找到，则添加cookie
        response.addCookie(new Cookie("CookieLanguage",request.getLocale().toString()));
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,request.getLocale()) ;
    }
    public void deleteCookie(Cookie cookie){
        cookie.setValue(null);
        cookie.setMaxAge(0);//一个星期内登录有效
        cookie.setPath("/");//设置cookie的存储位置
    }
    @RequestMapping("updateLocale")
    @ResponseBody
    public ResponseInfo updateLocale(HttpServletRequest request, HttpServletResponse response, @RequestParam String localeLanguage){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("CookieLanguage")){
                deleteCookie(cookie);//删除旧的，然后更新
                Cookie cookie1=new Cookie("CookieLanguage",localeLanguage);
                cookie1.setValue(localeLanguage);
                cookie1.setMaxAge(60*60*24*7);//一个星期内登录有效
                cookie1.setPath("/");//设置cookie的存储位置
                response.addCookie(cookie1);//还需要再添加一次以覆盖
                return new ResponseInfo(ResponseFlag.STATUS_OK,null,"ok") ;
            }
        }
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,"ok");
    }
    @RequestMapping("getLocaleProperties")
    @ResponseBody
    public ResponseInfo getLocaleProperties(HttpServletRequest request){
        Locale locale=null;
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals("CookieLanguage")){
                switch (cookie.getValue().toLowerCase()){
                    case "en_us":
                        locale=new Locale("en","US");
                        break;
                    case "zh_cn":
                        locale=new Locale("zh","CN");
                        break;
                }
            }
        }
        if(locale!=null){
                return new ResponseInfo(ResponseFlag.STATUS_OK,null,localeService.getAllLocaleProperties(locale));
        }
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,localeService.getAllLocaleProperties(request.getLocale()));
    }

}
