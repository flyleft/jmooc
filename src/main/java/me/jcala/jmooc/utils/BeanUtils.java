package me.jcala.jmooc.utils;

import me.jcala.jmooc.entity.Chapter;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.form.ChpForm;

public class BeanUtils {

    public static Chapter fromToChapter(ChpForm chpForm){
         return new Chapter(chpForm.getName(),chpForm.getPos(),new Course(chpForm.getCrs_id()));
    }

}
