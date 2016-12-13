package program.security.filter;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import program.entity.PersonEntity;
import program.security.AuthImpl;
import program.security.AuthUserDetailsImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityContext context =new SecurityContextImpl();
        context.setAuthentication(new AuthImpl(new AuthUserDetailsImpl(
                new PersonEntity("","","",0,"","","",null))));
        SecurityContextHolder.setContext(context);
        HttpSession session = ((HttpServletRequest) request).getSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT",SecurityContextHolder.getContext());
        //这两个缺一不可，既要在session中放置SPRING_SECURITY_CONTEXT对象
        //又要在SecurityContextHolder中放置对象
        chain.doFilter(request,response);
        return ;
    }
    @Override
    public void destroy() {
    }
}
