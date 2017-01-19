package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import program.controller.base.DataOperateBase;
import program.controller.util.ResponseInfo;
import program.entity.GroupEntity;
import program.entity.ItemEntity;
import program.entity.MessageEntity;
import program.entity.PersonEntity;
import program.entity.entityInterface.IEntity;
import program.service.UserService;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by 【王耀冲】 on 【2017/1/19】 at 【19:41】.
 */
@Controller
@RequestMapping("/Message")
public class MessageController extends DataOperateBase{
    @Resource
    UserService userService;
    @RequestMapping("/getUnReadMessageCount")
    @ResponseBody
    public ResponseInfo getUnReadMessageCount(String personId){
        return null;
    }
    @RequestMapping("/sendGroupApplyMessage")
    @ResponseBody
    public ResponseInfo sendGroupApplyMessage(String groupId){
        IEntity oneById = crudService.getOneById(GroupEntity.class, groupId);
        PersonEntity creator = ((GroupEntity) oneById).getCreator();
        ItemEntity itemEntity=new ItemEntity();
//        itemEntity
//        MessageEntity messageEntity=new MessageEntity(userService.getCurrentUser(), Arrays.asList(creator),)
        return null;
    }
}
