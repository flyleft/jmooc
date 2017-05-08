package me.jcala.jmooc.service;

import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.repository.CourserRepository;
import me.jcala.jmooc.service.inter.CrsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrsSerImpl implements CrsSer{

    private CourserRepository courserRepository;

    @Autowired
    public CrsSerImpl(CourserRepository courserRepository) {
        this.courserRepository = courserRepository;
    }

    @Override
    public int addCourse(Course course) {
       return courserRepository.save(course).getId();
    }
}
