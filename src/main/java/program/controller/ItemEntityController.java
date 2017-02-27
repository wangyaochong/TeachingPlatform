package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.dao.PersonDao;
import program.entity.GroupEntity;
import program.entity.ItemEntity;
import program.entity.PersonEntity;
import program.entity.entityInterface.IEntity;
import program.entity.type.ItemType;
import program.service.CrudService;
import program.service.ItemEntityService;
import program.service.UserService;
import program.service.bean.PageBean;
import program.service.PageListService;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    UserService userService;
    @Resource
    PageListService<ItemEntity> pageListService;
    @Resource
    ItemEntityService itemEntityService;



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

    @RequestMapping("/getItemEntityListByClassGroupId")//通过班级groupId获取该班级的所有资源
    @ResponseBody
    public ResponseInfo getItemEntityListByClassGroupId(@RequestParam String id){
        GroupEntity groupEntity=new GroupEntity();
        groupEntity.setId(id);//这里传来的id是classGroupId
        ItemEntity itemEntity=new ItemEntity();
        itemEntity.setClassGroup(groupEntity);
        List<ItemEntity> listByCondition = crudService.getListByCondition(itemEntity);
        Collections.sort(listByCondition, new Comparator<ItemEntity>() {
            @Override
            public int compare(ItemEntity o1, ItemEntity o2) {//按照时间创建顺序排序
                return (int) (o2.getCreateDate()-o1.getCreateDate());
            }
        });
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,listByCondition);
    }


    @RequestMapping("/updateItemEntity")
    @ResponseBody//update操作，如果不带id，那么就保存
    public ResponseInfo updateItemEntity(@RequestBody ItemEntity itemEntity) {


        if(itemEntity.getCreator()==null){
            PersonEntity currentUser = userService.getCurrentUser();
            itemEntity.setCreator(currentUser);
        }
        Serializable serializable = crudService.saveOrUpdateOne(itemEntity);
        IEntity oneById = crudService.getOneById(ItemEntity.class, (String) serializable);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,oneById);
    }

    @RequestMapping("/deleteItemEntity")
    @ResponseBody
    public ResponseInfo deleteItemEntity(@RequestParam(value = "id") String id) {
        crudService.deleteOneById(ItemEntity.class,id);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,null);
    }

    @RequestMapping("/getCurrentUserRollPicture")
    @ResponseBody
    public ResponseInfo getCurrentUserRollPicture(){
        List<ItemEntity> currentUserItemEntityByType = itemEntityService.getCurrentUserItemEntityByType(ItemType.ROLLPICTURE);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,currentUserItemEntityByType);
    }

    @RequestMapping("/getCurrentUserIndexMessage")
    @ResponseBody
    public ResponseInfo getCurrentUserIndexMessage(){
        List<ItemEntity> currentUserItemEntityByType = itemEntityService.getCurrentUserItemEntityByType(ItemType.ANNOUNCEMENT);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,currentUserItemEntityByType);
    }

    @RequestMapping("/getCurrentUserAssignment")
    @ResponseBody
    public ResponseInfo getCurrentUserAssignment(){
        List<ItemEntity> currentUserItemEntityByType = itemEntityService.getCurrentUserItemEntityByType(ItemType.ASSIGNMENT);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,currentUserItemEntityByType);
    }

    @RequestMapping("/getCurrentUserDocument")
    @ResponseBody
    public ResponseInfo getCurrentUserDocument(){
        List<ItemEntity> currentUserItemEntityByType = itemEntityService.getCurrentUserItemEntityByType(ItemType.DOCUMENT);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,currentUserItemEntityByType);
    }

    @RequestMapping("/getCurrentUserVideo")
    @ResponseBody
    public ResponseInfo getCurrentUserVideo(){
        List<ItemEntity> currentUserItemEntityByType = itemEntityService.getCurrentUserItemEntityByType(ItemType.VIDEO);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,currentUserItemEntityByType);
    }
    @RequestMapping("/getItemEntityById")
    @ResponseBody
    public ResponseInfo getFileEntityById(@RequestParam String id){
        IEntity oneById = crudService.getOneById(ItemEntity.class, id);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,oneById);
    }
}
