package program.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * BaseDao的主要作用就是从配置文件中获取SessionFactory，
 * 返回一个已经打开的sesion，留给子类使用
 */
@Repository(value = "BaseDao")
public class BaseDao{
//    Session session;//给子类留一个可用的session
    @Autowired
    private SessionFactory sessionFactory;//sessionFactory只有自己可见
    public Session getSession() {
        return sessionFactory.getCurrentSession();
//        return sessionFactory.openSession();
    }
}
