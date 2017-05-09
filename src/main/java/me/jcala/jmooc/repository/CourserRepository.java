package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourserRepository extends CrudRepository<Course,Integer> {

    @Query
    Course findById(int id);
}
