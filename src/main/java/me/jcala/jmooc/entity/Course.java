package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "course_tb")
public class Course {

    @Id
    private long id;//id

    private String  name;//课程名称

    private int difficulty;//课程难度

    private List<Chapter> chapterList;//课程章节列表

    public Course() {
    }

    public Course(long id, String name, int difficulty, List<Chapter> chapterList) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.chapterList = chapterList;
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }
}
