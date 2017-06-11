package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;
import program.util.urlBean.HeaderUrl;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by【王耀冲】on 【2016/12/15】 at 【1:17】.
 */
@Controller
@RequestMapping("/Header")
public class HeaderController {
    @RequestMapping("/getHeaderUrl")
    @ResponseBody
    public HeaderUrl getHeaderUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        HeaderUrl headerUrl = new HeaderUrl(RequestContextUtils.getLocale(request));//传入国际化Locale
//        System.out.println(headerUrl);
        return headerUrl;
    }
}
