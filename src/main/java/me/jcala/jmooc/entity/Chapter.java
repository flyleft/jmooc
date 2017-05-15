package me.jcala.jmooc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER,targetEntity = Course.class)
    @JoinColumn(name = "course_id")
    private Course course;

    @OrderBy("pos ASC")
    @OneToMany(cascade = CascadeType.REMOVE,orphanRemoval = true,fetch=FetchType.LAZY,mappedBy = "chapter")
    private Set<Lesson> lessons=new HashSet<>();

    public Chapter() {
    }


    public Chapter(long id) {
        this.id=id;
    }

    public Chapter(String name, int pos, Course course) {
        this.name = name;
        this.pos = pos;
        this.course = course;
    }

}
