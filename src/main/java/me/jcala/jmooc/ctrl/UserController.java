package me.jcala.jmooc.ctrl;

import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.service.inter.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private UserSer userSer;

    @Autowired
    public UserController(UserSer userSer) {
        this.userSer = userSer;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name","jcala");
      return "index";
    }

    @GetMapping("/user/login")
    public String loginPage(){
        return "login";
    }

    @ResponseBody
    @PostMapping("/user/login.do")
    public String DoLogin(User user, HttpServletRequest request){
        return user.toString();
        /*boolean result=userSer.login(user,request);
        if (result){
            return "redirect:/";
        }
        return "redirect:/user/login";*/
    }

}

