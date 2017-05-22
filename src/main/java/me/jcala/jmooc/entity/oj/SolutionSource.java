package me.jcala.jmooc.entity.oj;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "solution_source")
public class SolutionSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "solution_id")
    private long solution_id;

    @Column(columnDefinition = "text")
    private String source;

    public SolutionSource() {
    }

    public SolutionSource(long solution_id, String source) {
        this.solution_id = solution_id;
        this.source = source;
    }
}
