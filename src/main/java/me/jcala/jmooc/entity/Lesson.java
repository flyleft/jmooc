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

    @ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER,targetEntity = Chapter.class)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @Column(name = "file_json",columnDefinition="text")
    private String upFileList;//存储文件信息。List<UpFile>的json形式，可以省去一个数据表

    @Column(name = "exercise_list")
    @OneToMany(cascade = CascadeType.REMOVE,fetch=FetchType.LAZY)
    private Set<Exercise> exerciseList=new HashSet<>();//习题列表

    @Transient
    private int fileNum;//文件数量，为了方便前端的数量

    @Transient
    private int exeNum;//习题数量，为了方便前端的数量

}
