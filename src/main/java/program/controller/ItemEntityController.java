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
import program.service.CrudService;
import program.service.bean.PageBean;
import program.service.PageListService;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by【王耀冲】on 【2016/12/15】 at 【1:21】.
 */
@Controller
@RequestMapping("/ItemEntity")
public class ItemEntityController {
    @Resource
    PersonDao genericDao;
    @Resource
    CrudService crudService;//增删改查操作
    @Resource
    PageListService<ItemEntity> pageListService;
    @RequestMapping("/getItemEntityPage")//需要获取分页itemEntity共用的URL
    @ResponseBody
    public PageBean<ItemEntity> getItemEntityPage(@ModelAttribute ItemEntity itemEntity,
                                                  @RequestParam Integer pageCurrentIndex, @RequestParam Integer pageRowSize,
                                                  @RequestParam(required = false) String orderBy, @RequestParam(required = false) Boolean orderAsc) {
        //itemEntity是过滤条件，
        //这个地方，由于返回时使用StringHttpMessageConverter将类转换为字符串，在转换为字符串的时候，需要获取所有的属性，
        //包括级联属性，类属性，因为是使用getCurrentSession，调用完session会关闭。
        // 所以在转换时就无法获取到真实的属性，因此不能使用懒加载
        return pageListService.getPageBean(itemEntity,
                pageCurrentIndex, pageRowSize, orderBy, orderAsc);
    }

    @RequestMapping("/updateItemEntity")
    @ResponseBody//update操作，如果不带id，那么就保存
    public ResponseInfo updateItemEntity(@ModelAttribute ItemEntity itemEntity) {
        Serializable serializable = crudService.saveOrUpdateOne(itemEntity);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,serializable);
    }

    @RequestMapping("/deleteItemEntity")
    @ResponseBody
    public ResponseInfo deleteItemEntity(@RequestParam(value = "id") String id) {
        crudService.deleteOneById(ItemEntity.class,id);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,null);
    }
}
