package program.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.dao.GenericDao;
import program.entity.ItemEntity;
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
    public Session getSession(){
        return genericDao.getSession();
    }
    public IEntity getOneById(Class<? extends IEntity> clazz, String id) {
        return genericDao.getSession().get(clazz, id);
    }

    //如是一个没有保存过的entity就保存，否则只要不是null，就用新的属性值一一替换旧值
    public Serializable saveOrUpdateOne(IEntity entity) {
        if (entity.getId() == null || entity.getId() == "") {
            Serializable save = genericDao.getSession().save(entity);
            return save;
        }
        IEntity update = genericDao.getSession().get(entity.getClass(), entity.getId());
        EntityUtil.updateEntity(update, entity);
        genericDao.getSession().update(update);
        return "";
    }

    public void deleteOneById(Class<?> clazz, String id) {
        try {
            IEntity o = (IEntity) clazz.newInstance();
            o.setId(id);
            genericDao.getSession().delete(o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> getListByCondition(T condition) {
        return genericDao.simpleQueryList(condition);
    }

    public <T> T getOnebyCondition(T condition) {
        return genericDao.simpleQueryOne(condition);
    }

    public <T> Integer deleteByCondition(T condition) {
        return genericDao.simpleDelete(condition);
    }
    public <T> List<T> getAll(Class<?> clazz){
        return genericDao.simpleGetAll(clazz);
    }
}
