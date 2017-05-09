package me.jcala.jmooc.service.inter;

import me.jcala.jmooc.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserSer {

    boolean login(User user, HttpServletRequest request);

}
