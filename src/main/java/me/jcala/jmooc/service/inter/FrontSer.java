package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.service.FrontSerImpl;
import org.springframework.data.domain.Pageable;

public interface FrontSer {

    FrontSerImpl.ExeFront getExeFront(String param, Pageable pageable);//分页获取习题列表

    FrontSerImpl.CrsFront getCrsFront(String param,Pageable pageable);//分页获取课程列表

    Course getCourse(long crsId);
}
