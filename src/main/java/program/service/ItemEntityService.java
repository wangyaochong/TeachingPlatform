package program.service;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import program.entity.ItemEntity;
import program.entity.PersonEntity;
import program.entity.type.ItemType;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by【王耀冲】on 【2017/1/8】 at 【11:52】.
 */
@Service
public class ItemEntityService {
    @Resource
    CrudService crudService;
    @Resource
    UserService userService;
    //通过类型获取当前用户的所有的资源，只要是资源group属于当前用户的group之一，用户就可以获取到
    public List<ItemEntity> getCurrentUserItemEntityByType(ItemType itemType){

        Session currentSession = crudService.getCurrentSession();
        PersonEntity currentUser = userService.getCurrentUser();


        Criteria criteriaCreator = currentSession.createCriteria(ItemEntity.class);
        criteriaCreator.add(Restrictions.eq("creator",currentUser));
        criteriaCreator.add(Restrictions.eq("type",itemType.toString()));//创建者肯定可以看到自己创建的资源
        List listByCreator = criteriaCreator.list();

        if(currentUser.getGroupEntityList().size()!=0){//如果当前用户在某个班级中
            String hql="from program.entity.ItemEntity item where item.classGroup in :classList and item.type=:type";
            Query query = currentSession.createQuery(hql);
            query.setParameterList("classList",currentUser.getGroupEntityList());
            query.setParameter("type",itemType.toString());
            List listInGroup=  query.list();
            listByCreator.addAll(listInGroup);
        }




        return listByCreator;
    }
}
