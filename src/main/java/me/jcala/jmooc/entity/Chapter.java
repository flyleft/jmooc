package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "chapter_tb")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//id

    @Column(nullable = false,length = 40)
    private String name;//章节名称

    @Column(length = 40)
    private String video;//在线视频url

    @Column(name = "file_list")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER)
    private Set<File> fileList=new HashSet<>();//文件列表

    @Column(name = "exercise_list")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER)
    private Set<Exercise> exerciseList=new HashSet<>();//习题列表

    public Chapter() {
    }

}
