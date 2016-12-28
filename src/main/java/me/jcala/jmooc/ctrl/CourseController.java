package me.jcala.jmooc.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    @GetMapping("/video")
    public String video(Model model){
        return "video";
    }

    @GetMapping("/courses")
    public String coursesList(){

        return "courses";
    }
}
