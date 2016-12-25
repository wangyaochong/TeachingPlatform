package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import program.controller.util.ResponseInfo;
import program.entity.ItemEntity;
import program.service.CrudService;
import program.service.PageListService;
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
public class UserController {
    @Resource
    UserService userService;
    @Resource
    CrudService crudService;
    @Resource
    PageListService<PersonEntity> pageListService;

    @RequestMapping("/getCurrent")
    @ResponseBody
//    获取当前在线用户
    public PersonEntity getCurrentUser() {
        return userService.getCurrentUser();
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public ResponseInfo deleteUser(String id) {
        crudService.deleteOne(PersonEntity.class, id);
        return new ResponseInfo("ok", null, null);
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public ResponseInfo updateUser(@ModelAttribute PersonEntity personEntity) {
        Serializable serializable = crudService.saveOrUpdateOne(personEntity);//serializable是id
        return new ResponseInfo("ok", null, serializable);
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public PersonEntity getUser(@RequestParam String id) {
        return (PersonEntity) crudService.getOne(PersonEntity.class, id);
    }

    @RequestMapping("/getUserPage")
    @ResponseBody
    public PageBean<PersonEntity> getUserPage(@ModelAttribute PersonEntity personEntity,
                                              @RequestParam Integer pageCurrentIndex, @RequestParam Integer pageRowSize,
                                              @RequestParam(required = false) String orderBy, @RequestParam(required = false) Boolean orderAsc) {
        return pageListService.getPageBean(personEntity, pageCurrentIndex, pageRowSize, orderBy, orderAsc);
    }

}
