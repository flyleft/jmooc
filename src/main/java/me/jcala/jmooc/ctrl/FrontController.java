package me.jcala.jmooc.ctrl;

import lombok.extern.slf4j.Slf4j;
import me.jcala.jmooc.conf.WebMvcConfig;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Exercise;
import me.jcala.jmooc.entity.Lesson;
import me.jcala.jmooc.entity.Notice;
import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;
import me.jcala.jmooc.service.FrontSerImpl;
import me.jcala.jmooc.service.inter.CrsSer;
import me.jcala.jmooc.service.inter.FrontSer;
import me.jcala.jmooc.service.inter.NoticeSer;
import me.jcala.jmooc.utils.FileType;
import me.jcala.jmooc.utils.JmoocBeanUtils;
import me.jcala.jmooc.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class FrontController {

    private FrontSer frontSer;
    private CrsSer crsSer;
    private NoticeSer noticeSer;


    @Autowired
    public FrontController(FrontSer frontSer, CrsSer crsSer, NoticeSer noticeSer) {
        this.frontSer = frontSer;
        this.crsSer = crsSer;
        this.noticeSer = noticeSer;
    }

    @GetMapping("/")
    public String index(Model model,HttpServletRequest request){
       RequestUtils.setFrontUserInfo(model,request);
        return "index";
    }


    @GetMapping("/exercise/list")
    public String exercise(@RequestParam(value = "c",required = false) String param,
                           @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                           HttpServletRequest request,
                           Model model){
        long start=System.currentTimeMillis();
        /*Sort.Order numOrder=new Sort.Order(Sort.Direction.DESC,"collNum");
        Sort sort=new Sort(numOrder);
        Pageable pageable1=new PageRequest(pageable.getPageNumber(),pageable.getPageSize(),sort);*/
        RequestUtils.setFrontUserInfo(model,request);
        FrontSerImpl.ExeFront exe= frontSer.getExeFront(param,pageable);

        long count=exe.count/pageable.getPageSize() +1;
        model.addAttribute("exe", JmoocBeanUtils.setExeChooseList(exe.exercises));
        model.addAttribute("count",count);
        model.addAttribute("c",param);
        model.addAttribute("cur",pageable.getPageNumber()+1);
        long end=System.currentTimeMillis();
        log.info("习题列表响应时间为："+(end-start));
      return "exe";
    }

    @GetMapping("/course/list")
    public String coursesList(
            @RequestParam(value = "c",required = false) String param,
            @PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
            HttpServletRequest request,
            Model model){
        long start=System.currentTimeMillis();
        RequestUtils.setFrontUserInfo(model,request);
        FrontSerImpl.CrsFront crs=frontSer.getCrsFront(param,pageable);
        long count=crs.count/pageable.getPageSize() +1;
        model.addAttribute("crs", crs.courses);
        model.addAttribute("count",count);
        model.addAttribute("c",param);
        model.addAttribute("cur",pageable.getPageNumber()+1);
        long end=System.currentTimeMillis();
        log.info("课程列表响应时间为："+(end-start));
        return "crs";
    }

    @GetMapping("/course/{id}")
    public String courseDetail(@PathVariable("id") long id,
                               @RequestParam(value = "c",required = false) String param,
                               HttpServletRequest request,
                               Model model){
        long start=System.currentTimeMillis();
        RequestUtils.setFrontUserInfo(model,request);
        long userId=RequestUtils.getUserIdFromReq(request);
        if (userId>=0 && crsSer.hasJoinCrs(id,userId)){
           model.addAttribute("join",true);
        }else {
            model.addAttribute("join",false);
        }


        Course course=frontSer.getCourse(id);
        if (param==null){
            model.addAttribute("crs",course);
            model.addAttribute("url", FileType.FILE.getUrl());
            return "crs_chp";
        }

        List<Notice> notices=noticeSer.getCrsNotice(id);
        model.addAttribute("crs",course);
        model.addAttribute("cmt",notices);
        long end=System.currentTimeMillis();
        log.info("课程详细响应时间为："+(end-start));
        return "crs_cmt";
    }

    /**
     *  习题详情界面
     */
    @GetMapping("/exercise/{id}")
    public String courseExe(@PathVariable("id") long id, Model model,HttpServletRequest request){
        RequestUtils.setFrontUserInfo(model,request);
        Exercise exercise=frontSer.getExercise(id);

        if (exercise==null) return "redirect:/exercise/list";

        List<Notice> notices=noticeSer.getExeNotice(id);
        model.addAttribute("exe",exercise);
        model.addAttribute("cmt",notices);
        return "exe_detail";
    }

    @GetMapping("/video")
    public String video(@RequestParam("p") String video,Model model){
       if (video==null){
           throw new RuntimeException("请求参数异常");
       }
       String baseUrl=FileType.VIDEO.getUrl();
       model.addAttribute("video",baseUrl+video);
       return "video";
    }

    @GetMapping("/code")
    public String codePage(HttpServletRequest request,Model model){
        RequestUtils.setFrontUserInfo(model,request);
        return "code";
    }


    @PostMapping("/code/run")
    @ResponseBody
    public String codePost(HttpServletRequest request,Model model){
        String[] cmd={WebMvcConfig.OJ_PATH+"Client.exe",Integer.toString(1),Integer.toString(5),WebMvcConfig.OJ_INI_PATH};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
      return "result";
    }
}
