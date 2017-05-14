package me.jcala.jmooc.ctrl;

import lombok.extern.slf4j.Slf4j;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Exercise;
import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;
import me.jcala.jmooc.service.FrontSerImpl;
import me.jcala.jmooc.service.inter.FrontSer;
import me.jcala.jmooc.utils.JmoocBeanUtils;
import me.jcala.jmooc.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

    @GetMapping("/exercise/list")
    public String exercise(@RequestParam(value = "c",required = false) String param,
                           @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                           Model model){
        /*Sort.Order numOrder=new Sort.Order(Sort.Direction.DESC,"collNum");
        Sort sort=new Sort(numOrder);
        Pageable pageable1=new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),sort);*/

        FrontSerImpl.ExeFront exe= frontSer.getExeFront(param,pageable);

        model.addAttribute("exe", JmoocBeanUtils.setExeChooseList(exe.exercises));
        model.addAttribute("count",exe.count);

      return "exe";
    }

    @GetMapping("/course/list")
    public String coursesList(
            @RequestParam(value = "c",required = false) String param,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
            Model model){

        FrontSerImpl.CrsFront crs=frontSer.getCrsFront(param,pageable);
        model.addAttribute("crs", crs.courses);
        model.addAttribute("count",crs.count);
        return "crs";
    }

    @GetMapping("/course/{id}")
    public String courseDetail(@PathVariable("id") long id){
        return "crs_detail";
    }

    @GetMapping("/course/{id}/participate")
    public String courseParticipate(@PathVariable("id") long id){

       return "redirect:/course/"+id;
    }
}
