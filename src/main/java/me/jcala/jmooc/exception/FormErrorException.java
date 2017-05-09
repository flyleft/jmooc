package me.jcala.jmooc.exception;

public class FormErrorException extends RuntimeException{

    public FormErrorException() {
    }

    public FormErrorException(String message) {
        super("表单数据不合法："+message);
    }

}
