package test;

import org.junit.Test;
import program.entity.GroupEntity;
import program.entity.type.GroupType;
import test.util.DaoBaseUtil;

import java.util.Date;

/**
 * Created by 【王耀冲】 on 【2016/12/31】 at 【14:40】.
 */
public class TestGroupEntity extends DaoBaseUtil {
    @Test
    public void testAddGroup(){
        this.beforeMethod();
//        this.session.save();
        GroupEntity groupEntity = new GroupEntity("根", GroupType.CLASS, null, null, null, new Date().getTime());
        this.session.save(groupEntity);
        this.afterMethod();
    }
}
