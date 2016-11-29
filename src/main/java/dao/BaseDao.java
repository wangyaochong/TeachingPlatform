package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseDao{
    Session session;//给子类留一个可用的session
    private SessionFactory sessionFactory;//sessionFactory只有自己可见

    public BaseDao() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:bean.xml");
        //从配置文件中加载sessionFactory
        sessionFactory = context.getBean(SessionFactory.class);
        session = sessionFactory.openSession();
    }
    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
