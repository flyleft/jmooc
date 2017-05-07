package me.jcala.jmooc.exception;

public class NoPageException extends RuntimeException{

    private static final long serialVersionUID = 1121580965407728829L;

    public NoPageException() {
        this("您访问的页面不存在");
    }

    public NoPageException(String message) {
        super(message);
    }
}
