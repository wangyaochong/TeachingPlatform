package test;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import urlBean.HeaderUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJson {
    @Test
    public void testJson(){
        Map<String,String> json=new HashMap<String, String>();
        json.put("name","wangyaochogn");
        json.put("age","18");
        List<Map<String,String>> jsonlist=new ArrayList<Map<String, String>>();
        jsonlist.add(json);
        HeaderUrl headerUrl=new HeaderUrl(null);
        System.out.println(JSONObject.toJSONString(headerUrl));
        System.out.println(headerUrl);
    }
}
