package test.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import program.dao.GenericDao;
import program.entity.PersonEntity;
import program.entity.PrivilegeEntity;

import java.io.IOException;
import java.util.Properties;

public class TestDaoUtil {
    static Session session;
    private static SessionFactory sessionFactory;
    public static GenericDao getGenericDao() {

        return genericDao;
    }
    static GenericDao genericDao;
    static {
        DriverManagerDataSource datasource = new DriverManagerDataSource("jdbc:mysql://127.0.0.1/teachingplatform?useUnicode=true&characterEncoding=UTF-8", "wangyaochong", "qwerqwer");
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(datasource);
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setAnnotatedClasses(PersonEntity.class, PrivilegeEntity.class);
        localSessionFactoryBean.setPackagesToScan("program/entity");
        try {
            localSessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sessionFactory = localSessionFactoryBean.getObject();
        ClassPathXmlApplicationContext ctx=
                new ClassPathXmlApplicationContext("classpath:hibernate.xml","classpath:springMVC.xml","classpath:springSecurity.xml");
        genericDao = (GenericDao) ctx.getBean("GenericDao");
    }
    public static Session getSessionAndBeginTransaction() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }
    public static void endTransactionAndCloseSession(){
        session.getTransaction().commit();
        session.close();
    }
}
