package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    private List<File> fileList;//文件列表

    @Column(name = "exercise_list")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER)
    private List<Exercise> exerciseList;//习题列表

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

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
