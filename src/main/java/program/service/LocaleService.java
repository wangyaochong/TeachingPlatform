package program.service;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by 【王耀冲】 on 【2017/2/22】 at 【18:47】.
 */
@Service
public class LocaleService {
    static final String PROPERTIES_PATH="/locale.properties";
    public Map<String,String> getAllLocaleProperties(Locale locale){
        Map<String ,String> result=new HashMap<>();
        InputStream resourceAsStream = getClass().getResourceAsStream(PROPERTIES_PATH);
        Properties properties=new Properties();
        try {
            properties.load(resourceAsStream);//加载配置文件，从配置文件中读取值，然后放到返回的国际化map中
            Enumeration<?> enumeration = properties.propertyNames();
            ResourceBundleMessageSource resourceBundleMessageSource=new ResourceBundleMessageSource();
            resourceBundleMessageSource.setBasename("locale");

            while (enumeration.hasMoreElements()){
                String o = (String) enumeration.nextElement();
                result.put(o,resourceBundleMessageSource.getMessage(o,null,locale));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(resourceAsStream!=null){
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
