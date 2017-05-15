package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.Notice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 评论相关的service
 */
public interface NoticeSer {

    void addComment(HttpServletRequest request,Notice notice,int type);//添加课程或者习题评论

    List<Notice> getCrsNotice(long fromInfoId);//获取课程评论列表

    List<Notice> getExeNotice(long fromInfoId);//获取课程评论列表
}
