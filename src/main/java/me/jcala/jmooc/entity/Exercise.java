package me.jcala.jmooc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//习题表
@Getter
@Setter
@Entity
@Table(name = "exercise")
public class Exercise implements Serializable{

    private static final long serialVersionUID = -5175465537985355910L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//id

    @Column(nullable = false,columnDefinition="text")
    private String title;//习题名称

    @Column(nullable = false,columnDefinition="tinyint default 1")
    private int type;//类型。1:选择题，2：大题

    @Column(columnDefinition="text")
    private String desp;//习题描述

    @Column(nullable = false,columnDefinition="tinyint default 1")//1,2,3,4四个难度等级
    private int difficulty;//难度

    private String chooses;//选项列表

    @Column(name = "choose_answer",columnDefinition="char(1)")
    private char chooseAnswer;//正确选项

    @Column(nullable = false,columnDefinition="tinyint default 1")
    private int score;//习题分值

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY,mappedBy = "exercise",targetEntity = ExerciseComment.class)
    private Set<ExerciseComment> exerciseCommentList=new HashSet<>();

    public Exercise() {
    }

}
