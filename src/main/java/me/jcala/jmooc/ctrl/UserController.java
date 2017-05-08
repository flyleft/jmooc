package me.jcala.jmooc.ctrl;

import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;
import me.jcala.jmooc.service.inter.UserSer;
import me.jcala.jmooc.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

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

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login.do")
    public String DoLogin(User user, HttpServletRequest request){
        boolean result= false;
        try {
            result = userSer.login(user,request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result){
            return "redirect:/";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/logout")
    public String doLogOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute("cur_user");
        return "redirect:/";
    }

}

