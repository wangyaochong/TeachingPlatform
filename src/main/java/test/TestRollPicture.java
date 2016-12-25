package test;

import org.junit.Test;
import program.entity.FileEntity;
import program.entity.ItemEntity;
import program.entity.type.ItemType;
import test.util.DaoBaseUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by【王耀冲】on 【2016/12/17】 at 【21:20】.
 */
public class TestRollPicture extends DaoBaseUtil {
    @Test
    public void testAddRollPicture() {
        this.beforeMethod();
//        new FileEntity()
        for (int i = 0; i < 3; i++) {
            List<FileEntity> fileEntities = this.genericDao.simpleQueryList(new FileEntity());
            this.session.save(
                    new ItemEntity("这是一个标题", "这是描述", ItemType.ROLLPICTURE.toString(), true, new Date().getTime(),
                            null, null, null, fileEntities));
        }

        this.afterMethod();
    }
}
