package program.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by【王耀冲】on 【2016/12/12】 at 【0:04】.
 */
@Component
public class SessionAuthStrategyImpl implements SessionAuthenticationStrategy{
    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SessionAuthenticationException {
        if(authentication.isAuthenticated()){
            try {
                httpServletResponse.sendRedirect("view/5html/index.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                httpServletRequest.getRequestDispatcher("view/5html/logIn.html").forward(httpServletRequest,httpServletResponse);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
