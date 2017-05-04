package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "file_tb")
public class File {

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
