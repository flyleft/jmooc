package me.jcala.jmooc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "solution_source")
public class SolutionSource {
    @Id
    @Column(name = "solution_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long solution_id;
    @Column(columnDefinition = "text")
    private String source;
}
