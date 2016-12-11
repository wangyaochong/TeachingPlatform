package program.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by【王耀冲】on 【2016/12/11】 at 【12:17】.
 */
public class AuthGrantedAuthority implements GrantedAuthority {
    String authority;
    @Override
    public String getAuthority() {
        return null;
    }
    public AuthGrantedAuthority(String authority){
        this.authority=authority;
    }
    public void SetAuthority(String authority){
        this.authority=authority;
    }
}
