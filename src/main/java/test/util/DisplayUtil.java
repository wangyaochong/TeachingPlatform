package test.util;

import java.util.List;

/**
 * Created by 【王耀冲】 on 【2016/12/31】 at 【15:44】.
 */
public class DisplayUtil {
    public static  <T> void display(List<T> list) {
        for(T o:list){
            System.out.println(o);
        }
    }
}
