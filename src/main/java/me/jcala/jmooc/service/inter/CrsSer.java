package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.*;
import me.jcala.jmooc.entity.auxiliary.ChpForm;
import me.jcala.jmooc.entity.auxiliary.ExeForm;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface CrsSer {

    long addCourse(Course course);//添加课程

    Set<Course> getCourseList(long userId);//获取某用户的课程列表

    Set<Chapter> getChapterList(long crsId);//获取某课程的章节列表

    Set<Lesson> getLessonList(long chpId,long crsId);//获取某章节的课时列表

    void addChapter(ChpForm chpForm);//添加章节

    void delCourse(long crsId);//删除课程

    void updateLessonVideo(String videoUrl,long lesId);//更新视频地址

    void addLesson(Lesson lesson,long crsId,long chpId);//添加课时

    void uploadLessonFile(MultipartFile file,long crsId, long lesId);//上传课程文件

    void addExercise(ExeForm exeForm,long lesId,long ownerId);//添加习题

    void addExerciseBatch(String json,long lesId,long ownerId);//批量添加习题

    Set<Exercise> getExerciseByLesId(long lesId);//获取某课时下的习题

    void joinCrs(long crsId,long userId);//参加课程

    List<Long> getUserJoinCrs(long userId);//获取用户已经参与的课程id列表

    boolean hasJoinCrs(long crsId,long userId);//判断用户是否已经参与该课程

    void addColExe(long exeId,long userId);//用户收藏习题

    void clearNoticeNum(long userId);//清楚用户的未读信息数量

    Set<Notice> getNoticeList(long userId);//获取用户的消息

    List<Exercise> getColExeList(long userId);//获取用户收藏的习题列表

    List<Course> getJoinCrsList(long userId);//获取参加的课程列表
}