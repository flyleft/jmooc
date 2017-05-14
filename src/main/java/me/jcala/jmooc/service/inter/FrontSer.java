package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Exercise;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FrontSer {

    List<Exercise> getExercise(String type, Integer diff, Pageable pageable);

    long getExerciseCount(String type, Integer diff);

    List<Course> getCourse(String type,String dir,Pageable pageable);

    long getCourseCount(String type,String dir);
}
