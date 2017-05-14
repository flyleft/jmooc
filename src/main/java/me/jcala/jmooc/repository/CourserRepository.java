package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CourserRepository extends CrudRepository<Course,Long> {

    List<Course> findByTypeAndDir(String type, String dir, Pageable pageable);

    List<Course> findByType(String type,Pageable pageable);

    List<Course> findByDir(String dir,Pageable pageable);

    List<Course> findAll(Pageable pageable);


    long countByType(String type);

    long countByDir(String dir);

    long countByTypeAndDir(String type,String dir);

}
