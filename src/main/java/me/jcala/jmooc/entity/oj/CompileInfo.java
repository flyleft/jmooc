package me.jcala.jmooc.entity.oj;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "compile_info")
public class CompileInfo {
    @Id
    @Column(name = "solution_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long solution_id;

    @Column(columnDefinition = "text")
    private String error;
}
