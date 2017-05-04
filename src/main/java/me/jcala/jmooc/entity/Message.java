package me.jcala.jmooc.entity;

import javax.persistence.*;

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,columnDefinition="tinyint default 1")
    private int typeAndStatus;//1：系统未读消息；2：系统已读消息；3：用户未读消息；4：用户已读消息

    @OneToOne(optional=false,cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=User.class)
    @JoinColumn(name="id",nullable=false,updatable=false)
    private User fromUser;

    @Column(nullable = false,columnDefinition="text")
    private String content;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeAndStatus() {
        return typeAndStatus;
    }

    public void setTypeAndStatus(int typeAndStatus) {
        this.typeAndStatus = typeAndStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}
