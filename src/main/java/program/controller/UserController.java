package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import program.service.UserService;
import program.entity.PersonEntity;

import javax.annotation.Resource;

/**
 * Created by【王耀冲】on 【2016/12/15】 at 【1:13】.
 */
@Controller
@RequestMapping("/User")
public class UserController {
    @Resource
    UserService userService;
    @RequestMapping("/getCurrent")
    @ResponseBody
//    获取当前在线用户
    public PersonEntity getCurrentUser() {
        return userService.getCurrentUser();
    }
}
