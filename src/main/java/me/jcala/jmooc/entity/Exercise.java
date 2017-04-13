package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

//习题表
@Data
@Entity
@Table(name = "exercise_tb")
public class Exercise {

    private long id;//id

    private int type;//类型。1:选择题，2：大题

    private String name;//习题名称

    private String desc;//习题描述

    private int difficulty;//难度

    private List<String> chooses;//选项列表

    private int chooseAnswer;//正确选项

}
