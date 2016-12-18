package test.util;

import org.hibernate.Session;
import program.dao.GenericDao;
import test.util.TestDaoUtil;

/**
 * Created by【王耀冲】on 【2016/12/17】 at 【21:20】.
 */
public class DaoBaseUtil {
    protected GenericDao genericDao;
    protected Session session;
    public void beforeMethod() {
        session = TestDaoUtil.getSessionAndBeginTransaction();
        genericDao=TestDaoUtil.getGenericDao();
    }
    public void afterMethod() {
            TestDaoUtil.endTransactionAndCloseSession();
    }
}
