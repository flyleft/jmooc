package me.jcala.jmooc.service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Exercise;
import me.jcala.jmooc.repository.CourserRepository;
import me.jcala.jmooc.repository.ExerciseRepository;
import me.jcala.jmooc.service.inter.FrontSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FrontSerImpl implements FrontSer {

    private ExerciseRepository exerciseRepository;

    private CourserRepository courserRepository;

    @Autowired
    public FrontSerImpl(ExerciseRepository exerciseRepository, CourserRepository courserRepository) {
        this.exerciseRepository = exerciseRepository;
        this.courserRepository = courserRepository;
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
      if (param==null)  return new ExeFront(exerciseRepository.findAll(pageable),exerciseRepository.count());

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
           return new ExeFront(exerciseRepository.findAll(pageable),exerciseRepository.count());
        }

    }

    @Override
    public CrsFront getCrsFront(String param,Pageable pageable) {
        if (param==null) return new CrsFront(courserRepository.findAll(pageable),courserRepository.count());
        param=param.trim();
        if ("c".equals(param) || "cp".equals(param) || "java".equals(param)){
            return new CrsFront(courserRepository.findByType(param,pageable),courserRepository.countByType(param));
        }
        return new CrsFront(courserRepository.findByDir(param,pageable),courserRepository.countByDir(param));
    }

    /* @Override
    public List<Course> getCourse(String type, String dir, Pageable pageable) {
        if (type==null){
            if (dir==null){
               return courserRepository.findAll(pageable);
            }else {
               return courserRepository.findByDir(dir,pageable);
            }
        }else {
            if (dir==null){
              return courserRepository.findByType(type,pageable);
            }else {
                return courserRepository.findByTypeAndDir(type,dir,pageable);
            }

        }
    }

    @Override
    public long getCourseCount(String type, String dir) {
        if (type==null){
            if (dir==null){
                return courserRepository.count();
            }else {
                return courserRepository.countByDir(dir);
            }
        }else {
            if (dir==null){
                return courserRepository.countByType(type);
            }else {
                return courserRepository.countByTypeAndDir(type,dir);
            }

        }
    }*/
}
