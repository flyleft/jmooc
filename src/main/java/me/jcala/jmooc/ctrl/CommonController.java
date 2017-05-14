package me.jcala.jmooc.ctrl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {

    @GetMapping("/video")
    public String video(Model model){
        return "video";
    }

    @GetMapping("/course/list")
    public String coursesList(
            @RequestParam(value = "t",required = false) String type,
            @RequestParam(value = "d",required = false) String dir,
            Pageable pageable){

        return "courses";
    }

    @GetMapping("/exercise/list")
    public String exercise(@RequestParam(value = "t",required = false) String type,
                           @RequestParam(value = "d",required = false) Integer diff,
                           @PageableDefault( sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        Sort.Order numOrder=new Sort.Order(Sort.Direction.DESC,"collNum");
        Sort sort=new Sort(numOrder);
        Pageable pageable1=new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),sort);
      return "exercises";
    }
}
