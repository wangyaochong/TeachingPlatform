package dao;

import org.hibernate.Query;

public class GenericDao extends BaseDao {
    public  <T> Integer simpleDelete(T condition){//返回通过一个查询条件【删除的列数】
//        SingleClassSqlConstructor singleClassSqlConstructor=new SingleClassSqlConstructor();
//        Pair<String, Map<String, Object>> deleteQuestionMarkSql_and = singleClassSqlConstructor.createDeleteQuestionMarkSql_and(condition);
//        String hql=deleteQuestionMarkSql_and.getKey();
        session.beginTransaction();
        Query query = session.createQuery("delete from entity.PersonEntity as personEntity where personEntity.name= ? ");
//        singleClassSqlConstructor.setParam(query,deleteQuestionMarkSql_and.getValue());
        query.setString(0, "哈哈哈");
        int count = query.executeUpdate();
        session.getTransaction().commit();//session的关闭工作交给具体的类或者切面
        return count;
    }
}
