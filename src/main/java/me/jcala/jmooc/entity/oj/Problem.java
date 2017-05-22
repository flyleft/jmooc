package me.jcala.jmooc.entity.oj;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "problem")
public class Problem {
    @Id
    @Column(name = "problem_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long problem_id;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String input;
    @Column(columnDefinition = "text")
    private String output;
    private int spj;
    @Column(columnDefinition = "text")
    private String hint;
    private String source;
    private int time_limit;
    private int memory_limit;
    private char defunct;
    private int accepted;
    //private int solved;
    private int submit;
    private int submit_user;
    @Column(length = 30)
    private String author;
    @Temporal(TemporalType.DATE)
    private Date create_date;
    @Column(columnDefinition = "FLOAT default 0.00")
    private float difficulty;
    @Column(columnDefinition = "FLOAT default 0.00")
    private float ratio;
    private int contest_id;
    private String oj_name;
    private int oj_pid;
    @Column(columnDefinition = "tinyint(1)")
    private int isvirtual;

    public Problem() {
    }

}
