package me.jcala.jmooc.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course implements Serializable{

    private static final long serialVersionUID = -501263791865367727L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//id

    @NotBlank(message="课程名称不可以为空")
    @Column(nullable = false,length = 40)
    private String  name;//课程名称

    @NotBlank(message="课程描述不可以为空")
    @Column(nullable = false,columnDefinition="text")
    private String  desp;//课程描述

    @NotBlank(message="方向不可以为空")
    @Column(nullable = false,length = 10)
    private String dir;//方向:fe,be,mb,db

    @NotBlank(message="类型不可以为空")
    @Column(nullable = false,length = 10)
    private String type;//类型:c,cp,java

    @ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "chapters")
    @OneToMany(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER,mappedBy = "course")
    private Set<Chapter> chapters=new HashSet<>();//课程章节列表

    public Course() {
    }

    public Course(long id) {
        this.id=id;
    }

    public Course(String name, String desp, String dir, String type) {
        this.name = name;
        this.desp = desp;
        this.dir = dir;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desp='" + desp + '\'' +
                ", dir='" + dir + '\'' +
                ", type='" + type + '\'' +
                ", chapters=" + chapters +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
