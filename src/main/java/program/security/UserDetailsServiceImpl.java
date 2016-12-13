package program.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import program.dao.GenericDao;
import program.entity.PersonEntity;

import javax.annotation.Resource;

/**
 * Created by【王耀冲】on 【2016/12/11】 at 【12:04】.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource(name = "GenericDao")
    GenericDao genericDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setNumber(s);
        PersonEntity result = genericDao.simpleQueryOne(personEntity);
        return new AuthUserDetailsImpl(result);
    }
}
