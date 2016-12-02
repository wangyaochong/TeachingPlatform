package program.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * itemEntity代表教学平台的单项信息
 * 所有单项信息都有
 * 标题、描述、类型（文档|视频|公告|邮件）、附件id列表，可以为空、是否公开
 */

@Entity
public class ItemEntity {
    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//id
    String title;//标题
    String description;//描述
    String type;//类型
    Boolean isOpen;//是否公开
    @OneToMany(cascade = CascadeType.ALL)//当item删除后，对应文件也应该删除
    List<ResourceEntity> resources;//资源可以有一个、多个或者零个

    public ItemEntity(String title, String description, String type, Boolean isOpen, List<ResourceEntity> resources) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.isOpen = isOpen;
        this.resources = resources;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", isOpen=" + isOpen +
                ", resources=" + resources +
                '}';
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public ItemEntity() {
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public List<ResourceEntity> getResources() {
        return resources;
    }

    public void setResources(List<ResourceEntity> resources) {
        this.resources = resources;
    }
}
