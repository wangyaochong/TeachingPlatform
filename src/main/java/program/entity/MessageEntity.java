package program.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 这是互相通信之间的消息类，可以有附件
 */
@Entity
public class MessageEntity {
    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;
    @ManyToOne(optional = false)
    PersonEntity sender;//发送者
    @OneToMany
    PersonEntity receiver;//接受者
    @OneToOne(optional = false)
    ItemEntity itemEntity;//单项信息
    Date sendingDateTime;//发送时间
    Boolean hasRead;//是否已经阅读

    public MessageEntity(PersonEntity sender, PersonEntity receiver, ItemEntity itemEntity, Date sendingDateTime, Boolean hasRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.itemEntity = itemEntity;
        this.sendingDateTime = sendingDateTime;
        this.hasRead = hasRead;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonEntity getSender() {
        return sender;
    }

    public void setSender(PersonEntity sender) {
        this.sender = sender;
    }

    public PersonEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(PersonEntity receiver) {
        this.receiver = receiver;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public Date getSendingDateTime() {
        return sendingDateTime;
    }

    public void setSendingDateTime(Date sendingDateTime) {
        this.sendingDateTime = sendingDateTime;
    }

    public Boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }
}
