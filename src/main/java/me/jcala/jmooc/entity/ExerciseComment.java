package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "exercise_comment")
public class ExerciseComment implements Serializable{

    private static final long serialVersionUID = 3456978781637596326L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.REFRESH},fetch=FetchType.LAZY,targetEntity = Exercise.class)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @OneToOne(optional=false,cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=User.class)
    @JoinColumn(name="id",nullable=false,updatable=false)
    private User fromUser;

    @Column(nullable = false,columnDefinition="text")
    private String content;

    public ExerciseComment() {
    }

    public long getId() {
        return id;
    }
}
