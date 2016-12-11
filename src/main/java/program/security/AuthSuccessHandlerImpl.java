package program.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by【王耀冲】on 【2016/12/11】 at 【11:46】.
 */
@Component
public class AuthSuccessHandlerImpl  implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        //如果是直接forward会报错
        //11-Dec-2016 23:20:48.747 警告 [http-nio-8080-exec-8]
        // org.springframework.security.web.context.HttpSessionSecurityContextRepository.createNewSessionIfAllowed
        // Failed to create a session, as response has been committed. Unable to store SecurityContext.
        //意思是Spring Security来不及将信息保存的到SecurityContext中，请求就结束了
//        httpServletRequest.getRequestDispatcher("view/5html/index.html").forward(httpServletRequest,httpServletResponse);
        //也可以使用跳转策略
//        RedirectStrategy  redirectStrategy =new DefaultRedirectStrategy();
//        redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/view/5html/index.html");
        //认证成功后简单的跳转到首页
        httpServletResponse.sendRedirect("view/5html/index.html");
    }
}
