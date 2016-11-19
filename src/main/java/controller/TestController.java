package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by 2 on 2016/11/14.
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ){
        RequestContext requestContext=new RequestContext(httpServletRequest);
        Locale locale = requestContext.getLocale();
        System.out.println(locale);
        return "王耀冲";
    }

}
