package me.jcala.jmooc.repository;

import me.jcala.jmooc.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,String>{


}
