package test;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import program.entity.GroupEntity;
import program.entity.PrivilegeEntity;
import program.entity.type.PrivilegeType;
import test.util.DaoBaseUtil;
import test.util.DisplayUtil;

import java.util.List;

/**
 * Created by 【王耀冲】 on 【2016/12/23】 at 【10:32】.
 */
public class TestPrivilegeEntity extends DaoBaseUtil{
    @Test
    public void testAddPrivWithGroup(){
        this.beforeMethod();
        GroupEntity groupEntity = this.genericDao.simpleQueryOne(new GroupEntity());
        PrivilegeEntity privilegeEntity=new PrivilegeEntity();
        privilegeEntity.setType(PrivilegeType.USER_MANAGEMENT);
        privilegeEntity.setGroupEntity(groupEntity);
        this.session.save(privilegeEntity);

        this.afterMethod();
    }
    @Test
    public void testAdd(){
        this.beforeMethod();
        //添加7种根权限，也就是不受分组控制的权限
//        Criteria criteria = this.session.createCriteria(PrivilegeEntity.class);
        PrivilegeEntity privilegeEntity = new PrivilegeEntity();
        this.genericDao.simpleQueryList(privilegeEntity);
        this.session.save(new PrivilegeEntity(PrivilegeType.ASSIGNMENT,null));//添加一权限
        this.session.save(new PrivilegeEntity(PrivilegeType.DOCUMENT,null));//添加一个super权限
        this.session.save(new PrivilegeEntity(PrivilegeType.FRONT_MESSAGE,null));//添加一个super权限
        this.session.save(new PrivilegeEntity(PrivilegeType.GROUP_EDIT,null));//添加一个super权限
        this.session.save(new PrivilegeEntity(PrivilegeType.SUPER,null));//添加一个super权限
        this.session.save(new PrivilegeEntity(PrivilegeType.USER_MANAGEMENT,null));//添加一个super权限
        this.session.save(new PrivilegeEntity(PrivilegeType.VIDEO,null));//添加一个super权限
        this.afterMethod();
    }
    @Test
    public void queryRootPriv(){
        this.beforeMethod();
        GroupEntity groupEntity = this.genericDao.simpleQueryOne(new GroupEntity());
        PrivilegeEntity privilegeEntity = new PrivilegeEntity();
//        privilegeEntity.setGroupEntity(groupEntity);
//        privilegeEntity.setType(PrivilegeType.USER_MANAGEMENT);
        List<PrivilegeEntity> privilegeEntities = this.genericDao.simpleQueryList(privilegeEntity);
        for(int i=0;i<privilegeEntities.size();i++){
            System.out.println(privilegeEntities.get(i));
        }
        this.afterMethod();
    }
    @Test
    public void queryRootPrivWithCriteria(){
        this.beforeMethod();
        Criteria criteria = this.session.createCriteria(PrivilegeEntity.class);
        criteria.add(Restrictions.isNull("groupEntity"));
        List list = criteria.list();
        DisplayUtil.display(list);
        this.afterMethod();
    }
}
