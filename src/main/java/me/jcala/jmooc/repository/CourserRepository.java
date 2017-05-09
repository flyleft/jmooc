package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CourserRepository extends CrudRepository<Course,Long> {

    Course findById(long id);
}
