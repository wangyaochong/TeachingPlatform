package program.entity;

import org.hibernate.annotations.GenericGenerator;
import program.entity.entityInterface.IEntity;

import javax.persistence.*;
import java.util.Date;

/**
 *单项信息可能有一个或者多个资源，也可能没有资源
 */
@Entity
public class FileEntity implements IEntity{
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//资源id
    @ManyToOne(fetch = FetchType.EAGER)
    PersonEntity uploader;//上传者
    String filePath;//文件路径
    String htmlAccessPath;//html直接访问路径
    String originName;//文件原本的名字，文件上传后，统一重命名为id，
    // 然后保存，避免文件因为名字相同导致冲突或者丢失
    Long uploadTime;//上传时间

    Long fileSize;//文件大小

    @Override
    public String toString() {
        return "FileEntity{" +
                "id='" + id + '\'' +
                ", uploader=" + uploader +
                ", filePath='" + filePath + '\'' +
                ", htmlAccessPath='" + htmlAccessPath + '\'' +
                ", fileSize=" + fileSize +
                ", originName='" + originName + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }

    public String getHtmlAccessPath() {
        return htmlAccessPath;
    }

    public void setHtmlAccessPath(String htmlAccessPath) {
        this.htmlAccessPath = htmlAccessPath;
    }


    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }





    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }



    public FileEntity() {
    }

    public String getId() {
        return id;
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

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public FileEntity(PersonEntity uploader, String filePath, String htmlAccessPath, Long fileSize, String originName, Long uploadTime) {
        this.uploader = uploader;
        this.filePath = filePath;
        this.htmlAccessPath = htmlAccessPath;
        this.fileSize = fileSize;
        this.originName = originName;
        this.uploadTime = uploadTime;
    }
}
