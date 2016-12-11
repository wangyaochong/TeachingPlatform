package test;

import org.junit.Test;
import program.entity.ItemEntity;
import program.entity.ItemType;

import java.util.Date;

/**
 * Created by【王耀冲】on 【2016/12/10】 at 【17:05】.
 */
public class TestItemEntity {
    @Test
    public void test(){
        ItemEntity itemEntity=new ItemEntity("dsfds","sdfds", ItemType.ANNOUNCEMENT.toString(),true,new Date(),null);
        Date s = itemEntity.getCreateDate();
        System.out.println(s);
    }
}
