package me.jcala.jmooc.service;

import me.jcala.jmooc.entity.Chapter;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Lesson;
import me.jcala.jmooc.entity.form.ChpForm;
import me.jcala.jmooc.repository.ChapterRepository;
import me.jcala.jmooc.repository.CourserRepository;
import me.jcala.jmooc.service.inter.CrsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CrsSerImpl implements CrsSer{

    private CourserRepository courserRepository;

    private ChapterRepository chapterRepository;

    @Autowired
    public CrsSerImpl(CourserRepository courserRepository, ChapterRepository chapterRepository) {
        this.courserRepository = courserRepository;
        this.chapterRepository = chapterRepository;
    }

    @Override
    public long addCourse(Course course) {
       return courserRepository.save(course).getId();
    }

    @Override
    public Set<Chapter> getChapterList(long crsId) {
        Course course=courserRepository.findById(crsId);
        if (course==null){
            return new HashSet<>();
        }
        return course.getChapters();
    }

    @Override
    public Set<Lesson> getLessonList(long chpId) {
        Chapter chapter=chapterRepository.findById(chpId);
        if (chapter==null){
            return new HashSet<>();
        }
        return chapter.getLessons();
    }

    @Override
    public void addChapter(ChpForm chpForm) {
        Chapter chapter=new Chapter();
        chapter.setName(chpForm.getName());
        chapter.setPos(chpForm.getPos());
        chapter.setCourse(new Course(chpForm.getCrs_id()));
        chapterRepository.save(chapter);
    }
}
