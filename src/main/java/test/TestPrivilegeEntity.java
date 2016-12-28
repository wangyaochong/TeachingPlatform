package test;

import org.junit.Test;
import program.entity.PrivilegeEntity;
import test.util.DaoBaseUtil;

/**
 * Created by 【王耀冲】 on 【2016/12/23】 at 【10:32】.
 */
public class TestPrivilegeEntity extends DaoBaseUtil{
    @Test
    public void testAdd(){
        this.beforeMethod();
        this.session.save(new PrivilegeEntity(true,true,true,true,true,true,true,null));//添加一个super权限
        this.afterMethod();
    }
}
