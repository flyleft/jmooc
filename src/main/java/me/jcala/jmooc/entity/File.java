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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
