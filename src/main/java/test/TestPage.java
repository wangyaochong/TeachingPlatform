package test;

import org.junit.Test;
import program.util.PageBean;

import java.util.ArrayList;
import java.util.List;

public class TestPage {
    @Test
    public void testPageBean(){
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
//        PageBean pageBean=new PageBean(0,5,10,"hello",true,list);
//        System.out.println(pageBean);
    }
}
