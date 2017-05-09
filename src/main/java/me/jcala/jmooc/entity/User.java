package me.jcala.jmooc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_tb")
public class User implements Serializable{

    private static final String DEFAULT_AVATAR="/img/default.png";

    private static final long serialVersionUID = 8665628721543300843L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//id

    @Column(nullable = false,length = 40)
    private String name;//用户名

    @Column(nullable = false,length = 32)
    private String password;//密码

    @Column(nullable = false,columnDefinition="tinyint default 1")
    private int role;//类型。1：学生，2：老师，3：管理员

    @Column(nullable = false,name = "avatar_url",columnDefinition="varchar(40) default '/img/default.png'")
    private String avatarUrl;//头像

    @OneToMany(cascade = CascadeType.REMOVE,fetch=FetchType.LAZY,mappedBy = "user")
    private Set<Course> courses=new HashSet<>();

    @Column(name = "exercise_collection")
    @ManyToMany(cascade = CascadeType.REMOVE,fetch=FetchType.LAZY)
    private Set<Exercise> exerciseCollection=new HashSet<>();//收藏的习题

    @Column(name = "exercise_error")
    @ManyToMany(cascade = CascadeType.REMOVE,fetch=FetchType.LAZY)
    private Set<Exercise> exerciseError=new HashSet<>();//错误的习题

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY,targetEntity = Notice.class)
    private Set<Notice> notices = new HashSet<>();

    public User() {
    }


    public User(long id,String name) {
        this.id=id;
        this.name=name;
    }

    public User(String name, String password, int role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.avatarUrl=DEFAULT_AVATAR;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Exercise> getExerciseCollection() {
        return exerciseCollection;
    }

    public void setExerciseCollection(Set<Exercise> exerciseCollection) {
        this.exerciseCollection = exerciseCollection;
    }

    public Set<Exercise> getExerciseError() {
        return exerciseError;
    }

    public void setExerciseError(Set<Exercise> exerciseError) {
        this.exerciseError = exerciseError;
    }

    public Set<Notice> getNotices() {
        return notices;
    }

    public void setNotices(Set<Notice> notices) {
        this.notices = notices;
    }
}
