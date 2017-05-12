package me.jcala.jmooc.utils;

import me.jcala.jmooc.entity.Exercise;
import me.jcala.jmooc.entity.Lesson;
import me.jcala.jmooc.entity.auxiliary.ExeForm;

import java.util.Set;

public class JmoocBeanUtils {

    public static Set<Lesson> setFileAndExeNum(Set<Lesson> lessons,long crsId){

        for (Lesson lesson:lessons){
            if (lesson.getExerciseList()==null){
                lesson.setExeNum(0);
            }else {
                lesson.setExeNum(lesson.getExerciseList().size());
            }
            lesson.setFileUrl(FileType.FILE.getUrl()+crsId+"/"+lesson.getId());
        }
        return lessons;
    }


    public static Exercise exeFormToBean(ExeForm form,long lesId){
        Exercise exercise=new Exercise(
                form.getTitle(),
                form.getDifficulty(),
                form.getAnswer(),
                1,
                form.getAnalysis(),
                form.getType()
        );
        exercise.setContent(form.getContent());
        Lesson lesson=new Lesson(lesId);
        exercise.setLesson(lesson);
        return exercise;
    }

}
