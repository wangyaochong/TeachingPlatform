package program.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
    @OneToOne(fetch = FetchType.EAGER)
    ItemEntity itemEntity;

    Long deadlineDateTime;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
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

    public Long getDeadline() {
        return deadlineDateTime;
    }

    public void setDeadline(Long deadlineDateTime) {
        this.deadlineDateTime = deadlineDateTime;
    }

    public List<SolutionEntity> getSolutionEntities() {
        return solutionEntities;
    }

    public void setSolutionEntities(List<SolutionEntity> solutionEntities) {
        this.solutionEntities = solutionEntities;
    }
}
