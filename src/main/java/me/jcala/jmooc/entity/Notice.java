package me.jcala.jmooc.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "notice")
public class Notice implements Serializable{

    private static final long serialVersionUID = 7510490431006467069L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id")
    private User owner;

    private String fromUserName;

    private long fromUserId;

    @NotBlank(message = "评论源自信息")
    private String fromInfo;

    @Column(nullable = false,columnDefinition="tinyint(1) default 1")//1：表示课程留言；2：表示习题留言
    private int type;

    @Min(0)
    private long fromInfoId;//课程id或者习题id

    @Temporal(TemporalType.DATE)
    @Column(nullable = false,name = "create_at")
    private Date createdAt;

    @NotBlank(message = "评论内容")
    @Column(nullable = false,columnDefinition="text")
    private String content;

    @Transient
    @Min(0)
    private long ownerId;

    public Notice() {
    }
}
