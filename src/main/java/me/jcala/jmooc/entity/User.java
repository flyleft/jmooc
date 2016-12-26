package me.jcala.jmooc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private long userId;

    private String name;

    private String password;

    @Column(name = "user_type")
    private int userType;//0:student  1:teacher 2:admin

}
