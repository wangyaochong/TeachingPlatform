package program.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by【王耀冲】on 【2016/12/11】 at 【23:05】.
 */
public class AuthManagerImpl implements AuthenticationManager{
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
