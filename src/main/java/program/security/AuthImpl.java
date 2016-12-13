package program.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthImpl implements Authentication {
    AuthUserDetailsImpl authUserDetails;
    public AuthImpl(AuthUserDetailsImpl authUserDetails) {
        this.authUserDetails=authUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authUserDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return authUserDetails;
    }

    @Override
    public Object getDetails() {
        return this.authUserDetails;
    }

    @Override
    public Object getPrincipal() {
        return this.authUserDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return null;
    }
}
