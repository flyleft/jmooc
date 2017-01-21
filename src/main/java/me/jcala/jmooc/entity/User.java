package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    private long userId;//id

    private String name;//用户名

    private String password;//密码

    private int age;//年龄

    private String avatarUrl;//头像

    private boolean status;//状态：正常，冻结

    private int msgNum;//未读消息数

    private int sysMsgNum;//系统未读消息数

    @Column(name = "user_type")
    private int userType;//0:student 1:teacher 2:admin

}
