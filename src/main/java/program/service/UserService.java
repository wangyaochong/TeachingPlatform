package program.service;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import program.dao.GenericDao;
import program.entity.GroupEntity;
import program.entity.PersonEntity;
import program.entity.entityInterface.IEntity;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by【王耀冲】on 【2016/12/18】 at 【10:52】.
 */
@Service
public class UserService {
    @Resource(name = "GenericDao")
    GenericDao genericDao;
    public PersonEntity getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String number = authentication.getName();
//        AuthUserDetailsImpl principal = (AuthUserDetailsImpl) authentication.getPrincipal();
        PersonEntity personEntity=new PersonEntity();
        personEntity.setNumber(number);//以
        //由于用户是缓存在session中，所以如果当session有效时，用户权限发生更改，session缓存的用户信息不变，所以需要从数据库中查询
        return genericDao.simpleQueryOne(personEntity) ;
    }
    public void addCurrentUserToGroup(GroupEntity groupEntity){
        PersonEntity currentUser = getCurrentUser();
        List<GroupEntity> groupEntityList = currentUser.getGroupEntityList();
        groupEntityList.add(groupEntity);
        genericDao.getCurrentSession().save(currentUser);
    }
    public void addUserToGroup(PersonEntity personEntity,GroupEntity groupEntity){
        List<GroupEntity> groupEntityList = personEntity.getGroupEntityList();
        groupEntityList.add(groupEntity);
        genericDao.getCurrentSession().save(personEntity);
    }
    public List<PersonEntity> getUserFromGroup(GroupEntity groupEntity){
        Session currentSession = genericDao.getCurrentSession();
        String sql="select personentity.id from personentity_groupentity,personentity,groupentity where personentity.id=personentity_groupentity.personentity_id and groupentity.id=personentity_groupentity.groupEntityList_id and groupentity.id=:outId";
        SQLQuery sqlQuery = currentSession.createSQLQuery(sql);
        sqlQuery.setParameter("outId",groupEntity.getId());
        List list = sqlQuery.list();//先将该组的所有用户的Id找到
        if(list.size()==0){//如果没有用户，直接返回一个空的数组
            return new ArrayList<>();
        }
        String hql="from program.entity.PersonEntity person where person in :personList";
        List<PersonEntity> queryIds=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            PersonEntity personEntity=new PersonEntity();
            personEntity.setId((String) list.get(i));
            queryIds.add(personEntity);
        }
        Query query = currentSession.createQuery(hql);
        query.setParameterList("personList",queryIds);
        return query.list();
    }
    public void removePersonFromGroup(PersonEntity personEntity, GroupEntity groupEntity){
        List<GroupEntity> groupEntityList = personEntity.getGroupEntityList();
        groupEntityList.remove(groupEntity);
        personEntity.setGroupEntityList(groupEntityList);
        genericDao.getCurrentSession().update(personEntity);
    }
}
