package program.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * privilegeEntity是所有的权限entity，用户可以自定义自己的权限
 * 目前不考虑添加拥有不同权限的角色，用户自身直接与权限绑定
 * 除了id，权限的各个字段的各种组合唯一
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"frontMessage","document","video","assignment","personalInfomation"})})
//@UniqueConstraint()注解，可以让多个列组成的联合属性唯一
public class PrivilegeEntity {
    //每一种权限对应一个模块的所有权限
    //当老师给一个用户授权时，如果该权限组合没有在数据库中存在，则新增一种一条记录
    //然后该用户指向该权限id
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")//id的生成策略
    @GeneratedValue(generator = "generator")//指定使用哪一个主键生成器

    String id;
    Boolean frontMessage;//对首页消息的权限
    Boolean document;//对文档的权限
    Boolean video;//对视频的权限
    Boolean assignment;//对作业的权限，包括对作业批改等
    Boolean personalInfomation;//对个人信息权限，包括修改用户密码，用户权限等

    //是否具有super权限，仅super权限可以创建用户，同时不受分组的限制
    //也仅有super权限可以创建具有super权限的用户
    Boolean isSuper;
    @ManyToOne(fetch = FetchType.EAGER)
    GroupEntity groupEntity;//权限对应的组别，每个人的权限仅限自己组内





    public Boolean getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Boolean isSuper) {
        isSuper = isSuper;
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }




    public PrivilegeEntity() {
    }


    public PrivilegeEntity(Boolean frontMessage, Boolean document, Boolean video, Boolean assignment, Boolean personalInfomation, Boolean isSuper, GroupEntity groupEntity) {
        this.frontMessage = frontMessage;
        this.document = document;
        this.video = video;
        this.assignment = assignment;
        this.personalInfomation = personalInfomation;
        this.isSuper = isSuper;
        this.groupEntity = groupEntity;
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
}
