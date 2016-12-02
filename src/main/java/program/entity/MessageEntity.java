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

    @OneToOne(optional = false)
    PersonEntity sender;//发送者
    @OneToOne(optional = false)
    PersonEntity receiver;//接受者
    @OneToOne(optional = false)
    ItemEntity itemEntity;//单项信息
    Date sendingDate;//发送时间
    Boolean hasRead;//是否已经阅读

    public MessageEntity(PersonEntity sender, PersonEntity receiver, ItemEntity itemEntity, Date sendingDate, Boolean hasRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.itemEntity = itemEntity;
        this.sendingDate = sendingDate;
        this.hasRead = hasRead;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id='" + id + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", itemEntity=" + itemEntity +
                ", sendingDate=" + sendingDate +
                ", hasRead=" + hasRead +
                '}';
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

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public Boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }
}
