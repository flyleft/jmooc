package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.Chapter;
import me.jcala.jmooc.entity.Course;

import java.util.Set;

public interface CrsSer {

    int addCourse(Course course);//添加课程

    Set<Chapter> getChapterList(int crsId);//获取某课程的章节列表
}
