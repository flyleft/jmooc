package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.Chapter;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Lesson;
import me.jcala.jmooc.entity.auxiliary.ChpForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface CrsSer {

    long addCourse(Course course);//添加课程

    Set<Course> getCourseList(long userId);//获取某用户的课程列表

    Set<Chapter> getChapterList(long crsId);//获取某课程的章节列表

    Set<Lesson> getLessonList(long chpId);//获取某章节的课时列表

    void addChapter(ChpForm chpForm);//添加章节

    void delCourse(long crsId);//删除课程

    void updateLessonVideo(String videoUrl,long lesId);//更新视频地址

    void addLesson(Lesson lesson,long crsId,long chpId);//添加课时

    void uploadLessonFile(MultipartFile file,long crsId, long lesId);
}