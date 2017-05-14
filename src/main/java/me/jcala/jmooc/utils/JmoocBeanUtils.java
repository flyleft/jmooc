package me.jcala.jmooc.utils;

import me.jcala.jmooc.entity.Exercise;
import me.jcala.jmooc.entity.Lesson;
import me.jcala.jmooc.entity.auxiliary.ExeForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<Character,String> chooseMap=new HashMap<>();
        chooseMap.put('A',form.getA());
        chooseMap.put('B',form.getB());

        if (form.getC()!=null && !form.getC().trim().isEmpty()){
            chooseMap.put('C',form.getD());
        }
        if (form.getD()!=null && !form.getD().trim().isEmpty()){
            chooseMap.put('D',form.getD());
        }

        exercise.setChooses(JsonUtils.instance.toJson(chooseMap));
        exercise.setContent(form.getContent());
        Lesson lesson=new Lesson(lesId);
        exercise.setLesson(lesson);
        return exercise;
    }

    public static List<Exercise> setExeChooseList(List<Exercise> exercises){
        for (Exercise exe:exercises){
             Map<Character,String> chos=JsonUtils.instance.readJsonToExeMap(exe.getChooses());
             exe.setChooseList(chos);
        }
        return exercises;
    }

}
