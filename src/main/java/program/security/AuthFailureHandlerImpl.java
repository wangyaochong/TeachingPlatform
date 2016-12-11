package program.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by【王耀冲】on 【2016/12/11】 at 【11:55】.
 */
@Component
public class AuthFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        /*如果验证失败*/
        httpServletRequest.setAttribute("error","登录失败");//返回到登录页面，提示登录失败
        httpServletRequest.getRequestDispatcher("view/5html/logIn.html").forward(httpServletRequest,httpServletResponse);
    }
}
