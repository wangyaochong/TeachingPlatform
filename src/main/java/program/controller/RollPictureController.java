package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;
import program.urlBean.RollPictureUrl;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by【王耀冲】on 【2016/12/15】 at 【1:17】.
 */
@Controller
@RequestMapping("/RollPicture")
public class RollPictureController {
    @RequestMapping("/getRollPicture")
    @ResponseBody
    public RollPictureUrl getRollPicture(HttpServletRequest request) throws UnsupportedEncodingException {
        RollPictureUrl rollPictureUrl = new RollPictureUrl(RequestContextUtils.getLocale(request));
        return rollPictureUrl;
    }
}
