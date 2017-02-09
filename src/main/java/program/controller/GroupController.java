package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import program.controller.base.DataOperateBase;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.entity.GroupEntity;
import program.entity.PersonEntity;
import program.entity.entityInterface.IEntity;
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
        Serializable serializable=null;
        if(groupEntity.getCreator()==null){
            groupEntity.setCreateDate(new Date().getTime());
            groupEntity.setCreator(userService.getCurrentUser());
            serializable = crudService.saveOrUpdateOne(groupEntity);

// 不能给班级设置创建者的原因是，班级包含创建者，同时创建者又包含对应的班级，这样在进行将类转换成字符串时就会形成死循环
//            groupEntity.setId((String) serializable);
//            userService.getCurrentUser().getGroupEntityList().add(groupEntity);
//            crudService.saveOrUpdateOne(userService.getCurrentUser());
        }else{
            serializable= crudService.saveOrUpdateOne(groupEntity);
        }
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
    //通过groupId获取group
    @RequestMapping("/getClassGroupById")
    @ResponseBody
    public ResponseInfo getClassGroupById(@RequestParam String id){
        GroupEntity groupEntity = crudService.getCurrentSession().get(GroupEntity.class, id);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,groupEntity);
    }

    //获取所有的班级，这个接口学生可以用到，因为学生需要获取所有的课程列表，然后选择是否加入
    @RequestMapping("/getAllClassGroup")
    @ResponseBody
    public ResponseInfo getAllClassGroup(){
        GroupEntity groupEntity=new GroupEntity();
        groupEntity.setType(GroupType.CLASS);
        List<GroupEntity> listByCondition = crudService.getListByCondition(groupEntity);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,listByCondition);
    }

    //传入groupId获取该group的所有同学
    @RequestMapping("/getStudentsFromGroup")
    @ResponseBody
    public ResponseInfo getClassGroupStudents(@RequestParam String groupId){
        List<PersonEntity> userFromGroup = userService.getUserFromGroup((GroupEntity) crudService.getOneById(GroupEntity.class, groupId));
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,userFromGroup);
    }
    @RequestMapping("/removePersonFromGroup")
    @ResponseBody
    public ResponseInfo removePersonFromGroup(@RequestParam String personId,@RequestParam String groupId){
        IEntity personEntity = crudService.getOneById(PersonEntity.class, personId);
        IEntity groupEntity = crudService.getOneById(GroupEntity.class, groupId);
        userService.removePersonFromGroup((PersonEntity) personEntity,(GroupEntity) groupEntity);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,null);
    }
}
