package me.jcala.jmooc.utils;

import lombok.Getter;
import lombok.Setter;

public enum FileType {
    FILE("",""),
    VIDEO("","")
    ;

    @Getter
    @Setter
    private String home;

    @Getter
    @Setter
    private String url;

    FileType(String home, String url) {
        this.home = home;
        this.url = url;
    }

}
