package me.jcala.jmooc.service;

import me.jcala.jmooc.entity.Chapter;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Lesson;
import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.entity.auxiliary.ChpForm;
import me.jcala.jmooc.repository.ChapterRepository;
import me.jcala.jmooc.repository.CourserRepository;
import me.jcala.jmooc.repository.LessonRepository;
import me.jcala.jmooc.repository.UserRepository;
import me.jcala.jmooc.service.inter.CrsSer;
import me.jcala.jmooc.utils.FileType;
import me.jcala.jmooc.utils.FileUtils;
import me.jcala.jmooc.utils.JmoocBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CrsSerImpl implements CrsSer{

    private static final Logger logger= LoggerFactory.getLogger(CrsSer.class);

    private CourserRepository courserRepository;

    private ChapterRepository chapterRepository;

    private UserRepository userRepository;

    private LessonRepository lessonRepository;

    @Autowired
    public CrsSerImpl(CourserRepository courserRepository, ChapterRepository chapterRepository,
                      UserRepository userRepository, LessonRepository lessonRepository) {
        this.courserRepository = courserRepository;
        this.chapterRepository = chapterRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public long addCourse(Course course) {
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
    public Set<Lesson> getLessonList(long chpId) {
        Chapter chapter=chapterRepository.findOne(chpId);
        if (chapter==null){
            return new HashSet<>();
        }
        return JmoocBeanUtils.setFileAndExeNum(chapter.getLessons());
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

        logger.debug("上传文件地址: "+url);

        lesson.setVideo(url);
        lesson.setChapter(new Chapter(chapId));
        lessonRepository.save(lesson);
    }
}
