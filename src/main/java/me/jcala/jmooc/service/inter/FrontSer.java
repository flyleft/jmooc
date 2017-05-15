package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Exercise;
import me.jcala.jmooc.entity.Lesson;
import me.jcala.jmooc.entity.Notice;
import me.jcala.jmooc.service.FrontSerImpl;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FrontSer {

    FrontSerImpl.ExeFront getExeFront(String param, Pageable pageable);//分页获取习题列表

    FrontSerImpl.CrsFront getCrsFront(String param,Pageable pageable);//分页获取课程列表

    Course getCourse(long crsId);//获取课程的全部信息

    Exercise getExercise(long exeId);//获取习题全部信息

}
