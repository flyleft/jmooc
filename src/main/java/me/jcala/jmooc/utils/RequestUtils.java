package me.jcala.jmooc.utils;

import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static UserAuxiliary getUserAuxiliaryFromReq(HttpServletRequest request){
        Object obj = request.getSession().getAttribute("cur_user");
        if (obj == null || !(obj instanceof UserAuxiliary)) {
            return null;
        }
        return (UserAuxiliary) obj;
    }

    public static long getUserIdFromReq(HttpServletRequest request){
        Object obj = request.getSession().getAttribute("cur_user");
        if (obj == null || !(obj instanceof UserAuxiliary)) {
            return -1;
        }
        return ((UserAuxiliary) obj).getId();
    }

    public static User getUserFromReq(HttpServletRequest request){
        Object obj = request.getSession().getAttribute("cur_user");
        if (obj == null || !(obj instanceof UserAuxiliary)) {
            return null;
        }
        UserAuxiliary auxiliary=(UserAuxiliary)obj;
        return new User(auxiliary.getId(),auxiliary.getName());
    }

    public static void setFrontUserInfo(Model model, HttpServletRequest request){
        UserAuxiliary userAuxiliary= RequestUtils.getUserAuxiliaryFromReq(request);
        if (userAuxiliary==null){
            model.addAttribute("type",0);
        }else if (userAuxiliary.getType()==1){
            model.addAttribute("type",1);
            model.addAttribute("name",userAuxiliary.getName());
            model.addAttribute("num",userAuxiliary.getNoticeNum());
        }else if (userAuxiliary.getType()==2){
            model.addAttribute("type",2);
            model.addAttribute("name",userAuxiliary.getName());
            model.addAttribute("num",userAuxiliary.getNoticeNum());
        }
    }
}
