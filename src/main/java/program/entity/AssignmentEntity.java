package program.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
public class AssignmentEntity {
    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;
    @OneToOne
    ItemEntity itemEntity;
    Date deadline;
    @OneToMany
    List<SolutionEntity> solutionEntities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<SolutionEntity> getSolutionEntities() {
        return solutionEntities;
    }

    public void setSolutionEntities(List<SolutionEntity> solutionEntities) {
        this.solutionEntities = solutionEntities;
    }
}
