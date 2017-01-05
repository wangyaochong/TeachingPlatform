package program.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import program.dao.GenericDao;
import program.entity.entityInterface.IEntity;
import program.util.EntityUtil;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by【王耀冲】on 【2016/12/25】 at 【19:14】.
 */
@Service
public class CrudService {
    @Resource(name = "GenericDao")
    GenericDao genericDao;
    public Session getCurrentSession(){
        return genericDao.getCurrentSession();
    }
    public Session openSession(){
        return genericDao.openSession();
    }
    public IEntity getOneById(Class<? extends IEntity> clazz, String id) {
        return genericDao.getCurrentSession().get(clazz, id);
    }

    //如是一个没有保存过的entity就保存，否则只要不是null，就用新的属性值一一替换旧值
    public Serializable saveOrUpdateOne(IEntity entity) {
        if (entity.getId() == null || entity.getId() == "") {
            Serializable save = genericDao.getCurrentSession().save(entity);
            return save;
        }
        IEntity update = genericDao.getCurrentSession().get(entity.getClass(), entity.getId());
        EntityUtil.updateEntity(update, entity);

        genericDao.getCurrentSession().update(update);
        return "";
    }

    public void deleteOneById(Class<?> clazz, String id) {
        try {
            IEntity o = (IEntity) clazz.newInstance();
            o.setId(id);
            genericDao.getCurrentSession().delete(o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> getListByCondition(T condition) {
        return genericDao.simpleQueryList(condition);
    }

    public <T> T getOneByCondition(T condition) {
        return genericDao.simpleQueryOne(condition);
    }

    public <T> Integer deleteByCondition(T condition) {
        return genericDao.simpleDelete(condition);
    }
    public <T> List<T> getAll(Class<?> clazz){
        return genericDao.simpleGetAll(clazz);
    }
}
