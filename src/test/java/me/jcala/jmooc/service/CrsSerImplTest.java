package me.jcala.jmooc.service;


import me.jcala.jmooc.BaseTestService;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.repository.CourserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CrsSerImplTest extends BaseTestService {

     @Autowired
     CourserRepository courserRepository;

     @Test
     public void test(){
         Course course=courserRepository.findOne(1L);
         System.out.println(course);
     }

}
