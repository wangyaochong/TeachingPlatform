package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PersonEntity {
    @Id
    @GenericGenerator(name="generator",strategy = "uuid")
    @GeneratedValue(generator = "generator")
    String id;
    String number;//学生就是学号，教师就是工号，管理员可以没有号码
    String name;//名字
    String age;//年龄
    String gender;//性别，男或女
    String phone;//手机号码
    String email;//邮箱账号

    @ManyToOne(cascade = CascadeType.ALL)
    PrivileageEntity privileageEntity;//所具有的权限，超级管理员的权限都是真，其他用户默认的权限都是假，

    public PersonEntity() {
    }

    public PersonEntity(String number, String name, String age, String gender, String phone, String email) {
        this.number = number;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", privileageEntity=" + privileageEntity +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PrivileageEntity getPrivileageEntity() {
        return privileageEntity;
    }

    public void setPrivileageEntity(PrivileageEntity privileageEntity) {
        this.privileageEntity = privileageEntity;
    }
}
