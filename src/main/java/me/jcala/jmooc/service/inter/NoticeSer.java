package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.Notice;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论相关的service
 */
public interface NoticeSer {

    void addCrsComment(HttpServletRequest request,Notice notice);//添加课程评论

    void addExeComment(HttpServletRequest request,Notice notice);//添加课程评论
}
