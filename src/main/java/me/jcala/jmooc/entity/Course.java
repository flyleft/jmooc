package me.jcala.jmooc.entity;

import lombok.Data;

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
    private int id;//id

    @Column(nullable = false,length = 40)
    private String  name;//课程名称

    @Column(nullable = false,length = 10)
    private String dir;//方向:fe,be,mb,db

    @Column(nullable = false,length = 10)
    private String type;//类型:c,cp,java

    @Column(name = "chapters")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.LAZY)
    private Set<Chapter> chapters=new HashSet<>();//课程章节列表

    public Course() {
    }


}
