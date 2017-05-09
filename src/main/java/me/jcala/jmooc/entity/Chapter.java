package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "chapter")
public class Chapter implements Serializable{

    private static final long serialVersionUID = -8060437688285524352L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//id

    @Column(nullable = false,length = 40)
    private String name;//章节名称

    @Column(nullable = false)
    private int pos;//位置，表示第几章节

    @ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.LAZY,targetEntity = Course.class)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(cascade = {CascadeType.REFRESH},orphanRemoval = true,fetch=FetchType.LAZY,mappedBy = "chapter")
    private Set<Lesson> lessons=new HashSet<>();

    public Chapter() {
    }

    public Chapter(String name, int pos, Course course) {
        this.name = name;
        this.pos = pos;
        this.course = course;
    }
}
