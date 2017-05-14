package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CourserRepository extends CrudRepository<Course,Long> {

    List<Course> findByType(String type,Pageable pageable);

    List<Course> findByDir(String dir,Pageable pageable);

    Page<Course> findAll(Pageable pageable);


    long countByType(String type);

    long countByDir(String dir);

}
