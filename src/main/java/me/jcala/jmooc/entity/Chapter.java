package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "chapter")
public class Chapter implements Serializable{

    private static final long serialVersionUID = -8060437688285524352L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//id

    @Column(nullable = false,length = 40)
    private String name;//章节名称

    @Column(nullable = false)
    private int pos;//位置，表示第几章节

    @Column(length = 40)
    private String video;//在线视频url

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.EAGER)
    private Set<Lesson> lessons=new HashSet<>();

    public Chapter() {
    }

}
