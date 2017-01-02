package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import program.controller.base.DataOperateBase;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.entity.GroupEntity;
import program.entity.PersonEntity;
import program.entity.type.GroupType;
import program.service.UserService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by【王耀冲】on 【2016/12/29】 at 【22:03】.
 */
@Controller
@RequestMapping("/Group")
public class GroupController extends DataOperateBase {
    @Resource
    UserService userService;


    @RequestMapping("/deleteGroup")
    @ResponseBody
    public ResponseInfo deleteGroup(@RequestBody GroupEntity groupEntity){
        crudService.deleteByCondition(groupEntity);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,null);
    }



    @RequestMapping("/updateGroup")
    @ResponseBody
    public ResponseInfo updateGroup(@RequestBody GroupEntity groupEntity){
        if(groupEntity.getCreateDate()==null){
            groupEntity.setCreateDate(new Date().getTime());
        }
        if(groupEntity.getCreator()==null){
            groupEntity.setCreator(userService.getCurrentUser());
        }
        Serializable serializable = crudService.saveOrUpdateOne(groupEntity);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,serializable);
    }
    @RequestMapping("/getCurrentTeacherClassGroup")
    @ResponseBody
    public ResponseInfo getCurrentTeacherClassGroup(){
        PersonEntity currentUser = userService.getCurrentUser();
        GroupEntity groupEntity=new GroupEntity();
        groupEntity.setType(GroupType.CLASS);
        groupEntity.setCreator(currentUser);
        List<GroupEntity> listByCondition = crudService.getListByCondition(groupEntity);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,listByCondition);
    }
    @RequestMapping("/getClassGroupById")
    @ResponseBody
    public ResponseInfo getClassGroupById(@RequestParam String id){
        GroupEntity groupEntity = crudService.getCurrentSession().get(GroupEntity.class, id);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,groupEntity);
    }

}
