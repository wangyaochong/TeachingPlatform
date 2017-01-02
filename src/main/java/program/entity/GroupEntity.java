package program.entity;

/**
 * Created by【王耀冲】on 【2016/12/22】 at 【23:09】.
 */

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import program.entity.entityInterface.IEntity;

import javax.persistence.*;
import java.util.List;

/**
 *分组信息，同一个分组内的单项信息可以由该分组内的所有人共享
 */
@Entity
public class GroupEntity implements IEntity{
    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//id
    String name;//分组的名字
    String description;//描述
    String type;//分组的类型分为人员分组和章节分组

    @ManyToOne(fetch = FetchType.EAGER)
    GroupEntity parentGroupEntity;//只能有一个父级分组

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)//使用list，同时一个类中有多个eagerfetch，那么需要使用子查询或者set
    List<GroupEntity> childGroupEntityList;//可以有多个子级分组

    @ManyToOne(fetch = FetchType.EAGER)
    PersonEntity creator;//分组创建人


    Long createDate;//创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GroupEntity getParentGroupEntity() {
        return parentGroupEntity;
    }

    public void setParentGroupEntity(GroupEntity parentGroupEntity) {
        this.parentGroupEntity = parentGroupEntity;
    }

    public List<GroupEntity> getChildGroupEntityList() {
        return childGroupEntityList;
    }

    public void setChildGroupEntityList(List<GroupEntity> childGroupEntityList) {
        this.childGroupEntityList = childGroupEntityList;
    }

    public PersonEntity getCreator() {
        return creator;
    }

    public void setCreator(PersonEntity creator) {
        this.creator = creator;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public GroupEntity() {
    }

    public GroupEntity(String name, String description, String type, GroupEntity parentGroupEntity, List<GroupEntity> childGroupEntityList, PersonEntity creator, Long createDate) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.parentGroupEntity = parentGroupEntity;
        this.childGroupEntityList = childGroupEntityList;
        this.creator = creator;
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
