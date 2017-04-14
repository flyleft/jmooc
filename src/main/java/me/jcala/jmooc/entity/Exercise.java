package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

//习题表
@Data
@Entity
@Table(name = "exercise_tb")
public class Exercise {

    @Id
    private long id;//id

    private int type;//类型。1:选择题，2：大题

    private String name;//习题名称

    private String desc;//习题描述

    private int difficulty;//难度

    private List<String> chooses;//选项列表

    private int chooseAnswer;//正确选项

    public Exercise() {
    }

    public Exercise(long id, int type, String name,
                    String desc, int difficulty,
                    List<String> chooses, int chooseAnswer) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.desc = desc;
        this.difficulty = difficulty;
        this.chooses = chooses;
        this.chooseAnswer = chooseAnswer;
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

    public List<String> getChooses() {
        return chooses;
    }

    public void setChooses(List<String> chooses) {
        this.chooses = chooses;
    }

    public int getChooseAnswer() {
        return chooseAnswer;
    }

    public void setChooseAnswer(int chooseAnswer) {
        this.chooseAnswer = chooseAnswer;
    }
}
