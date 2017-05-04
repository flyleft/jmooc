package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//id

    @Column(nullable = false,length = 40)
    private String name;//用户名

    @Column(nullable = false,length = 32)
    private String password;//密码

    @Column(nullable = false,columnDefinition="tinyint default 1")
    private int type;//类型。1：学生，2：老师，3：管理员

    @Column(columnDefinition="tinyint default 1")
    private int age;//年龄

    @Column(nullable = false,name = "avatar_url",columnDefinition="varchar(40) default '/img/default.png'")
    private String avatarUrl;//头像

    @Column(nullable = false,columnDefinition="tinyint default 1")//1: 正常; 0:冻结
    private boolean status;//状态：正常，冻结

    @Column(name = "exercise_collection")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.LAZY)
    private Set<Exercise> exerciseCollection=new HashSet<>();//收藏的习题

    @Column(name = "exercise_error")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.LAZY)
    private Set<Exercise> exerciseError=new HashSet<>();//错误的习题

    public User() {
    }

    public User(int id,String name, String password, int type) {
        this.id=id;
        this.name = name;
        this.password = password;
        this.type = type;
    }
}
