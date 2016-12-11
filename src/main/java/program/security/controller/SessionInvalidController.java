package program.security.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by【王耀冲】on 【2016/12/12】 at 【2:09】.
 */
@Component
public class SessionInvalidController {
    @RequestMapping("invalidateSession")
    public void invalidateSession(HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect("/view/5html/logIn.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
