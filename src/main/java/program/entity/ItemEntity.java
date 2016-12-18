package program.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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

    public Boolean getIsOpen() {
        return isOpen;
    }
    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    Boolean isOpen;//是否公开
    Long createDate;//生成的时间
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)//当item删除后，对应文件也应该删除
    List<FileEntity> resources;//资源可以有一个、多个或者零个

    public ItemEntity(String title, String description, String type, Boolean isOpen, Long createDate, List<FileEntity> resources) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.isOpen = isOpen;
        this.createDate = createDate;
        this.resources = resources;
    }
    public Long getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
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
                ", createDate=" + createDate +
                ", resources=" + resources +
                '}';
    }



    public ItemEntity() {
    }



    public List<FileEntity> getResources() {
        return resources;
    }

    public void setResources(List<FileEntity> resources) {
        this.resources = resources;
    }
}
