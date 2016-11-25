package util;

import javafx.util.Pair;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SqlStatementConstructor{//sql语句构造器
//    public static <T>  String getAndSqlParam(T ... conditions){
//
//        return null;
//    }
    public static <T> Pair<String,List<Object>> createQuestionMarkSqlParam_and(T condition){//返回的是sql字符串以及依次的值
        Field[] declaredFields = condition.getClass().getDeclaredFields();
        List<Object> valueList=new ArrayList<Object>();
        String sqlParam="";
        for(Field f: declaredFields){
            f.setAccessible(true);
            Object fieldValue= null;
            try {
                fieldValue = f.get(condition);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(fieldValue!=null){
                sqlParam=f.getName()+" = ?"+" and ";
                valueList.add(fieldValue);
            }
        }
        sqlParam=sqlParam.substring(0,sqlParam.length()-" and ".length());

        return new Pair<String, List<Object>>(sqlParam,valueList);
    }
//    public static <T> String getAndSqlParam(T condition){
//        Field[] declaredFields = condition.getClass().getDeclaredFields();
//        String sqlParam="";
//        for(Field f: declaredFields){
//            f.setAccessible(true);
//            Object fv= null;
//            try {
//                fv = f.get(condition);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            if(fv!=null){
//                sqlParam=f.getName()+"="+fv.toString()+" and ";
//            }
//        }
//        sqlParam=sqlParam.substring(0,sqlParam.length()-" and ".length());
//        return sqlParam;
//    }
}
