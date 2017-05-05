package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "course_tb")
public class Course implements Serializable{

    private static final long serialVersionUID = -501263791865367727L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//id

    @Column(nullable = false,length = 40)
    private String  name;//课程名称

    @Column(nullable = false,columnDefinition="tinyint default 1")//1,2,3,4四个难度等级
    private int difficulty;//课程难度

    @Column(name = "chapter_list")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER)
    private Set<Chapter> chapterList=new HashSet<>();//课程章节列表

    public Course() {
    }


}
