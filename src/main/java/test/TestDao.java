package test;

import dao.PersonDao;
import entity.PersonEntity;
import entity.PrivilegeEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import util.SqlStatementConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class TestDao {
    SessionFactory sessionFactory;
    Session session;
    @Before
    public void before() throws IOException {
        DriverManagerDataSource datasource = new DriverManagerDataSource("jdbc:mysql://127.0.0.1/teachingplatform?useUnicode=true&characterEncoding=UTF-8", "wangyaochong", "qwerqwer");
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(datasource);
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.format_sql","true");
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setAnnotatedClasses(PersonEntity.class, PrivilegeEntity.class);
        localSessionFactoryBean.setAnnotatedPackages("entity");
        //localSessionFactoryBean.setAnnotatedClasses(PersonEntity.class);
        localSessionFactoryBean.afterPropertiesSet();
        sessionFactory=localSessionFactoryBean.getObject();
        session=sessionFactory.openSession();
        session.beginTransaction();
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:springMVC.xml","classpath:bean.xml");
        SessionFactory bean = applicationContext.getBean(SessionFactory.class);
        System.out.println(bean);
    }
    @After
    public void after(){
        session.getTransaction().commit();
        this.session.close();
        this.sessionFactory.close();
    }
    @Test
    public void testAddPrivileage(){
        PrivilegeEntity privilegeEntity =new PrivilegeEntity(false,false,false,false,false);
        session.save(privilegeEntity);
    }
    @Test
    public void testSearchPerson(){
        String hql="from entity.PersonEntity as person where person.email=?";
        Query query=session.createQuery(hql);
        query.setString(0,"1162025261@qq");
        List list = query.list();
        System.out.println(list);

    }

    @Test
    public void deletePrivilege(){
        String hql="delete from entity.PrivilegeEntity as privilege where privilege.frontMessage=? and privilege.document=? and privilege.video=? ";
        Query query=session.createQuery(hql);
        query.setBoolean(0,false);
        query.setBoolean(1,false);
        query.setBoolean(2,false);
        int i = query.executeUpdate();
        System.out.println("changed count"+i);

    }
    @Test
    public void testDeletePerson(){
        PersonDao personDao=new PersonDao();
        personDao.simpleDelete(new PersonEntity(null,"哈哈哈",null,null,null,null,null));
    }
    @Test
    public void testAddPerson(){
        String hql = "from entity.PrivilegeEntity as privilege where " +
                "privilege.frontMessage=? and " +
                "privilege.document=? and " +
                "privilege.video=? and " +
                "privilege.assignment=? and " +
                "privilege.personalInfomation=?";
        Query query = session.createQuery(hql);
        query.setBoolean(0,false);
        query.setBoolean(1,false);
        query.setBoolean(2,false);
        query.setBoolean(3,false);
        query.setBoolean(4,false);
        List<PrivilegeEntity> list = query.list();
        System.out.println(list);
        PersonEntity personEntity=new PersonEntity(
                "201399252",
                "哦哦哦",
                "22",
                "男",
                "18840838242",
                "1162025261@qq",list.get(0));
        System.out.println(personEntity);

//        list.get(0).getPersonEntities().add(personEntity);
//        session.save(list.get(0));
        session.save(personEntity);

    }
    @Test
    public void testDao() throws IOException {
        String hql="from entity.PersonEntity,entity.PrivilegeEntity as person";
        Query query = session.createQuery(hql);
        List list = query.list();
        System.out.println(list);
    }
}
