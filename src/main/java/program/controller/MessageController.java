package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import program.controller.base.DataOperateBase;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.entity.GroupEntity;
import program.entity.ItemEntity;
import program.entity.MessageEntity;
import program.entity.PersonEntity;
import program.entity.entityInterface.IEntity;
import program.service.MessageService;
import program.service.UserService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 【王耀冲】 on 【2017/1/19】 at 【19:41】.
 */
@Controller
@RequestMapping("/Message")
public class MessageController extends DataOperateBase{
    @Resource
    UserService userService;

    @Resource
    MessageService messageService;

    @RequestMapping("/getCurrentUserMessage")
    @ResponseBody
    public ResponseInfo getUnReadMessageCount(){
        List<MessageEntity> userMessage = messageService.getUserMessage(userService.getCurrentUser());
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,userMessage);
    }

    @RequestMapping("/sendGroupApplyMessage")
    @ResponseBody
    public ResponseInfo sendGroupApplyMessage(@RequestParam String groupId){
        IEntity oneById = crudService.getOneById(GroupEntity.class, groupId);
        messageService.sendGroupApplyMessage(userService.getCurrentUser(), (GroupEntity) oneById);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,"");
    }
    @RequestMapping("/updateMessage")
    @ResponseBody
    public ResponseInfo updateMessage(@RequestBody MessageEntity messageEntity){
        if(messageEntity.getItemEntity().getDescription().indexOf("同意")!=-1){//如果同意，就把用户加入到课程中
            userService.addUserToGroup(messageEntity.getItemEntity().getCreator(),messageEntity.getItemEntity().getClassGroup());
        }
        crudService.saveOrUpdateOne(messageEntity);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,"ok");
    }
}
