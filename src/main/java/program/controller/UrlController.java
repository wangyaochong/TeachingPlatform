package program.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;
import program.dao.GenericDao;
import program.entity.ItemEntity;
import program.urlBean.HeaderUrl;
import program.urlBean.RollPictureUrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class UrlController {

    @Resource(name = "GenericDao")
    GenericDao genericDao;
    @RequestMapping("/headerUrl")
    @ResponseBody
    public HeaderUrl headerUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        HeaderUrl headerUrl=new HeaderUrl(RequestContextUtils.getLocale(request));//传入国际化Locale
        System.out.println(headerUrl);
        return headerUrl;
    }
    @RequestMapping("/rollPictureUrl")
    @ResponseBody
    public RollPictureUrl rollPictureUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        RollPictureUrl rollPictureUrl =new RollPictureUrl(RequestContextUtils.getLocale(request));
        return rollPictureUrl;
    }
    @RequestMapping("/frontMessageUrl")
    public ItemEntity frontMessageUrl(){
        return null;
    }

}
