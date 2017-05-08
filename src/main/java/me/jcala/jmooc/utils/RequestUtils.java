package me.jcala.jmooc.utils;

import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static UserAuxiliary getUserFromReq(HttpServletRequest request){
        Object obj = request.getSession().getAttribute("cur_user");
        if (obj == null || !(obj instanceof UserAuxiliary)) {
            return null;
        }
        return (UserAuxiliary) obj;
    }

    public static int getUserIdFromReq(HttpServletRequest request){
        Object obj = request.getSession().getAttribute("cur_user");
        if (obj == null || !(obj instanceof UserAuxiliary)) {
            return -1;
        }
        return ((UserAuxiliary) obj).getId();
    }
}
