package program.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import program.entity.PersonEntity;
import program.security.util.AuthGrantedAuthorityUtil;

import java.util.Collection;

/**
 * Created by【王耀冲】on 【2016/12/11】 at 【12:10】.
 */
public class AuthUserDetailsImpl implements UserDetails {
    PersonEntity personEntity;
    public AuthUserDetailsImpl(PersonEntity personEntity){
        this.personEntity=personEntity;
        if(this.personEntity==null){
            this.personEntity=new PersonEntity();//如果没有查询到用户，给一个不存在的person
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthGrantedAuthorityUtil.getAuthorities(this.personEntity.getPrivilegeEntity());
    }
    @Override
    public String getPassword() {
        return personEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return personEntity.getNumber();
    }//这里的用户编号，也就是学号之类的是用户登录名

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }//目前暂不使用SpringSecurity的禁用等高级特性
}
