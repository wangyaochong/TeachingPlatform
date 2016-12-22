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
    List<FileEntity> resourceEntities;
    @ManyToOne
    PersonEntity provider;//作业答题者

    public PersonEntity getProvider() {
        return provider;
    }
    public void setProvider(PersonEntity provider) {
        this.provider = provider;
    }
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

    public List<FileEntity> getResourceEntities() {
        return resourceEntities;
    }

    public void setResourceEntities(List<FileEntity> resourceEntities) {
        this.resourceEntities = resourceEntities;
    }

    public SolutionEntity(Integer mark, String comment, List<FileEntity> resourceEntities, PersonEntity provider) {
        this.mark = mark;
        this.comment = comment;
        this.resourceEntities = resourceEntities;
        this.provider = provider;
    }
}
