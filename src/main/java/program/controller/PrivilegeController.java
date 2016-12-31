package program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import program.controller.base.DataOperateBase;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.entity.GroupEntity;
import program.entity.PrivilegeEntity;
import program.entity.entityInterface.IEntity;
import program.entity.wrapper.PrivilegeWrapper;
import program.service.PrivilegeService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by【王耀冲】on 【2016/12/29】 at 【21:43】.
 */
@Controller
@RequestMapping("/Privilege")
public class PrivilegeController extends DataOperateBase{
    @Resource
    PrivilegeService privilegeService;
    @RequestMapping("/update")
    @ResponseBody
    public ResponseInfo update(@ModelAttribute PrivilegeEntity privilegeEntity){
        Serializable serializable = crudService.saveOrUpdateOne(privilegeEntity);
        return new ResponseInfo(ResponseFlag.STATUS_OK,"",serializable);
    }

    @RequestMapping("/getPrivByGroupId")//根据groupId获取权限，如果没有groupId(如超级管理员)，则返回顶级权限
    @ResponseBody
    public ResponseInfo getPrivByGroupId(@RequestParam(required = false) String groupId){
        List<PrivilegeEntity> privByGroupId = privilegeService.getPrivByGroupId(groupId);
        return new ResponseInfo(ResponseFlag.STATUS_OK,null,privByGroupId);
    }

}
