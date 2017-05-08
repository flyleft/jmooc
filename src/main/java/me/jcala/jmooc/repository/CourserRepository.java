package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CourserRepository extends CrudRepository<Course,Integer> {

    @Query("select chapters from Course where id =?1")
    Course findChapterListById(int id);
}
