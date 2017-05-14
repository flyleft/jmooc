package me.jcala.jmooc.ctrl;

import lombok.extern.slf4j.Slf4j;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Exercise;
import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;
import me.jcala.jmooc.service.inter.FrontSer;
import me.jcala.jmooc.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class FrontController {

    private FrontSer frontSer;

    @Autowired
    public FrontController(FrontSer frontSer) {
        this.frontSer = frontSer;
    }

    @GetMapping("/")
    public String index(Model model,HttpServletRequest request){
        UserAuxiliary userAuxiliary= RequestUtils.getUserAuxiliaryFromReq(request);
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

    @GetMapping("/video")
    public String video(Model model){
        return "video";
    }

    @GetMapping("/course/list")
    public String coursesList(
            @RequestParam(value = "t",required = false) String type,
            @RequestParam(value = "d",required = false) String dir,
            Pageable pageable,
            Model model){
        List<Course> courses=frontSer.getCourse(type,dir,pageable);
        long count=frontSer.getCourseCount(type,dir);

//        model.addAttribute("crs",courses);
//        model.addAttribute("count",count);
        log.info(count+"");
        return "courses";
    }

    @GetMapping("/exercise/list")
    public String exercise(@RequestParam(value = "t",required = false) String type,
                           @RequestParam(value = "d",required = false) Integer diff,
                           @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                           Model model){
        /*Sort.Order numOrder=new Sort.Order(Sort.Direction.DESC,"collNum");
        Sort sort=new Sort(numOrder);
        Pageable pageable1=new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),sort);*/

        List<Exercise> exercises= frontSer.getExercise(type,diff,pageable);
        long count= frontSer.getExerciseCount(type,diff);

//        model.addAttribute("exe",exercises);
//        model.addAttribute("count",count);

        log.info(count+"");
      return "exercises";
    }
}
