package test;

import entity.PersonEntity;
import entity.PrivileageEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

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
        localSessionFactoryBean.setAnnotatedClasses(PersonEntity.class,PrivileageEntity.class);
        localSessionFactoryBean.setAnnotatedPackages("entity");
        //localSessionFactoryBean.setAnnotatedClasses(PersonEntity.class);
        localSessionFactoryBean.afterPropertiesSet();
        sessionFactory=localSessionFactoryBean.getObject();
        session=sessionFactory.openSession();
        session.beginTransaction();
    }
    @After
    public void after(){
        session.getTransaction().commit();
        this.session.close();
        this.sessionFactory.close();
    }
    @Test
    public void testAddPrivileage(){
        PrivileageEntity privileageEntity=new PrivileageEntity(false,false,false,false,false);
        session.save(privileageEntity);
    }
    @Test
    public void testSearchPerson(){

    }
    @Test
    public void testAddPerson(){
        String hql = "from entity.PrivileageEntity as privileage where " +
                "privileage.frontMessage=? and " +
                "privileage.document=? and " +
                "privileage.video=? and " +
                "privileage.assignment=? and " +
                "privileage.personalInfomation=?";
        Query query = session.createQuery(hql);
        query.setBoolean(0,false);
        query.setBoolean(1,false);
        query.setBoolean(2,false);
        query.setBoolean(3,false);
        query.setBoolean(4,false);
        List<PrivileageEntity> list = query.list();
        System.out.println(list);
        PersonEntity personEntity=new PersonEntity(
                "201399252",
                "王耀冲",
                "22",
                "男",
                "18840838242",
                "1162025261@qq");
        System.out.println(personEntity);
//        list.get(0).getPersonEntities().add(personEntity);
//        session.save(list.get(0));
        session.save(personEntity);

    }
    @Test
    public void testDao() throws IOException {
        String hql="from entity.PersonEntity,entity.PrivileageEntity as person";
        Query query = session.createQuery(hql);
        List list = query.list();
        System.out.println(list);
    }
}
