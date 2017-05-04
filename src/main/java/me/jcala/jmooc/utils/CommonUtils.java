package me.jcala.jmooc.utils;

public class CommonUtils {

    public static boolean isEmpty(String...strings){
        for (String string:strings){
            if (string==null || string.trim().isEmpty()){
                return true;
            }
        }
        return false;
    }
}
