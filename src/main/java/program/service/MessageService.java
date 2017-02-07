package program.service;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import program.entity.GroupEntity;
import program.entity.ItemEntity;
import program.entity.MessageEntity;
import program.entity.PersonEntity;
import program.entity.entityInterface.IEntity;
import program.entity.type.ItemType;
import program.entity.type.MessageType;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by 【王耀冲】 on 【2017/1/19】 at 【20:01】.
 */
@Service
public class MessageService {

    @Resource
    CrudService crudService;

    //这里的申请加入班级的信息是不变的，所以写在Service里面
    public void sendGroupApplyMessage(PersonEntity applicant, GroupEntity groupEntity) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setTitle(MessageType.APPLY_JOIN_CLASS);
        itemEntity.setType(ItemType.EMAIL.toString());
        itemEntity.setClassGroup(groupEntity);//设置要加入的班级
        itemEntity.setCreator(applicant);
        itemEntity.setCreateDate(new Date().getTime());
        String applyMessage="【" +applicant.getName()+"】申请加入课程【"+groupEntity.getName()+"】";
        itemEntity.setDescription(applyMessage);
        Serializable save = crudService.getCurrentSession().save(itemEntity);
        itemEntity.setId((String) save);
        MessageEntity messageEntity = new MessageEntity();
//        messageEntity.setSender(null);//发送人为空，则说明是系统发送的消息
        messageEntity.setSendingDateTime(new Date().getTime());
        messageEntity.setReceiver(Arrays.asList(groupEntity.getCreator()));
        messageEntity.setHasRead(false);//邮件一创建肯定是未读的
        messageEntity.setItemEntity(itemEntity);//设置邮件体
        crudService.saveOrUpdateOne(messageEntity);
    }

    public void sendGroupReplyMessage(GroupEntity groupEntity, PersonEntity receiver, boolean isApproved) {
        String yesString = "您申请加入班级【" + groupEntity.getName() + "】的请求已通过。";
        String noString = "对不起，您申请加入班级【" + groupEntity.getName() + "】的请求被拒绝了。";
        String respondString = (isApproved==true?yesString:noString);
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setTitle(MessageType.APPLY_JOIN_CLASS_REPLY);
        itemEntity.setType(ItemType.EMAIL.toString());
        itemEntity.setDescription(respondString);
        itemEntity.setCreator(groupEntity.getCreator());
        Serializable save = crudService.getCurrentSession().save(itemEntity);
        itemEntity.setId((String) save);
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSender(null);//发送人为空，则说明是系统发送的消息
        messageEntity.setSendingDateTime(new Date().getTime());
        messageEntity.setReceiver(Arrays.asList(receiver));
        messageEntity.setHasRead(false);//邮件一创建肯定是未读的
        messageEntity.setItemEntity(itemEntity);//设置邮件体
        crudService.saveOrUpdateOne(messageEntity);
    }

    //获取一个用户下的所有信息
    public List<MessageEntity> getUserMessage(PersonEntity personEntity) {
        String sql = "select messageentity.id from messageentity,personentity,messageentity_personentity where messageentity.id=messageentity_personentity.MessageEntity_id and messageentity_personentity.receiver_id = personentity.id and personentity.id=:personId ";
        SQLQuery sqlQuery = crudService.getCurrentSession().createSQLQuery(sql);
        sqlQuery.setParameter("personId", personEntity.getId());
        List<String> list = sqlQuery.list();
        List<MessageEntity> messageEntityList = new ArrayList<>();
        for (String s : list) {
            IEntity oneById = crudService.getOneById(MessageEntity.class, s);
            messageEntityList.add((MessageEntity) oneById);
        }
        return messageEntityList;
    }
}
