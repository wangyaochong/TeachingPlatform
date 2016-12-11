package program.security.util;

import program.entity.PrivilegeEntity;
import program.security.AuthGrantedAuthority;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by【王耀冲】on 【2016/12/11】 at 【12:24】.
 */
public class AuthGrantedAuthorityUtil {
    public static List<AuthGrantedAuthority> getAuthorities(PrivilegeEntity privilegeEntity) {
        ArrayList<AuthGrantedAuthority> authorities = new ArrayList<>();
        Field[] declaredFields = privilegeEntity.getClass().getDeclaredFields();
        for(Field f:declaredFields){
            try {
                f.setAccessible(true);
                Object o = f.get(privilegeEntity);
                if(o instanceof  Boolean){//如果是Boolean类型的权限信息，则加上权限
                    authorities.add(new AuthGrantedAuthority("ROLE_"+f.getName()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (privilegeEntity!=null){
            authorities.add(new AuthGrantedAuthority("ROLE_USER"));//所有登录成功的用户，都有一个默认的
        }
        return authorities;
    }
}
