package me.jcala.jmooc.service;

import lombok.extern.slf4j.Slf4j;
import me.jcala.jmooc.entity.Chapter;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Exercise;
import me.jcala.jmooc.entity.Lesson;
import me.jcala.jmooc.repository.ChapterRepository;
import me.jcala.jmooc.repository.CourserRepository;
import me.jcala.jmooc.repository.ExerciseRepository;
import me.jcala.jmooc.service.inter.FrontSer;
import me.jcala.jmooc.utils.JmoocBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class FrontSerImpl implements FrontSer {

    private ExerciseRepository exerciseRepository;

    private CourserRepository courserRepository;

    private ChapterRepository chapterRepository;

    @Autowired
    public FrontSerImpl(ExerciseRepository exerciseRepository,
                        CourserRepository courserRepository,
                        ChapterRepository chapterRepository) {
        this.exerciseRepository = exerciseRepository;
        this.courserRepository = courserRepository;
        this.chapterRepository = chapterRepository;
    }

    public static class ExeFront{
        public final List<Exercise> exercises;
        public final long count;

        public ExeFront(List<Exercise> exercises, long count) {
            this.exercises = exercises;
            this.count = count;
        }
    }

    public static class CrsFront{
        public final List<Course> courses;
        public final long count;

        public CrsFront(List<Course> courses, long count) {
            this.courses = courses;
            this.count = count;
        }
    }

    @Override
    public ExeFront getExeFront(String param,Pageable pageable) {
      if (param==null)  return new ExeFront(exerciseRepository.findAll(pageable).getContent(),exerciseRepository.count());

        param=param.trim();
        if ("c".equals(param) || "cp".equals(param) || "java".equals(param)){
            return new ExeFront(exerciseRepository.findByType(param,pageable),exerciseRepository.countExerciseByType(param));
        }
        try {
            int diff=Integer.parseInt(param);
            return new ExeFront(exerciseRepository.findByDifficulty(diff,pageable),exerciseRepository.countExerciseByDifficulty(diff));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            log.info("获取习题列表参数错误，param: "+param);
           return new ExeFront(exerciseRepository.findAll(pageable).getContent(),exerciseRepository.count());
        }

    }

    @Override
    public CrsFront getCrsFront(String param,Pageable pageable) {
        if (param==null) return new CrsFront(courserRepository.findAll(pageable).getContent(),courserRepository.count());
        param=param.trim();
        if ("c".equals(param) || "cp".equals(param) || "java".equals(param)){
            return new CrsFront(courserRepository.findByType(param,pageable),courserRepository.countByType(param));
        }
        return new CrsFront(courserRepository.findByDir(param,pageable),courserRepository.countByDir(param));
    }

    @Override
    public Course getCourse(long crsId) {
        return courserRepository.findOne(crsId);
    }

    @Override
    public Exercise getExercise(long exeId) {
        Exercise exercise = exerciseRepository.findOne(exeId);
        if (exercise!=null){
            JmoocBeanUtils.setOneExeChooseList(exercise);
        }
        return exercise;
    }
}
