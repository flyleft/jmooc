package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.Chapter;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Lesson;
import me.jcala.jmooc.entity.form.ChpForm;

import java.util.Set;

public interface CrsSer {

    long addCourse(Course course);//添加课程

    Set<Course> getCourseList(long userId);//获取某用户的课程列表

    Set<Chapter> getChapterList(long crsId);//获取某课程的章节列表

    Set<Lesson> getLessonList(long chpId);//获取某章节的课时列表

    void addChapter(ChpForm chpForm);
}
