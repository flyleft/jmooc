package me.jcala.jmooc.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,columnDefinition="tinyint default 1")
    private int typeAndStatus;//1：系统未读消息；2：系统已读消息；3：用户未读消息；4：用户已读消息

    @Column(nullable = false,columnDefinition="varchar(40) default 'jmooc'")
    private String fromUsername;

    @Column(nullable = false,columnDefinition="varchar(40) default '/img/default.png'")
    private String fromUserAvatar;

    @Column(nullable = false,columnDefinition="text")
    private String content;

    public Message() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTypeAndStatus() {
        return typeAndStatus;
    }

    public void setTypeAndStatus(int typeAndStatus) {
        this.typeAndStatus = typeAndStatus;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getFromUserAvatar() {
        return fromUserAvatar;
    }

    public void setFromUserAvatar(String fromUserAvatar) {
        this.fromUserAvatar = fromUserAvatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
