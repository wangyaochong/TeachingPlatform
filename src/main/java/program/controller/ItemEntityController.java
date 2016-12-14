package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.dao.PersonDao;
import program.entity.ItemEntity;
import program.entity.ResourceEntity;
import program.util.EntityUtil;
import program.util.PageBean;
import program.util.PageListWithSingleBean;

import javax.annotation.Resource;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by【王耀冲】on 【2016/12/15】 at 【1:21】.
 */
@Controller
@RequestMapping("/ItemEntity")
public class ItemEntityController {
    @Resource
    PersonDao genericDao;

    @RequestMapping("/getItemEntityPage")//需要获取分页itemEntity共用的URL
    @ResponseBody
    public PageBean<ItemEntity> getItemEntityPage(@ModelAttribute ItemEntity itemEntity,
                                                  @RequestParam Integer pageCurrentIndex, @RequestParam Integer pageRowSize,
                                                  @RequestParam(required = false) String orderBy, @RequestParam(required = false) Boolean orderAsc) {
        //itemEntity是过滤条件，
        PageListWithSingleBean<ItemEntity> pageListWithSingleBean = new PageListWithSingleBean<ItemEntity>(genericDao.getSession(), itemEntity,
                pageCurrentIndex, pageRowSize, orderBy, orderAsc);
        //这个地方，由于返回时使用StringHttpMessageConverter将类转换为字符串，在转换为字符串的时候，需要获取所有的属性，
        //包括级联属性，类属性，因为是使用getCurrentSession，调用完session会关闭。
        // 所以在转换时就无法获取到真实的属性，因此不能使用懒加载
        return pageListWithSingleBean.getPageBean();
    }

    @RequestMapping("/updateItemEntity")
    @ResponseBody
    public ResponseInfo updateItemEntity(@ModelAttribute ItemEntity itemEntity) {
        ItemEntity update = genericDao.getSession().get(ItemEntity.class, itemEntity.getId());
        EntityUtil.updateEntity(update, itemEntity);
        genericDao.getSession().update(update);
        return new ResponseInfo(ResponseInfo.STATUS_OK);
    }

    @RequestMapping("/deleteItemEntity")
    @ResponseBody
    public ResponseInfo deleteItemEntity(@RequestParam(value = "id") String id) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(id);
        genericDao.getSession().delete(itemEntity);
        return new ResponseInfo(ResponseInfo.STATUS_OK);
    }
}
