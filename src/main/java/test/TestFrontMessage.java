package test;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import program.entity.ItemEntity;
import program.entity.ItemType;
import test.util.TestDaoUtil;

import java.util.Date;

public class TestFrontMessage {
    Session thisSession;
    @Before
    public void setUp() throws Exception {
        thisSession=TestDaoUtil.getSessionAndBeginTransaction();
    }

    @After
    public void tearDown() throws Exception {
        TestDaoUtil.endTransactionAndCloseSession();
    }

    @Test
    public void testAddFrontMessage(){
        ItemEntity itemEntity=new ItemEntity("课前预习","今天咱们讲解线性代数第一个，请同学们做好预习", ItemType.ANNOUNCEMENT.toString(),true,new Date(),null);
        thisSession.save(itemEntity);
    }
}
