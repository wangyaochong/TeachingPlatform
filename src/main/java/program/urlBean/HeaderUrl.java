package program.urlBean;
import program.urlBean.urlDef.UrlDefinition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class HeaderUrl  {
    private UrlDefinition webTitle;//网站的标题
    private List<UrlDefinition> headerNavLeft;//网站左侧菜单
    private List<UrlDefinition> headerNavRight;//网站右侧菜单
    private UrlDefinition headerDropDownTitle;//网站下拉菜单标题
    private List<UrlDefinition> headerDropDownList;//网站下拉菜单
    public HeaderUrl(Locale locale) {
        webTitle=new UrlDefinition("header.标题","index",locale);//首页大标题
        headerNavLeft=new ArrayList<UrlDefinition>(Arrays.asList(//导航栏左侧菜单
                new UrlDefinition("header.navLeft.作业","assignment",locale),
                new UrlDefinition("header.navLeft.文档","document",locale),
                new UrlDefinition("header.navLeft.视频","video",locale)
        ));
        headerNavRight=new ArrayList<UrlDefinition>(Arrays.asList(//导航栏右侧菜单
                new UrlDefinition("header.navRight.注销", "locale.logOut",locale)
        ));
        headerDropDownTitle=new UrlDefinition("header.navDrop","#",locale);//下拉菜单的标题
        headerDropDownList=new ArrayList<UrlDefinition>(Arrays.asList(//下拉菜单具体选项
                new UrlDefinition("header.navDrop.用户中心","userCenter",locale),
                new UrlDefinition("header.navDrop.课程中心","courseCenter",locale),
                new UrlDefinition("header.navRight.语言设置","languageSetting",locale),
                new UrlDefinition("header.navRight.搜索","search",locale)
        ));
    }
    @Override
    public String toString() {
        return "HeaderUrl{" +
                "webTitle=" + webTitle +
                ", headerNavLeft=" + headerNavLeft +
                ", headerNavRight=" + headerNavRight +
                ", headerDropDownTitle=" + headerDropDownTitle +
                ", headerDropDownList=" + headerDropDownList +
                '}';
    }

    public UrlDefinition getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(UrlDefinition webTitle) {
        this.webTitle = webTitle;
    }

    public List<UrlDefinition> getHeaderNavLeft() {
        return headerNavLeft;
    }

    public void setHeaderNavLeft(List<UrlDefinition> headerNavLeft) {
        this.headerNavLeft = headerNavLeft;
    }

    public List<UrlDefinition> getHeaderNavRight() {
        return headerNavRight;
    }

    public void setHeaderNavRight(List<UrlDefinition> headerNavRight) {
        this.headerNavRight = headerNavRight;
    }

    public UrlDefinition getHeaderDropDownTitle() {
        return headerDropDownTitle;
    }

    public void setHeaderDropDownTitle(UrlDefinition headerDropDownTitle) {
        this.headerDropDownTitle = headerDropDownTitle;
    }

    public List<UrlDefinition> getHeaderDropDownList() {
        return headerDropDownList;
    }

    public void setHeaderDropDownList(List<UrlDefinition> headerDropDownList) {
        this.headerDropDownList = headerDropDownList;
    }


}
