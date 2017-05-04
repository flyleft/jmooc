package me.jcala.jmooc.ctrl;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name","jcala");
      return "index";
    }

    @GetMapping("/user/login")
    public String login(){
        return "login";
    }

}

