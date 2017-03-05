package program.controller.base;

import org.springframework.stereotype.Component;
import program.entity.PersonEntity;
import program.service.CrudService;
import program.service.PageListService;

import javax.annotation.Resource;

/**
 * Created by【王耀冲】on 【2016/12/29】 at 【21:50】.
 */
@Component
public class DataOperateBase {
    @Resource
    protected CrudService crudService;
    @Resource
    protected PageListService<PersonEntity> pageListService;
    static int initCount=0;
    public DataOperateBase(){//这里可以说明，作为基类，这个类会被初始化多少次
        initCount++;
        System.out.println("DataOperateBase initializing count: "+initCount);
    }
}
