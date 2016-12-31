package program.entity.wrapper;

import program.entity.GroupEntity;
import program.entity.PrivilegeEntity;
import program.entity.type.PrivilegeType;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by 【王耀冲】 on 【2016/12/31】 at 【14:56】.
 */
public class PrivilegeWrapper {
    PrivilegeEntity privilegeEntity;
    String fullName;//fullName是权限的全名，可以根据locale信息进行国际化

    public String getId() {
        return privilegeEntity.getId();
    }

    public PrivilegeWrapper(PrivilegeEntity privilegeEntity) {
        this.privilegeEntity = privilegeEntity;
    }

    public PrivilegeWrapper() {
    }

    public String getFullName() {
        GroupEntity groupEntity = this.privilegeEntity.getGroupEntity();
        String group = "";
        if (groupEntity != null) {
            group = groupEntity.getName()+":";
        }else{
            group="全局：";
        }
        String privilegeFullName = "";
        switch (privilegeEntity.getType()) {
            case PrivilegeType.ASSIGNMENT:
                privilegeFullName = "作业管理";
                break;
            case PrivilegeType.DOCUMENT:
                privilegeFullName = "文档管理";
                break;
            case PrivilegeType.FRONT_MESSAGE:
                privilegeFullName = "首页管理";
                break;
            case PrivilegeType.GROUP_EDIT:
                privilegeFullName = "班级管理";
                break;
            case PrivilegeType.SUPER:
                privilegeFullName = "超级权限";
                break;
            case PrivilegeType.USER_MANAGEMENT:
                privilegeFullName = "用户管理";
                break;
            case PrivilegeType.VIDEO:
                privilegeFullName = "视频管理";
                break;

        }
        return group+privilegeFullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(String id) {
        privilegeEntity.setId(id);
    }

    public String getType() {
        return this.privilegeEntity.getType();
    }

    public void setType(String type) {
        this.privilegeEntity.setType(type);
    }

    public GroupEntity getGroupEntity() {
        return this.privilegeEntity.getGroupEntity();
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.privilegeEntity.setGroupEntity(groupEntity);
    }
}
