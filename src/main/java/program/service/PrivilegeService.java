package program.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import program.entity.GroupEntity;
import program.entity.PrivilegeEntity;
import program.entity.entityInterface.IEntity;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 【王耀冲】 on 【2016/12/31】 at 【14:51】.
 */
@Service
public class PrivilegeService {
    @Resource
    CrudService crudService;
    List<PrivilegeEntity> getRootPrivilegeList(){//选出不处于任何一个分组的privilege
        Criteria criteria = crudService.getSession().createCriteria(PrivilegeEntity.class);
        criteria.add(Restrictions.isNull("groupEntity"));
        return  criteria.list();
    }
    public List<PrivilegeEntity> getPrivByGroupId(String groupId){
        if(groupId==null||groupId.equals("")){
            return getRootPrivilegeList();//如果groupId是空，则返回根权限
        }
        IEntity oneById = crudService.getOneById(GroupEntity.class, groupId);
        PrivilegeEntity privilegeEntity=new PrivilegeEntity();
        privilegeEntity.setGroupEntity((GroupEntity) oneById);
        return crudService.getListByCondition(privilegeEntity);
    }
}
