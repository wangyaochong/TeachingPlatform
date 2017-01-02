package test;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import program.entity.GroupEntity;
import program.entity.ItemEntity;
import program.entity.type.ItemType;
import test.util.TestDaoUtil;

import java.util.Date;
import java.util.List;

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
        Criteria criteria = this.thisSession.createCriteria(GroupEntity.class);
        List<GroupEntity> list = criteria.list();
        for(int i=0;i<5;i++){
            ItemEntity itemEntity=new ItemEntity("通知","本周老师要出差，需要请假一天，缺下的课程周六找时间补上", ItemType.ANNOUNCEMENT.toString(),true,new Date().getTime(),null,null,
                    list.get(0),null);
            thisSession.save(itemEntity);
        }
    }
}
