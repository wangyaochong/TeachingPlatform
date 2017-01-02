package program.controller.util;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import program.entity.PersonEntity;
import program.entity.PrivilegeEntity;
import program.entity.type.PrivilegeType;
import program.service.CrudService;
import program.service.PrivilegeService;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

/**
 * Created by 【王耀冲】 on 【2016/12/31】 at 【14:52】.
 */
@Component
public class AppInitializer implements ServletContextAware {
//这个地方如果一直使用crudService的getCurrentSession，由于事务管理器会自动关闭session，
// 会导致异常，所以使用openSession，然后自己关闭
    @Override
    public void setServletContext(ServletContext servletContext) {
        if (privilegeService.getPrivByGroupId(null).size() == 0) {//如果没有根权限，则创建
            Session session = crudService.openSession();
            session.beginTransaction();
            session.save(new PrivilegeEntity(PrivilegeType.ASSIGNMENT, null));//添加一权限
            session.save(new PrivilegeEntity(PrivilegeType.DOCUMENT, null));//添加一个super权限
            session.save(new PrivilegeEntity(PrivilegeType.FRONT_MESSAGE, null));//添加一个super权限
            session.save(new PrivilegeEntity(PrivilegeType.GROUP_EDIT, null));//添加一个super权限
            session.save(new PrivilegeEntity(PrivilegeType.SUPER, null));//添加一个super权限
            session.save(new PrivilegeEntity(PrivilegeType.USER_MANAGEMENT, null));//添加一个super权限
            session.save(new PrivilegeEntity(PrivilegeType.VIDEO, null));//添加一个super权限
            session.getTransaction().commit();
            session.beginTransaction();
            PersonEntity personEntity = new PersonEntity();
            personEntity.setName("super");
            personEntity.setNumber("super");
            personEntity.setPassword("123");
            personEntity.setPrivilegeEntityList(privilegeService.getPrivByGroupId(null));
            session.save(personEntity);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Resource
    PrivilegeService privilegeService;
    @Resource
    CrudService  crudService;
}
