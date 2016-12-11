package program.security;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by【王耀冲】on 【2016/12/11】 at 【23:55】.
 */
@Component
public class InvalidSessionStrategyImpl implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        //session失效后直接跳转到登录页面
//        httpServletResponse.sendRedirect("/TeachingPlatform/view/5html/logIn.html");
        httpServletRequest.getRequestDispatcher("/view/5html/logIn.html").forward(httpServletRequest,httpServletResponse);
    }
}
