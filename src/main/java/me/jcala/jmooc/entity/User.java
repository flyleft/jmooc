package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "user_tb")
public class User {

    @Id
    private long id;//id

    private String name;//用户名

    private String password;//密码

    private int type;//类型。1：学生，2：老师，3：管理员

    private int age;//年龄

    private String avatarUrl;//头像

    private boolean status;//状态：正常，冻结

    private int msgNum;//未读消息数

    private int sysMsgNum;//系统未读消息数

    private List<Integer> exerciseCollection;//收藏的习题

    private List<Integer> exerciseError;//错误的习题

    public User() {
    }

    public User(long id, String name, String password, int type, int age, String avatarUrl,
                boolean status, int msgNum, int sysMsgNum, List<Integer> exerciseCollection,
                List<Integer> exerciseError) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
        this.age = age;
        this.avatarUrl = avatarUrl;
        this.status = status;
        this.msgNum = msgNum;
        this.sysMsgNum = sysMsgNum;
        this.exerciseCollection = exerciseCollection;
        this.exerciseError = exerciseError;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }

    public int getSysMsgNum() {
        return sysMsgNum;
    }

    public void setSysMsgNum(int sysMsgNum) {
        this.sysMsgNum = sysMsgNum;
    }

    public List<Integer> getExerciseCollection() {
        return exerciseCollection;
    }

    public void setExerciseCollection(List<Integer> exerciseCollection) {
        this.exerciseCollection = exerciseCollection;
    }

    public List<Integer> getExerciseError() {
        return exerciseError;
    }

    public void setExerciseError(List<Integer> exerciseError) {
        this.exerciseError = exerciseError;
    }
}
