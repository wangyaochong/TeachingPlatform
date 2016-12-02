package program.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 *单项信息可能有一个或者多个资源，也可能没有资源
 */
@Entity
public class ResourceEntity {
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//资源id
    @ManyToOne
    PersonEntity uploader;//上传者
    String filePath;//文件路径
    Date uploadTime;//上传时间

    public ResourceEntity() {
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ResourceEntity{" +
                "id='" + id + '\'' +
                ", uploader=" + uploader +
                ", filePath='" + filePath + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonEntity getUploader() {
        return uploader;
    }

    public void setUploader(PersonEntity uploader) {
        this.uploader = uploader;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public ResourceEntity(PersonEntity uploader, String filePath, Date uploadTime) {
        this.uploader = uploader;
        this.filePath = filePath;
        this.uploadTime = uploadTime;
    }
}
