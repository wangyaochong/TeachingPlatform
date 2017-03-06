package program.util;

import javafx.util.Pair;
import org.hibernate.Query;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SingleClassSqlConstructor {//单表sql语句构造器
    public static final String type_DELETE="delete";
    public static final String type_QUERY="from";
    public static void setParam(Query query, Map<String, Object> valueMap) {
        for (Entry<String, Object> e : valueMap.entrySet()) {
            query.setParameter(e.getKey(), e.getValue());
        }
    }

    //在传入condition时，如果是null，则不加入查询条件，要查属性为null的表，需要另外使用criteria
    //这个位置其实可以给为null的属性进行初始化，比如String形式初始化为空字符串，数字型初始化为最大或者最小。
    public static <T> Pair<String, Map<String, Object>> createQuestionMarkHql_and(T condition, String operateType) {//返回的是sql字符串以及依次的值
        Field[] declaredFields = condition.getClass().getDeclaredFields();
        Map<String, Object> valueMap = new HashMap<String, Object>();
        //String sqlParam="delete "+condition.getClass().getName()+" as "+condition.getClass().getSimpleName()+" where ";
        String sqlParam = operateType+" " + condition.getClass().getName() + " where ";
        for(Field f: declaredFields){
            f.setAccessible(true);
            Object fieldValue= null;
            try {
                fieldValue = f.get(condition);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(fieldValue!=null){
//                sqlParam=sqlParam+condition.getClass().getSimpleName()+"."+f.getName()+" = :"+f.getName()+" and ";
                sqlParam = sqlParam + f.getName() + " = :" + f.getName() + "  and ";
                valueMap.put(f.getName(), fieldValue);
            }
        }
        sqlParam=sqlParam.substring(0,sqlParam.length()-" and ".length());
        return new Pair<String, Map<String, Object>>(sqlParam, valueMap);
    }
}
