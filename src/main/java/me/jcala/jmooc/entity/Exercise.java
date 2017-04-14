package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;
//习题表
@Data
@Entity
@Table(name = "exercise_tb")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//id

    @Column(nullable = false,length = 40)
    private String name;//习题名称

    @Column(nullable = false,columnDefinition="tinyint default 1")
    private int type;//类型。1:选择题，2：大题

    @Column(columnDefinition="TEXT")
    private String desc;//习题描述

    @Column(nullable = false,columnDefinition="tinyint default 1")//1,2,3,4四个难度等级
    private int difficulty;//难度

    private String chooses;//选项列表

    @Column(name = "choose_answer")
    private int chooseAnswer;//正确选项

    public Exercise() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getChooseAnswer() {
        return chooseAnswer;
    }

    public void setChooseAnswer(int chooseAnswer) {
        this.chooseAnswer = chooseAnswer;
    }

    public String getChooses() {
        return chooses;
    }

    public void setChooses(String chooses) {
        this.chooses = chooses;
    }
}
