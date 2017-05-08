package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//习题表
@Data
@Entity
@Table(name = "exercise")
public class Exercise implements Serializable{

    private static final long serialVersionUID = -5175465537985355910L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//id

    @Column(nullable = false,length = 40)
    private String name;//习题名称

    @Column(nullable = false,columnDefinition="tinyint default 1")
    private int type;//类型。1:选择题，2：大题

    @Column(columnDefinition="text")
    private String desp;//习题描述

    @Column(nullable = false,columnDefinition="tinyint default 1")//1,2,3,4四个难度等级
    private int difficulty;//难度

    private String chooses;//选项列表

    @Column(name = "choose_answer")
    private int chooseAnswer;//正确选项

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,targetEntity = ExerciseComment.class)
    private Set<ExerciseComment> exerciseCommentList=new HashSet<>();

    public Exercise() {
    }

}
