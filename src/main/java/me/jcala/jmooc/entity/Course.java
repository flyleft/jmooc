package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "course_tb")
public class Course {

    private long id;//id

    private String  name;//课程名称

    private int difficulty;//课程难度

    private List<Chapter> chapterList;//课程章节列表

}
