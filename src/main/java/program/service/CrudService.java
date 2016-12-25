package program.service;

import org.springframework.stereotype.Service;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.dao.GenericDao;
import program.entity.ItemEntity;
import program.entity.entityInterface.IEntity;
import program.util.EntityUtil;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by【王耀冲】on 【2016/12/25】 at 【19:14】.
 */
@Service
public class CrudService {
    @Resource(name = "GenericDao")
    GenericDao genericDao;



    public IEntity getOne(Class<? extends IEntity> clazz, String id) {
        return genericDao.getSession().get(clazz, id);
    }
    //update包含save和update
    public Serializable saveOrUpdateOne(IEntity entity) {
        if (entity.getId() == null||entity.getId()=="") {//如果是一个没有保存过的entity，则保存
            Serializable save = genericDao.getSession().save(entity);
            return save;
        }
        IEntity update = genericDao.getSession().get(entity.getClass(), entity.getId());
        EntityUtil.updateEntity(update, entity);
        genericDao.getSession().update(update);
        return "";
    }

    public void deleteOne( Class<?> clazz,String id) {
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
}
