package me.jcala.jmooc.ctrl;

import me.jcala.jmooc.entity.Chapter;
import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.Lesson;
import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.entity.auxiliary.ChpForm;
import me.jcala.jmooc.exception.NoPageException;
import me.jcala.jmooc.service.inter.CrsSer;
import me.jcala.jmooc.utils.CommonUtils;
import me.jcala.jmooc.utils.FileType;
import me.jcala.jmooc.utils.FileUtils;
import me.jcala.jmooc.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 *  课程管理控制器
 */
@Controller
public class CrsController {

    private static final Logger logger= LoggerFactory.getLogger(CrsController.class);

    private CrsSer crsSer;

    @Autowired
    public CrsController(CrsSer crsSer) {
        this.crsSer = crsSer;
    }

    //-------------------------------------------------课程管理相关---------------------------------------------

    /**
     *  课程管理界面
     *  do为add返回添加课程页面
     *  do为md返回课程管理界面
     */
    @GetMapping("/user/tea/crs_mgr")
    public String courseManagerPage(@RequestParam("do") String operate,HttpServletRequest request,Model model){

        if (operate==null || CommonUtils.isEmpty(operate)){
            throw new NoPageException();
        }
        if ("add".equals(operate.trim())){
            return "crs/crs_mgr_add";
        }else if ("mod".equals(operate.trim())){
            long userId=RequestUtils.getUserIdFromReq(request);
            Set<Course> courses=crsSer.getCourseList(userId);
            model.addAttribute("courses",courses);
            return "crs/crs_mgr_mod";
        }else {
            throw new NoPageException();
        }

    }

    /**
     * 课程添加请求
     */
    @PostMapping("/user/tea/crs_mgr/add")
    public String addCourse(@ModelAttribute("course") @Valid Course course,
                            BindingResult result,
                            HttpServletRequest request){

        if (result.hasErrors()) {
            return "crs/crs_mgr_add";
        }

        User user= RequestUtils.getUserFromReq(request);
        course.setUser(user);

        long crsId=crsSer.addCourse(course);
        return "redirect:/user/tea/chp_mgr?crs_id="+crsId;
    }

    /**
     * 课程删除请求
     */
    @GetMapping("/user/tea/crs_mgr/del")
    public String delCourse(@RequestParam("crs_id") long crsId){
        crsSer.delCourse(crsId);
        return "redirect:/user/tea/crs_mgr?do=md";
    }

    //-------------------------------------------------章节管理相关---------------------------------------------
    /**
     *  返回章节列表界面
     */
    @GetMapping("/user/tea/chp_mgr")
    public String chpMgr(@RequestParam("crs_id") long crsId,Model model){
        Set<Chapter> chapters=crsSer.getChapterList(crsId);
        if (chapters!=null){
            model.addAttribute("chps",chapters);
            model.addAttribute("crs",crsId);
            model.addAttribute("pos",chapters.size()+1);
        }
        return "crs/chp_mgr";
    }


    /**
     * 添加章节操作
     */
    @PostMapping("/user/tea/chp_mgr/add")
    public String chpPost(@ModelAttribute("chapter") @Valid ChpForm chpForm,BindingResult result){
        if (result.hasErrors()) {
            throw new RuntimeException("表单数据不合法");
        }
        crsSer.addChapter(chpForm);
        return "redirect:/user/tea/chp_mgr?crs_id="+chpForm.getCrs_id();
    }

    //-------------------------------------------------课时管理相关---------------------------------------------

    /**
     * 返回课时列表界面
     */
    @GetMapping("/user/tea/les_mgr")
    public String LesMgr(@RequestParam("crs_id") long crsId,
                         @RequestParam("chp_id") long chpId,
                         Model model){
        Set<Lesson> lessons=crsSer.getLessonList(chpId);

        if (lessons!=null){
            model.addAttribute("les",lessons);
            model.addAttribute("crs_id",crsId);
            model.addAttribute("chp_id",chpId);
            model.addAttribute("pos",lessons.size()+1);
        }
       return "crs/les_mgr";
    }

    /**
     * 添加课时
     */
    @PostMapping("/user/tea/les_mgr/add")
    public String lesPost(@ModelAttribute("lesson") @Valid Lesson lesson,
                          BindingResult result,
                          @RequestParam("crs_id") long crsId,
                          @RequestParam("chp_id") long chpId){

        if (result.hasErrors()) {
            logger.info("name:"+lesson.getName());
            throw new RuntimeException("表单数据不合法");
        }

        crsSer.addLesson(lesson,crsId,chpId);

        return "redirect:/user/tea/les_mgr?crs_id="+crsId+"&chp_id="+chpId;
    }

    /**
     * 修改课时的视频
     */
    @PostMapping("/user/tea/les_mgr/video")
    public String modifyVideo(@RequestParam("crs_id") long crsId,
                              @RequestParam("chp_id") long chpId,
                              @RequestParam("les_id") long lesId,
                              @RequestParam("video") MultipartFile file){


        String url = FileUtils.uploadVideo(file, FileType.VIDEO,crsId);
        if (url!=null){
            crsSer.updateLessonVideo(url,lesId);
        }

        return "redirect:/user/tea/les_mgr?crs_id="+crsId+"&chp_id="+chpId;
    }


}
