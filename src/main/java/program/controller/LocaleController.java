package program.controller;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
import java.util.Locale;

/**
 * Created by 【王耀冲】 on 【2017/2/13】 at 【19:55】.
 */
@Controller
@RequestMapping("Locale")
public class LocaleController {

    @Resource
    CookieLocaleResolver resolver;

    @Resource
    LocaleService localeService;

    @RequestMapping("getLocale")
    @ResponseBody
    public ResponseInfo getLocale(HttpServletRequest request){
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals("Language")){
                Locale locale=new Locale(cookie.getValue());
                return new ResponseInfo(ResponseFlag.STATUS_OK,null,locale) ;
            }
        }
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,request.getLocale()) ;
    }
    @RequestMapping("/getLocaleProperties")
    @ResponseBody
    public ResponseInfo getLocaleProperties(HttpServletRequest request){
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,localeService.getAllLocaleProperties(request.getLocale()));
    }

}
