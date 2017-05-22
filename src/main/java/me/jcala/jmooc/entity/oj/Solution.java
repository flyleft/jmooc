package me.jcala.jmooc.entity.oj;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "solution")
public class Solution {
    @Id
    @Column(name = "solution_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long solution_id;
    @Column(nullable = false)
    private long problem_id;
    private String username;
    @Temporal(TemporalType.DATE)
    private Date submit_date;
    private int memory;
    private int time;
    private int code_length;
    @Column(columnDefinition = "tinyint(4)")
    private int language;
    @Column(columnDefinition = "tinyint(4)")
    private int verdict;
    private long contest_id;
    private int testcase;

    public Solution() {
    }
}
