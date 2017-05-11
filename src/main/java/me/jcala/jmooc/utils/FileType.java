package me.jcala.jmooc.utils;

import lombok.Getter;
import lombok.Setter;

public enum FileType {
    FILE,
    VIDEO
    ;

    @Getter
    @Setter
    private String home;

    @Getter
    @Setter
    private String url;

    FileType() {
    }

    @Override
    public String toString() {
        return "FileType{" +
                "home='" + home + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
