package program.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Entity
public class SolutionEntity {
    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;
    Integer mark;//得分
    String comment;//评语
    @OneToMany(fetch = FetchType.EAGER)
    List<ResourceEntity> resourceEntities;

    public SolutionEntity() {
    }

    @Override
    public String toString() {
        return "SolutionEntity{" +
                "id='" + id + '\'' +
                ", mark=" + mark +
                ", comment='" + comment + '\'' +
                ", resourceEntities=" + resourceEntities +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<ResourceEntity> getResourceEntities() {
        return resourceEntities;
    }

    public void setResourceEntities(List<ResourceEntity> resourceEntities) {
        this.resourceEntities = resourceEntities;
    }

    public SolutionEntity(Integer mark, String comment, List<ResourceEntity> resourceEntities) {
        this.mark = mark;
        this.comment = comment;
        this.resourceEntities = resourceEntities;
    }
}
