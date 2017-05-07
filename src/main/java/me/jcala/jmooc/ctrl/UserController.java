package me.jcala.jmooc.ctrl;

import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;
import me.jcala.jmooc.exception.NoPageException;
import me.jcala.jmooc.service.inter.UserSer;
import me.jcala.jmooc.utils.CommonUtils;
import me.jcala.jmooc.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserSer userSer;

    @Autowired
    public UserController(UserSer userSer) {
        this.userSer = userSer;
    }

    @GetMapping("/")
    public String index(Model model,HttpServletRequest request){
        UserAuxiliary userAuxiliary= RequestUtils.getUserFromReq(request);
        if (userAuxiliary==null){
           model.addAttribute("type",0);
        }else if (userAuxiliary.getType()==1){
            model.addAttribute("type",1);
            model.addAttribute("name",userAuxiliary.getName());
        }else if (userAuxiliary.getType()==2){
            model.addAttribute("type",2);
            model.addAttribute("name",userAuxiliary.getName());
        }
      return "index";
    }

    @GetMapping("/user/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/user/login.do")
    public String DoLogin(User user, HttpServletRequest request){
        boolean result=userSer.login(user,request);
        if (result){
            return "redirect:/";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/user/logout")
    public String doLogOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("cur_user");
        return "redirect:/";
    }

    @GetMapping("/user/tea/crs_mgr")
    public String courseManagerPage(@RequestParam("do") String operate){
        if (operate==null || CommonUtils.isEmpty(operate)){
           throw new NoPageException();
        }
        if ("add".equals(operate.trim())){
            return "crs_mgr_add";
        }else if ("del".equals(operate.trim())){
            return "crs_mgr_del";
        }else if ("mod".equals(operate.trim())){
            return "crs_mgr_mod";
        }else {
            throw new NoPageException();
        }

    }
}

