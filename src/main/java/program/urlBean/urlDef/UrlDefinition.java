package program.urlBean.urlDef;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class UrlDefinition {
    String name;
    String description;
    String href;
    String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UrlDefinition{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public UrlDefinition(String id, String href, Locale locale) {
        this.href = href;
        this.id = id;
        ResourceBundleMessageSource resourceBundleMessageSource=new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("Url");
        resourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        this.name= resourceBundleMessageSource.getMessage(id+".name", null, locale);
        this.description=resourceBundleMessageSource.getMessage(id+".desc",null,locale);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
