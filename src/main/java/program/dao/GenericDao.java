package program.dao;

import javafx.util.Pair;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import program.util.SingleClassSqlConstructor;

import java.util.List;
import java.util.Map;
@Repository(value="GenericDao")
public class GenericDao extends BaseDao {
    SingleClassSqlConstructor singleClassSqlConstructor=new SingleClassSqlConstructor();
    public <T> Integer simpleDelete(T condition){//返回通过一个查询条件【删除的列数】
        Pair<String, Map<String, Object>> deleteQuestionMarkSql_and =
                singleClassSqlConstructor.createQuestionMarkSql_and(condition,SingleClassSqlConstructor.type_DELETE);
        Query query = getSession().createQuery(deleteQuestionMarkSql_and.getKey());
        singleClassSqlConstructor.setParam(query,deleteQuestionMarkSql_and.getValue());
        return query.executeUpdate();
    }
    public <T> T simpleQueryOne(T condition){
        Pair<String, Map<String, Object>> deleteQuestionMarkSql_and =
                singleClassSqlConstructor.createQuestionMarkSql_and(condition,SingleClassSqlConstructor.type_QUERY);
        Query query = getSession().createQuery(deleteQuestionMarkSql_and.getKey());
        singleClassSqlConstructor.setParam(query,deleteQuestionMarkSql_and.getValue());
        return (T)query.list().get(0);
    }
    public <T> List<T> simpleQueryList(T condition){
        Pair<String, Map<String, Object>> deleteQuestionMarkSql_and =
                singleClassSqlConstructor.createQuestionMarkSql_and(condition,SingleClassSqlConstructor.type_QUERY);
        Query query = getSession().createQuery(deleteQuestionMarkSql_and.getKey());
        singleClassSqlConstructor.setParam(query,deleteQuestionMarkSql_and.getValue());
        return query.list();
    }
    public <T> List<T> simpleGetAll(Class<?> clazz){
        return getSession().createCriteria(clazz).list();
    }
}
