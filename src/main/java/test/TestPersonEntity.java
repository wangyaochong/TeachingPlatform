package test;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import program.dao.GenericDao;
import program.entity.PersonEntity;
import program.entity.PrivilegeEntity;
import program.util.SingleClassSqlConstructor;
import test.util.TestDaoUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by【王耀冲】on 【2016/12/11】 at 【13:31】.
 */
public class TestPersonEntity {
    Session session;
    @Before
    public void setUp() throws Exception {
        session= TestDaoUtil.getSessionAndBeginTransaction();

    }
    @After
    public void tearDown() throws Exception {
        TestDaoUtil.endTransactionAndCloseSession();
    }

    @Test
    public  void testAddPerson(){
        List<PrivilegeEntity> privilegeEntities = TestDaoUtil.getGenericDao().simpleQueryList(new PrivilegeEntity());
        PersonEntity personEntity=new PersonEntity("201392254","qwerqwer","王耀冲",new Date().getTime(),"男","18840838242","1162025261@qq.com",privilegeEntities,null);
        session.save(personEntity);
    }
}
