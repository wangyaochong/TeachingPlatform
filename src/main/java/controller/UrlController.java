package controller;
import urlBean.HeaderUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class UrlController {
    @RequestMapping("/headerUrl")
    @ResponseBody
    public HeaderUrl headerUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        RequestContext requestContext=new RequestContext(request);
        HeaderUrl headerUrl=new HeaderUrl(requestContext.getLocale());
        System.out.println(headerUrl);
        return headerUrl;
    }
}
