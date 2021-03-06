package program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.dao.BaseDao;
import program.dao.PersonDao;
import program.entity.ItemEntity;
import program.entity.PersonEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by 2 on 2016/11/14.
 */
@Controller
public class TestController {
    @Autowired
    PersonDao personDao;

    @RequestMapping("/src/main/test")
    @ResponseBody
    public String test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ){
        RequestContext requestContext=new RequestContext(httpServletRequest);
        Locale locale = requestContext.getLocale();
        System.out.println(locale);
        return "王耀冲";
    }

    @RequestMapping("/testDeletePerson")
    @ResponseBody
    public String test() {
        BaseDao baseDao = new BaseDao();
//        SessionFactory sessionFactory = baseDao.getSessionFactory();
//        PersonDao personDao = new PersonDao();
//        Integer delete = personDao.simpleDelete(new PersonEntity(null, "哈哈哈", null, "男", null, null, null));
        PersonEntity newPerson=new PersonEntity("201392252","3497725","hello",new Date().getTime(),"男","18840838242","1162025261@qq.com",null,null);
        Integer delete=personDao.simpleDelete(newPerson);
        return delete.toString();
    }
    @RequestMapping("/testQueryPerson")
    @ResponseBody
    public List<PersonEntity> testQuery(){
        return personDao.simpleQueryList(new PersonEntity());//查询所有的用户
    }

    @RequestMapping("/testRequestBody")
    @ResponseBody
    public ResponseInfo testRequestBody(@RequestBody ItemEntity itemEntity){
        System.out.println(itemEntity);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,null);
    }

    @RequestMapping("/testRequestBodyAndRequestParam")
    @ResponseBody
    public ResponseInfo testRequestBodyAndRequestParam(@RequestBody String firstParam,@RequestParam String queryParam){
        System.out.println(firstParam);
        System.out.println(queryParam);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,null);
    }

}
