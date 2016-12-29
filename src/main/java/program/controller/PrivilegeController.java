package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import program.controller.base.DataOperateBase;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.entity.PrivilegeEntity;
import program.entity.entityInterface.IEntity;

import java.io.Serializable;

/**
 * Created by【王耀冲】on 【2016/12/29】 at 【21:43】.
 */
@Controller
@RequestMapping("/Privilege")
public class PrivilegeController extends DataOperateBase{
    @RequestMapping("/update")
    @ResponseBody
    public ResponseInfo update(@ModelAttribute PrivilegeEntity privilegeEntity){
        Serializable serializable = crudService.saveOrUpdateOne(privilegeEntity);
        return new ResponseInfo(ResponseFlag.STATUS_OK,"",serializable);
    }
}
