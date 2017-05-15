package me.jcala.jmooc.service;

import me.jcala.jmooc.entity.*;
import me.jcala.jmooc.entity.auxiliary.ChpForm;
import me.jcala.jmooc.entity.auxiliary.ExeForm;
import me.jcala.jmooc.repository.*;
import me.jcala.jmooc.service.inter.CrsSer;
import me.jcala.jmooc.utils.FileType;
import me.jcala.jmooc.utils.FileUtils;
import me.jcala.jmooc.utils.JmoocBeanUtils;
import me.jcala.jmooc.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class CrsSerImpl implements CrsSer{

    private static final Logger logger= LoggerFactory.getLogger(CrsSer.class);

    private CourserRepository courserRepository;

    private ChapterRepository chapterRepository;

    private UserRepository userRepository;

    private LessonRepository lessonRepository;

    private ExerciseRepository exerciseRepository;

    @Autowired
    public CrsSerImpl(CourserRepository courserRepository, ChapterRepository chapterRepository,
                      UserRepository userRepository, LessonRepository lessonRepository,
                      ExerciseRepository exerciseRepository) {
        this.courserRepository = courserRepository;
        this.chapterRepository = chapterRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public long addCourse(Course course) {
        course.setCreatedAt(new Date());
       return courserRepository.save(course).getId();
    }

    @Override
    public Set<Chapter> getChapterList(long crsId) {
        Course course=courserRepository.findOne(crsId);
        if (course==null){
            return new HashSet<>();
        }
        return course.getChapters();
    }

    @Override
    public Set<Lesson> getLessonList(long chpId,long crsId) {
        Chapter chapter=chapterRepository.findOne(chpId);
        if (chapter==null){
            return new HashSet<>();
        }
        return JmoocBeanUtils.setFileAndExeNum(chapter.getLessons(),crsId);
    }

    @Override
    public void addChapter(ChpForm chpForm) {
        logger.info("chpForm:"+chpForm.toString());
        Chapter chapter=new Chapter();
        chapter.setName(chpForm.getName());
        chapter.setPos(chpForm.getPos());
        chapter.setCourse(new Course(chpForm.getCrs_id()));
        chapterRepository.save(chapter);
    }

    @Override
    public Set<Course> getCourseList(long userId) {
        User user=userRepository.findOne(userId);
        if (user==null) return new HashSet<>();
        return user.getCourses();
    }

    @Override
    public void delCourse(long crsId) {
        courserRepository.delete(crsId);
    }

    @Override
    public void updateLessonVideo(String videoUrl, long lesId) {
         lessonRepository.updateVideoById(videoUrl,lesId);
    }

    @Override
    public void addLesson(Lesson lesson,long crsId,long chapId) {

        String url = FileUtils.uploadVideo(lesson.getVf(), FileType.VIDEO,crsId);

        if (url==null) return;

        logger.debug("上传视频地址: "+url);

        lesson.setVideo(url);
        lesson.setChapter(new Chapter(chapId));
        lessonRepository.save(lesson);
    }

    @Override
    public void uploadLessonFile(MultipartFile file, long crsId, long lesId) {

        String url = FileUtils.uploadFile(file, FileType.FILE,crsId,lesId);

        if (url==null) return;

        logger.debug("上传文件地址: "+url);

        Lesson lesson=lessonRepository.findOne(lesId);
        if (lesson!=null){
            List<String> fileList= JsonUtils.instance.readJsonToStringList(lesson.getUpFileList());
            fileList.add(url);
            String newFileList=JsonUtils.instance.toJson(fileList);
            lessonRepository.updateFileById(newFileList,lesId);
        }
    }

    @Override
    public void addExercise(ExeForm exeForm, long lesId,long ownerId) {
        Exercise exercise=JmoocBeanUtils.exeFormToBean(exeForm,lesId);
        exercise.setOwnerId(ownerId);
        exerciseRepository.save(exercise);
    }

    @Override
    public Set<Exercise> getExerciseByLesId(long lesId) {
        Lesson lesson=lessonRepository.findOne(lesId);
        if (lesson==null){
            return new HashSet<>();
        }
        for (Exercise exercise:lesson.getExerciseList()){
            Map<Character,String> map=JsonUtils.instance.readJsonToExeMap(exercise.getChooses());
            exercise.setChooseList(map);
        }
        return lesson.getExerciseList();
    }

    @Override
    public void addExerciseBatch(String json, long lesId,long ownerId) {
        Set<Exercise> exercises=JsonUtils.instance.readJsonToExeSet(json);
        for (Exercise exercise:exercises){
            exercise.setLesson(new Lesson(lesId));
            exercise.setOwnerId(ownerId);
        }
        exerciseRepository.save(exercises);
    }

    @CacheEvict(value = "user_join_courses",key = "#userId")
    @Override
    public void joinCrs(long crsId, long userId) {

       List<Long> joinList=getUserJoinCrs(userId);

       for (long id:joinList){
           if (id==crsId) return;
       }


       joinList.add(crsId);

        String newJoinCrs=JsonUtils.instance.toJson(joinList);
       userRepository.updateJoinCourses(newJoinCrs,userId);
    }

    @Cacheable(value = "user_join_courses",key = "#userId",unless="#result == null")
    @Override
    public List<Long> getUserJoinCrs(long userId) {
        User user=userRepository.findOne(userId);
        if (user==null) return null;
        return JsonUtils.instance.readJsonToSLongList(user.getJoinCourses());
    }

    @Override
    public boolean hasJoinCrs(long crsId, long userId) {
        List<Long> joinCrs=getUserJoinCrs(userId);
        for (long id: joinCrs){
            if (crsId==id) return true;
        }
        return false;
    }

    @Override
    public void addColExe(long exeId,long userId) {
        User user=userRepository.findOne(userId);
        if (user==null) return;
        List<Long> exeColList=JsonUtils.instance.readJsonToSLongList(user.getColExercises());
        for (long id:exeColList){
            if (id==exeId) return;
        }
        exeColList.add(exeId);
        String newJson=JsonUtils.instance.toJson(exeColList);
        userRepository.updateColExe(newJson,userId);
    }

    @Override
    public void clearNoticeNum(long userId) {
        userRepository.clearNoticeNum(userId);
    }

    @Override
    public Set<Notice> getNoticeList(long userId) {
        User user=userRepository.findOne(userId);
        if (user==null) return new HashSet<>();
        return user.getNotices();
    }
}
