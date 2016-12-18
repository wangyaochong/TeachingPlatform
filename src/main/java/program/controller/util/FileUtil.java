package program.controller.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * Created by【王耀冲】on 【2016/12/18】 at 【11:27】.
 */
@Component
public class FileUtil implements ServletContextAware {
    private String realFileBase;
    private String subDir="uploadFiles";
    private String completeRealFilePath;
    private String completeContextFilePath;
    public String getCompleteRealFilePath() {
        return completeRealFilePath;
    }
    public String getCompleteContextFilePath(){
        return this.completeContextFilePath;
    }
    @Override
    public void setServletContext(ServletContext servletContext) {
        if (this.realFileBase == null) {
            this.realFileBase = servletContext.getRealPath(File.separator);
            this.completeRealFilePath =this.realFileBase+ File.separator+subDir+File.separator;
            File file=new File(realFileBase);
            if(file.exists()==false){
                file.mkdir();//创建文件上传的路径
            }
        }
        String contextPath = servletContext.getContextPath();
        this.completeContextFilePath =contextPath+File.separator+subDir+File.separator;
    }
}
