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

    private String user_id;

    @Temporal(TemporalType.DATE)
    private Date submit_date;

    @Column(columnDefinition = "tinyint(4)")
    private int language;

    @Column(columnDefinition = "tinyint(4)")
    private int verdict;

    @Column(columnDefinition = "text")
    private String result;

    public Solution() {
    }
}
