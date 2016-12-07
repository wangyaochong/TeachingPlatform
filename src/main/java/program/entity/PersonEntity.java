package program.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * PersonEntity是所有的用户，包括教师，学生，管理员等，但只用一个表来表示，不同用户的区别有权限分隔
 */
@Entity
public class PersonEntity {
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;
    String number;//学生就是学号，教师就是工号，管理员可以没有号码
    String name;//名字
    Integer age;//年龄
    String gender;//性别，男或女
    String phone;//手机号码
    String email;//邮箱账号
    @ManyToOne(cascade = CascadeType.ALL)
    PrivilegeEntity privilegeEntity;//所具有的权限，超级管理员的权限都是真，其他用户默认的权限都是假，

    public PersonEntity() {
    }

    public PersonEntity(String number, String name, Integer age, String gender, String phone, String email, PrivilegeEntity privilegeEntity) {
        this.number = number;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.privilegeEntity = privilegeEntity;
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
                ", privilegeEntity=" + privilegeEntity +
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public PrivilegeEntity getPrivilegeEntity() {
        return privilegeEntity;
    }

    public void setPrivilegeEntity(PrivilegeEntity privilegeEntity) {
        this.privilegeEntity = privilegeEntity;
    }

}
