package test;

import org.springframework.transaction.annotation.Transactional;
import program.dao.BaseDao;
import program.dao.GenericDao;
import program.dao.PersonDao;
import program.entity.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import program.util.PageBean;
import program.util.PageListWithSingleBean;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class TestDao {
    @Test
    public void testAddResource() {

//        List<PersonEntity> objects = genericDao.simpleGetAll(PersonEntity.class);
//
//        Session session = baseDao.getSession();
//        Session session1 = genericDao.getSession();
//        System.out.println(objects);
//        baseDao.getSession().save(new ResourceEntity(objects.get(0),"sdfsdff",new Date()));
        List<PersonEntity> list = session.createCriteria(PersonEntity.class).list();
        System.out.println(list);
        session.save(new ResourceEntity(list.get(0), "sdsdfsdfsaaadfsdffsdff", new Date()));

    }

    @Test
    public void testAddItem() {
        List<ResourceEntity> list = session.createCriteria(ResourceEntity.class).list();
        System.out.println(list);
//        session.save(new ItemEntity("课前预习", "同学们，今天咱们讲解有关高斯定理部分的内容，难度较大，请同学们提前复习", ItemType.ANNOUNCEMENT.toString(), true, new Date(),null));
//        session.save(new ItemEntity("课后作业", "本次作业的内容已经放到文档中心，请同学们下载查阅，并按时上交作业", ItemType.ANNOUNCEMENT.toString(), true, new Date(),null));

    }

    @Test
    public void getItem() {
        List<ItemEntity> list = session.createCriteria(ItemEntity.class).list();
        System.out.println(list.get(0).getResources());
    }

    SessionFactory sessionFactory;
    Session session;
    ApplicationContext applicationContext;
    GenericDao genericDao;
    BaseDao baseDao;

    @Test
    public void testPageList(){
        PersonEntity condition=new PersonEntity();
//        condition.setAge(24);
//        condition.setEmail("");
        condition.setPhoneNumber("18840838242");
        PageListWithSingleBean<PersonEntity> pageListWithSingleBean=new PageListWithSingleBean<PersonEntity>(session,condition,1,5,"ag",true);
        PageBean<PersonEntity> pageBean = pageListWithSingleBean.getPageBean();
        System.out.println("总共："+pageBean.getPageTotalCount()+"页");
        for(PersonEntity p:pageBean.getPageList()){
            System.out.println(p);
        }

    }

    @Before
    public void before() throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("hibernate.xml", "springMVC.xml");
        genericDao = (GenericDao) applicationContext.getBean("GenericDao");
        baseDao = (BaseDao) applicationContext.getBean("BaseDao");
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
        localSessionFactoryBean.setAnnotatedPackages("program/entity");
        //localSessionFactoryBean.setAnnotatedClasses(PersonEntity.class);
        localSessionFactoryBean.afterPropertiesSet();
        sessionFactory = localSessionFactoryBean.getObject();
        session = sessionFactory.openSession();
        session.beginTransaction();
        applicationContext = new ClassPathXmlApplicationContext("classpath:springMVC.xml", "classpath:hibernate.xml");
        SessionFactory bean = applicationContext.getBean(SessionFactory.class);
        System.out.println(bean);

    }

    @After
    public void after() {
        session.getTransaction().commit();
        this.session.close();
        this.sessionFactory.close();
    }

    @Test
    public void testAddPrivileage() {
        PrivilegeEntity privilegeEntity = new PrivilegeEntity(false, false, false, false, false);
        session.save(privilegeEntity);
    }

    @Test
    public void testSearchPerson() {
        String hql = "from program.entity.PersonEntity as person where person.email=?";
        Query query = session.createQuery(hql);
        query.setString(0, "1162025261@qq");
        List list = query.list();
        System.out.println(list);

    }

    @Test
    public void deletePrivilege() {
        String hql = "delete from program.entity.PrivilegeEntity as privilege where privilege.frontMessage=? and privilege.document=? and privilege.video=? ";
        Query query = session.createQuery(hql);
        query.setBoolean(0, false);
        query.setBoolean(1, false);
        query.setBoolean(2, false);
        int i = query.executeUpdate();
        System.out.println("changed count" + i);

    }

    @Test
    public void testDeletePersonHql() {
        session = new BaseDao().getSession();
        String hql = "delete from program.entity.PersonEntity where name='哈哈哈'";
        Query query = session.createQuery(hql);
//        query.setParameter(1;
        int i = query.executeUpdate();
        System.out.println("count:" + i);
    }

    @Test
    public void testDeletePerson() throws InterruptedException {
        PersonDao personDao = new PersonDao();
        PersonEntity condition=new PersonEntity(null,null,"哈哈哈",null,null,null,null,null);
        personDao.simpleDelete(condition);
//        String hql="delete from PersonEntity where name=?";
//        Query query=session.createQuery(hql);
//        query.setString(0,"哈哈哈");
//        int i = query.executeUpdate();
//        System.out.println("count:"+i);
    }

    @Test
    @Transactional
    public void testAddPerson() {
        PersonEntity test=new PersonEntity();
        String hql = "from program.entity.PrivilegeEntity as privilege where " +
                "privilege.frontMessage=? and " +
                "privilege.document=? and " +
                "privilege.video=? and " +
                "privilege.assignment=? and " +
                "privilege.personalInfomation=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, false);
        query.setParameter(1, false);
        query.setParameter(2, false);
        query.setParameter(3, false);
        query.setParameter(4, false);
//        query.setBoolean(0,false);
//        query.setBoolean(1,false);
//        query.setBoolean(2,false);
//        query.setBoolean(3,false);
//        query.setBoolean(4,false);
        List<PrivilegeEntity> list = query.list();
        System.out.println(list);
        PersonEntity personEntity = new PersonEntity(
                "201399252",
                "qwerqwer",
                "哈哈哈",
                12,
                "男",
                "18840838242",
                "1162025261@qq", list.get(0));
        System.out.println(personEntity);

//        list.get(0).getPersonEntities().add(personEntity);
//        session.save(list.get(0));
        session.save(personEntity);

    }

    @Test
    public void testDao() throws IOException {
//        String hql="from entity.PersonEntity,entity.PrivilegeEntity as person";
//        Query query = session.createQuery(hql);
//        List list = query.list();
//        System.out.println(list);
    }
}
