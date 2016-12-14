package program.util;

import java.lang.reflect.Field;

/**
 * Created by【王耀冲】on 【2016/12/15】 at 【2:46】.
 */
public class EntityUtil {
    public static <T>void updateEntity(T oldObject,T newObject){
        Field[] declaredFields = oldObject.getClass().getDeclaredFields();
        for(Field  f:declaredFields){
            f.setAccessible(true);
            try {
                if(f.get(newObject)!=null){
                    f.set(oldObject,f.get(newObject));//如果属性不为空，则更新
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
