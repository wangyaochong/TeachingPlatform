package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PrivileageEntity {
    //每一种权限对应一个模块的所有权限
    //当老师给一个用户授权时，如果该权限组合没有在数据库中存在，则新增一种一条记录
    //然后该用户指向该权限id
    @Id
    @GenericGenerator(name="generator",strategy = "uuid")
    @GeneratedValue(generator = "generator")
    String id;

    Boolean frontMessage;//对首页消息的权限
    Boolean document;//对文档的权限
    Boolean video;//对视频的权限
    Boolean assignment;//对作业的权限，包括对作业批改等
    Boolean personalInfomation;//对个人信息权限，包括修改用户密码等

    @OneToMany(cascade = CascadeType.ALL)
    Set<PersonEntity> personEntities;

    public PrivileageEntity() {
    }

    @Override
    public String toString() {
        return "PrivileageEntity{" +
                "id='" + id + '\'' +
                ", frontMessage=" + frontMessage +
                ", document=" + document +
                ", video=" + video +
                ", assignment=" + assignment +
                ", personalInfomation=" + personalInfomation +
                ", personEntities=" + personEntities +
                '}';
    }

    public PrivileageEntity(Boolean frontMessage, Boolean document, Boolean video, Boolean assignment, Boolean personalInfomation) {
        this.frontMessage = frontMessage;
        this.document = document;
        this.video = video;
        this.assignment = assignment;
        this.personalInfomation = personalInfomation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getFrontMessage() {
        return frontMessage;
    }

    public void setFrontMessage(Boolean frontMessage) {
        this.frontMessage = frontMessage;
    }

    public Boolean getDocument() {
        return document;
    }

    public void setDocument(Boolean document) {
        this.document = document;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Boolean getAssignment() {
        return assignment;
    }

    public void setAssignment(Boolean assignment) {
        this.assignment = assignment;
    }

    public Boolean getPersonalInfomation() {
        return personalInfomation;
    }

    public void setPersonalInfomation(Boolean personalInfomation) {
        this.personalInfomation = personalInfomation;
    }

    public Set<PersonEntity> getPersonEntities() {
        return personEntities;
    }

    public void setPersonEntities(Set<PersonEntity> personEntities) {
        this.personEntities = personEntities;
    }
}
