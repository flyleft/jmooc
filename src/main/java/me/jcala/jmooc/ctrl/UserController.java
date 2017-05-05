package me.jcala.jmooc.ctrl;

import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;
import me.jcala.jmooc.service.inter.UserSer;
import me.jcala.jmooc.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

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

}

