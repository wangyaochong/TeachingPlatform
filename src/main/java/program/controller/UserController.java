package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import program.controller.base.DataOperateBase;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.entity.GroupEntity;
import program.entity.entityInterface.IEntity;
import program.service.UserService;
import program.entity.PersonEntity;
import program.service.bean.PageBean;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by【王耀冲】on 【2016/12/15】 at 【1:13】.
 */
@Controller
@RequestMapping("/User")
public class UserController  extends DataOperateBase{
    @Resource
    UserService userService;
    @RequestMapping("/getCurrent")
    @ResponseBody
//    获取当前在线用户
    public PersonEntity getCurrentUser() {
        return userService.getCurrentUser();
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public ResponseInfo deleteUser(@RequestBody PersonEntity personEntity) {
        crudService.deleteOneById(PersonEntity.class, personEntity.getId());
        return new ResponseInfo("ok", null, null);
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    //使用post请求直接发送一个发个对象，可以使用RequestBody注解获取，否则有可能会报错
    public ResponseInfo updateUser(@RequestBody PersonEntity personEntity) {
        Serializable serializable = crudService.saveOrUpdateOne(personEntity);//serializable是id
        if(personEntity.getPrivilegeEntityList()==null||personEntity.getPrivilegeEntityList().size()==0){
            PersonEntity oneById = (PersonEntity) crudService.getOneById(PersonEntity.class, (String) serializable);
            oneById.setPrivilegeEntityList(null);
            crudService.getCurrentSession().update(oneById);
        }
        return new ResponseInfo("ok", null, serializable);
    }
    @RequestMapping("/getUser")
    @ResponseBody
    public PersonEntity getUser(@RequestParam String id) {
        return (PersonEntity) crudService.getOneById(PersonEntity.class, id);
    }

    @RequestMapping("/getUserPage")
    @ResponseBody
    public PageBean<PersonEntity> getUserPage(@ModelAttribute PersonEntity personEntity,
                                              @RequestParam Integer pageCurrentIndex, @RequestParam Integer pageRowSize,
                                              @RequestParam(required = false) String orderBy, @RequestParam(required = false) Boolean orderAsc) {
        return pageListService.getPageBean(personEntity, pageCurrentIndex, pageRowSize, orderBy, orderAsc);
    }
    @RequestMapping("/addCurrentUserToGroup")
    @ResponseBody
    public ResponseInfo addCurrentUserToGroup(@RequestParam String groupId){
        IEntity oneById = crudService.getOneById(GroupEntity.class, groupId);
        userService.addCurrentUserToGroup((GroupEntity) oneById);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,null);
    }
}
