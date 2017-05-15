package me.jcala.jmooc.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = -501263791865367727L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//id

    @NotBlank(message = "课程名称不可以为空")
    @Column(nullable = false, length = 40)
    private String name;//课程名称

    @NotBlank(message = "课程描述不可以为空")
    @Column(nullable = false, columnDefinition = "text")
    private String desp;//课程描述

    @NotBlank(message = "方向不可以为空")
    @Column(nullable = false, length = 10)
    private String dir;//方向:fe,be,mb,db

    @NotBlank(message = "类型不可以为空")
    @Column(nullable = false, length = 10)
    private String type;//类型:c,cp,java

    @Column(nullable = false,columnDefinition = "int default 0")
    private int parNum;//参与本课程的人数

    @Temporal(TemporalType.DATE)
    @Column(nullable = false,name = "create_at")
    private Date createdAt;

//    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, targetEntity = User.class)
//    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "chapters")
    @OrderBy("pos ASC")
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "course")
    private Set<Chapter> chapters = new HashSet<>();//课程章节列表

    public Course() {
    }

    public Course(long id) {
        this.id = id;
    }

}