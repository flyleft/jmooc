package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "chapter_tb")
public class Chapter {

    @Id
    private long id;//id

    private String name;//章节名称

    private String video;//在线视频url

    private List<String> fileUrls;//文件列表

    private List<Exercise> exerciseList;//习题列表

    public Chapter() {
    }

    public Chapter(long id, String name, String video,
                   List<String> fileUrls, List<Exercise> exerciseList) {
        this.id = id;
        this.name = name;
        this.video = video;
        this.fileUrls = fileUrls;
        this.exerciseList = exerciseList;
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

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
