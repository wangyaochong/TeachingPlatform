package program.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * PersonEntity是所有的用户，包括教师，学生，管理员等，但只用一个表来表示，不同用户的区别有权限分隔
 */
@Entity
public class PersonEntity {
    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;

    @Column(unique = true)
    String number;//学生就是学号，教师就是工号，管理员可以没有号码
    String password;//用户名密码
    String name;//名字
    Integer age;//年龄
    String gender;//性别，男或女
    String phoneNumber;//手机号码
    String email;//邮箱账号


    //所具有的权限，超级管理员的权限都是真，其他用户默认的权限都是假，
    //可能具有多个权限，比如既是数学课代表又是英语课代表
    //所有用于默认都具有读权限
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<PrivilegeEntity> privilegeEntityList;

    //所在的分组，有可能有多个，比如同时上数学和英语课
    List<GroupEntity> groupEntityList;

    public List<GroupEntity> getGroupEntityList() {
        return groupEntityList;
    }

    public void setGroupEntityList(List<GroupEntity> groupEntityList) {
        this.groupEntityList = groupEntityList;
    }

    public PersonEntity() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PrivilegeEntity> getPrivilegeEntityList() {
        return privilegeEntityList;
    }

    public void setPrivilegeEntityList(List<PrivilegeEntity> privilegeEntityList) {
        this.privilegeEntityList = privilegeEntityList;
    }

    public PersonEntity(String number, String password, String name, Integer age, String gender, String phoneNumber, String email, List<PrivilegeEntity> privilegeEntityList, List<GroupEntity> groupEntityList) {
        this.number = number;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.privilegeEntityList = privilegeEntityList;
        this.groupEntityList = groupEntityList;
    }
}
