package me.jcala.jmooc.service;

import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Exercise;
import me.jcala.jmooc.repository.CourserRepository;
import me.jcala.jmooc.repository.ExerciseRepository;
import me.jcala.jmooc.service.inter.FrontSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FrontSerImpl implements FrontSer {

    private ExerciseRepository exerciseRepository;

    private CourserRepository courserRepository;

    @Autowired
    public FrontSerImpl(ExerciseRepository exerciseRepository, CourserRepository courserRepository) {
        this.exerciseRepository = exerciseRepository;
        this.courserRepository = courserRepository;
    }

    @Override
    public List<Exercise> getExercise(String type, Integer diff,Pageable pageable) {
      if (type==null){
          if (diff==null){
              return exerciseRepository.findAll(pageable);
          }else {
              return exerciseRepository.findByDifficulty(diff,pageable);
          }

      }else {
          if (diff==null){
              return exerciseRepository.findByType(type,pageable);
          }else {
              return exerciseRepository.findByTypeAndDifficulty(type,diff,pageable);
          }

      }

    }

    @Override
    public long getExerciseCount(String type, Integer diff) {
        if (type==null){
            if (diff==null){
                return exerciseRepository.count();
            }else {
                return exerciseRepository.countExerciseByDifficulty(diff);
            }
        }else {
            if (diff==null){
                return exerciseRepository.countExerciseByType(type);
            }else {
                return exerciseRepository.countExerciseByTypeAndDifficulty(type,diff);
            }
        }
    }

    @Override
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
    }
}
