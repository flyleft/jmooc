package me.jcala.jmooc.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
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

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.REFRESH},fetch=FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "chapters")
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.REFRESH},orphanRemoval = true,fetch=FetchType.LAZY,mappedBy = "course")
    private Set<Chapter> chapters=new HashSet<>();//课程章节列表

    public Course() {
    }

    public Course(String name, String desp, String dir, String type) {
        this.name = name;
        this.desp = desp;
        this.dir = dir;
        this.type = type;
    }
}
