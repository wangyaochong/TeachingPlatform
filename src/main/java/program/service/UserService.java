package program.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import program.dao.GenericDao;
import program.entity.PersonEntity;

import javax.annotation.Resource;

/**
 * Created by【王耀冲】on 【2016/12/18】 at 【10:52】.
 */
@Service
public class UserService {
    @Resource(name = "GenericDao")
    GenericDao genericDao;
    public PersonEntity getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String number = authentication.getName();
//        AuthUserDetailsImpl principal = (AuthUserDetailsImpl) authentication.getPrincipal();
        PersonEntity personEntity=new PersonEntity();
        personEntity.setNumber(number);//以
        //由于用户是缓存在session中，所以如果当session有效时，用户权限发生更改，session缓存的用户信息不变，所以需要从数据库中查询
        return genericDao.simpleQueryOne(personEntity) ;
    }
}
