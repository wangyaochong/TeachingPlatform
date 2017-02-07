package program.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import program.entity.entityInterface.IEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 这是互相通信之间的消息类，可以有附件
 */
@Entity
public class MessageEntity implements IEntity{
    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;
    @ManyToOne(fetch = FetchType.EAGER)
    PersonEntity sender;//发送者

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    List<PersonEntity> receiver;//接受者

    @OneToOne(optional = false,fetch = FetchType.EAGER)
    ItemEntity itemEntity;//单项信息

    Long sendingDateTime;//发送时间
    Boolean hasRead;//是否已经阅读

    public MessageEntity() {
    }

    public MessageEntity(PersonEntity sender, List<PersonEntity> receiver, ItemEntity itemEntity, Long sendingDateTime, Boolean hasRead) {
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

    public List<PersonEntity> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<PersonEntity> receiver) {
        this.receiver = receiver;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public Long getSendingDateTime() {
        return sendingDateTime;
    }

    public void setSendingDateTime(Long sendingDateTime) {
        this.sendingDateTime = sendingDateTime;
    }

    public Boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }
}
