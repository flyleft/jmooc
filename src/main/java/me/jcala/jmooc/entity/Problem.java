package me.jcala.jmooc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "problem")
public class Problem {
    /*
    CREATE TABLE `problem` (
  `problem_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` text,
  `input` text,
  `output` text,
  `sample_input` text,
  `sample_output` text,
  `spj` int(11) DEFAULT NULL,
  `hint` text,
  `source` varchar(255) DEFAULT NULL,
  `time_limit` int(11) DEFAULT NULL,
  `memory_limit` int(11) DEFAULT NULL,
  `defunct` char(1) DEFAULT NULL,
  `accepted` int(11) DEFAULT NULL,
  `solved` int(11) DEFAULT NULL,
  `submit` int(11) DEFAULT NULL,
  `submit_user` int(11) DEFAULT NULL,
  `author` varchar(30) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `difficulty` float DEFAULT '0',
  `ratio` float DEFAULT '0',
  `tag` varchar(255) DEFAULT NULL,
  `contest_id` int(11) DEFAULT NULL,
  `oj_name` varchar(255) DEFAULT NULL,
  `oj_pid` int(11) DEFAULT NULL,
  `isvirtual` int(1) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`problem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=gbk;
     */
    @Id
    @Column(name = "problem_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    private String description;
}
