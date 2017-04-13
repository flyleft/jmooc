package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "chapter_tb")
public class Chapter {

    private long id;//id

    private String name;//章节名称

    private String video;//在线视频url

    private List<String> fileUrls;//文件列表

    private List<Exercise> exerciseList;//习题列表
}
