package me.jcala.jmooc.exception;

public class NoAuthoritativeException extends RuntimeException{

    public NoAuthoritativeException() {
    }

    public NoAuthoritativeException(String message) {
        super("没有用户权限："+message);
    }

}
