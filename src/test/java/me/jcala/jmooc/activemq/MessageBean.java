package me.jcala.jmooc.activemq;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageBean implements Serializable{
    private Integer age;
    private String name;
}
