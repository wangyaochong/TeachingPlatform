package dao;

import javafx.util.Pair;
import org.hibernate.Query;
import util.SqlStatementConstructor;

import java.util.List;
public class GenericDao extends BaseDao {
    public  <T> Integer simpleDelete(T condition){//返回通过一个查询条件【删除的列数】
        session=sessionFactory.openSession();
        session.beginTransaction();
        Pair<String, List<Object>> questionMarkSqlParam_and = SqlStatementConstructor.createQuestionMarkSqlParam_and(condition);
        String hql="delete from "+condition.getClass().getSimpleName()+" where " +questionMarkSqlParam_and.getKey();
        Query query = session.createQuery(hql);
        Integer index=0;
        for(Object o: questionMarkSqlParam_and.getValue()){
            query.setParameter(index,o);
            index++;
        }
        session.getTransaction().commit();
        session.close();
        return query.executeUpdate();
    }
}
