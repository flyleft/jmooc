package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 课时
 */
@Data
@Entity
@Table(name = "lesson")
public class Lesson implements Serializable{

    private static final long serialVersionUID = 6819059851938668124L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 40)
    private String name;//章节名称

    @Column(nullable = false)
    private int pos;//位置，表示第几课时

    @Column(length = 40)
    private String video;//在线视频url

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.REFRESH},fetch=FetchType.LAZY,targetEntity = Chapter.class)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @Column(name = "file_list")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch= FetchType.EAGER)
    private Set<File> fileList=new HashSet<>();//文件列表

    @Column(name = "exercise_list")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER)
    private Set<Exercise> exerciseList=new HashSet<>();//习题列表

}
