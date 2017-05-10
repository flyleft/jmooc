package me.jcala.jmooc.utils;

import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;

import java.util.Calendar;
import java.util.Date;

public class CommonUtils {

    public static boolean isEmpty(String...strings){
        for (String string:strings){
            if (string==null || string.trim().isEmpty()){
                return true;
            }
        }
        return false;
    }

    public static boolean notEmpty(String...strings){
        for (String string:strings){
            if (string==null || string.trim().isEmpty()){
                return false;
            }
        }
        return true;
    }

    public static UserAuxiliary User2Auxiliary(User user){

        return new UserAuxiliary(
                user.getId(),
                user.getName(),
                user.getRole(),
                user.getAvatarUrl()
        );

    }
}
