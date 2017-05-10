package me.jcala.jmooc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "notice")
public class Notice implements Serializable{

    private static final long serialVersionUID = 7510490431006467069L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,columnDefinition="tinyint default 1")
    private int typeAndStatus;//1：系统未读消息；2：系统已读消息；3：用户未读消息；4：用户已读消息

    @OneToOne(optional=false,cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=User.class)
    @JoinColumn(name="id",nullable=false,updatable=false)
    private User fromUser;

    @Column(nullable = false,columnDefinition="text")
    private String content;


    public Notice() {
    }
}
