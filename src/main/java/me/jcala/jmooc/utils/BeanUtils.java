package me.jcala.jmooc.utils;

import me.jcala.jmooc.entity.Lesson;

import java.util.Set;

public class BeanUtils {

    public static Set<Lesson> setFileAndExeNum(Set<Lesson> lessons){

        for (Lesson lesson:lessons){
            if (lesson.getExerciseList()==null){
                lesson.setExeNum(0);
            }else {
                lesson.setExeNum(lesson.getExerciseList().size());
            }

            if (lesson.getFileList()==null){
                lesson.setFileNum(0);
            }else {
                lesson.setFileNum(lesson.getFileList().size());
            }
        }
        return lessons;
    }

}
