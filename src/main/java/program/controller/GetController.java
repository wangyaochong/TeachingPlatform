package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;
import program.dao.PersonDao;
import program.entity.ItemEntity;
import program.urlBean.HeaderUrl;
import program.urlBean.RollPictureUrl;
import program.util.PageBean;
import program.util.PageListWithSingleBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class GetController {

//    @Resource(name = "GenericDao")
//    GenericDao genericDao;
    @Resource
    PersonDao genericDao;
    @RequestMapping("/getHeader")
    @ResponseBody
    public HeaderUrl getHeader(HttpServletRequest request) throws UnsupportedEncodingException {
        HeaderUrl headerUrl=new HeaderUrl(RequestContextUtils.getLocale(request));//传入国际化Locale
//        System.out.println(headerUrl);
        return headerUrl;
    }
    @RequestMapping("/getRollPicture")
    @ResponseBody
    public RollPictureUrl getRollPicture(HttpServletRequest request) throws UnsupportedEncodingException {
        RollPictureUrl rollPictureUrl =new RollPictureUrl(RequestContextUtils.getLocale(request));
        return rollPictureUrl;
    }

    @RequestMapping("/getItemEntityPage")//需要获取分页itemEntity共用的URL
    @ResponseBody
    public PageBean<ItemEntity> getItemEntityPage(@ModelAttribute ItemEntity itemEntity,
                                           @RequestParam Integer pageCurrentIndex, @RequestParam Integer pageRowSize,
                                           @RequestParam(required = false)  String orderBy, @RequestParam(required = false) Boolean orderAsc){
        //itemEntity是过滤条件，
        PageListWithSingleBean<ItemEntity> pageListWithSingleBean =new PageListWithSingleBean<ItemEntity>(genericDao.getSession(),itemEntity,
                pageCurrentIndex,pageRowSize,orderBy,orderAsc);
        //这个地方，由于返回时使用StringHttpMessageConverter将类转换为字符串，在转换为字符串的时候，需要获取所有的属性，
        //包括级联属性，类属性，因为是使用getCurrentSession，调用完session会关闭。
        // 所以在转换时就无法获取到真实的属性，因此不能使用懒加载
        return pageListWithSingleBean.getPageBean();
    }

}
