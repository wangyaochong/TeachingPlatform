package program.urlBean;

import program.urlBean.urlDef.UrlDefinition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class RollPictureUrl {
    private List<UrlDefinition> pictures;
    public RollPictureUrl(Locale locale) {
        pictures=new ArrayList<UrlDefinition>(Arrays.asList(
                new UrlDefinition("pictures1","files/picture/pic1.jpg",locale),
                new UrlDefinition("pictures2","files/picture/pic2.jpg",locale)
        ));
    }
    public List<UrlDefinition> getPictures() {
        return pictures;
    }
    public void setPictures(List<UrlDefinition> pictures) {
        this.pictures = pictures;
    }
}
