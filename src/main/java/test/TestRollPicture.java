package test;

import org.junit.Test;
import program.entity.FileEntity;
import program.entity.ItemEntity;
import program.entity.ItemType;
import test.util.DaoBaseUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by【王耀冲】on 【2016/12/17】 at 【21:20】.
 */
public class TestRollPicture extends DaoBaseUtil {
    @Test
    public void testAddRollPicture(){
        this.beforeMethod();
//        new FileEntity()
        FileEntity fileEntity = this.genericDao.simpleQueryOne(new FileEntity());
        ArrayList<FileEntity> fileEntities=new ArrayList<>();
        fileEntities.add(fileEntity);
        Serializable id = this.session.save(new ItemEntity("这是一个标题", "这是描述", ItemType.ROLLPICTURE.toString(), true, new Date().getTime(), null));
        ItemEntity itemEntity = this.session.get(ItemEntity.class, id);
        itemEntity.setResources(fileEntities);

        this.afterMethod();
    }
}
