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
    private long id;//id

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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Set<File> getFileList() {
        return fileList;
    }

    public void setFileList(Set<File> fileList) {
        this.fileList = fileList;
    }

    public Set<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(Set<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
