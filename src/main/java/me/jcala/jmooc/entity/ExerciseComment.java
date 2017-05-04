package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exercise_comment_tb")
public class ExerciseComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(optional=false,cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=User.class)
    @JoinColumn(name="id",nullable=false,updatable=false)
    private User fromUser;

    @Column(nullable = false,columnDefinition="text")
    private String content;

    public ExerciseComment() {
    }

    public int getId() {
        return id;
    }
}
