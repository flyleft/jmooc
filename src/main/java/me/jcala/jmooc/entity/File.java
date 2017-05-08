package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "file")
public class File implements Serializable{

    private static final long serialVersionUID = 5459084997270275217L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 20)
    private String type;

    @Column(nullable = false,length = 40)
    private String url;

    public File() {
    }
}
