package program.entity;

import org.hibernate.annotations.GenericGenerator;
import program.entity.entityInterface.IEntity;

import javax.persistence.*;

/**
 * privilegeEntity是所有的权限entity，用户可以自定义自己的权限
 * 目前不考虑添加拥有不同权限的角色，用户自身直接与权限绑定
 * 除了id，权限的各个字段的各种组合唯一
 */
@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"frontMessage","document","video","assignment","personalInformation"})})
//@UniqueConstraint()注解，可以让多个列组成的联合属性唯一
public class PrivilegeEntity implements IEntity{
    //每一种权限对应一个模块的所有权限
    //当老师给一个用户授权时，如果该权限组合没有在数据库中存在，则新增一种一条记录
    //然后该用户指向该权限id
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")//id的生成策略
    @GeneratedValue(generator = "generator")//指定使用哪一个主键生成器
    String id;
    String type;//类型
    @ManyToOne(fetch = FetchType.EAGER)
    GroupEntity groupEntity;//权限对应的组别，每个人的权限仅限自己组内

    public PrivilegeEntity() {
    }

    public PrivilegeEntity(String type, GroupEntity groupEntity) {
        this.type = type;
        this.groupEntity = groupEntity;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
    }
}
