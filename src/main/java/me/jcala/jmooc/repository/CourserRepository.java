package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourserRepository extends CrudRepository<Course,Integer> {

    Course findById(int id);
}
