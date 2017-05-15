package me.jcala.jmooc.ctrl;

import lombok.extern.slf4j.Slf4j;
import me.jcala.jmooc.entity.*;
import me.jcala.jmooc.entity.auxiliary.ChpForm;
import me.jcala.jmooc.entity.auxiliary.ExeForm;
import me.jcala.jmooc.exception.NoPageException;
import me.jcala.jmooc.service.inter.CrsSer;
import me.jcala.jmooc.service.inter.NoticeSer;
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
import java.util.*;

/**
 *  课程管理控制器
 */
@Controller
@Slf4j
public class CrsController {

    private CrsSer crsSer;
    private NoticeSer noticeSer;

    @Autowired
    public CrsController(CrsSer crsSer, NoticeSer noticeSer) {
        this.crsSer = crsSer;
        this.noticeSer = noticeSer;
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
    public String lesMgr(@RequestParam("crs_id") long crsId,
                         @RequestParam("chp_id") long chpId,
                         Model model){
        Set<Lesson> lessons=crsSer.getLessonList(chpId,crsId);

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

    @PostMapping("/user/tea/les_mgr/file")
    public String uploadFile(@RequestParam("crs_id") long crsId,
                             @RequestParam("chp_id") long chpId,
                             @RequestParam("les_id") long lesId,
                             @RequestParam("file") MultipartFile file){

      crsSer.uploadLessonFile(file,crsId,lesId);

      return "redirect:/user/tea/les_mgr?crs_id="+crsId+"&chp_id="+chpId;
    }

   /* @GetMapping("/user/tea/les_mgr/exe")
    public String exeMgr(@RequestParam("crs_id") long crsId,
                         @RequestParam("chp_id") long chpId,
                         @RequestParam("les_id") long lesId){

        return "crs/exe_mgr";

    }*/


    /**
     * 习题管理页面
     */
    @GetMapping("/user/tea/les_mgr/exe")
    public String exeMgr(@RequestParam("crs_id") long crsId,
                         @RequestParam("chp_id") long chpId,
                         @RequestParam("les_id") long lesId,
                         Model model){

        /*List<Exercise> exercises=new ArrayList<>();
        Exercise exercise=new Exercise("明朝时期张居正改革的一条鞭法的主要思想是()",2,'B',5,"这是一道送分题","java");
        Map<Character,String> map=new HashMap<>();
        map.put('A',"面向过程");
        map.put('B',"万物皆数");
        exercise.setChooseList(map);
        exercises.add(exercise);
        exercises.add(exercise);*/

        Set<Exercise> exercises=crsSer.getExerciseByLesId(lesId);
        model.addAttribute("exe",exercises);
        model.addAttribute("crs_id",crsId);
        model.addAttribute("chp_id",chpId);
        model.addAttribute("les_id",lesId);
        return "crs/exe_mgr";

    }


    /**
     * 导入习题
     */
    @PostMapping("/user/tea/les_mgr/exe/add")
    public String exePost(@ModelAttribute("exe") @Valid ExeForm exeForm,
                          BindingResult result,
                          @RequestParam("crs_id") long crsId,
                          @RequestParam("chp_id") long chpId,
                          @RequestParam("les_id") long lesId){

        if (result.hasErrors()) {
            throw new RuntimeException("表单数据不合法");
        }
       crsSer.addExercise(exeForm,lesId);
        return "redirect:/user/tea/les_mgr/exe?crs_id="+crsId+"&chp_id="+chpId+"&les_id="+lesId;
    }

    /**
     * 批量导入习题
     */
    @PostMapping("/user/tea/les_mgr/exe/add_batch")
    public String exePostBatch(@RequestParam("json") String json,
                               @RequestParam("crs_id") long crsId,
                               @RequestParam("chp_id") long chpId,
                               @RequestParam("les_id") long lesId){

        if (json.trim().isEmpty()) {
            throw new RuntimeException("表单数据不合法");
        }
        crsSer.addExerciseBatch(json,lesId);
        return "redirect:/user/tea/les_mgr/exe?crs_id="+crsId+"&chp_id="+chpId+"&les_id="+lesId;
    }

    //---------------------------------------其他-----------------------------------------------

    /**
     * 参与课程
     */
    @GetMapping("/user/all/crs/join/{id}")
    public String joinCrs(@PathVariable("id") long id,HttpServletRequest request){
        long userId=RequestUtils.getUserIdFromReq(request);

        if (userId>=0) {
            crsSer.joinCrs(id, userId);
        }
        return "redirect:/course/"+id;
    }

    /**
     * 发表课程留言
     */
    @PostMapping("/user/all/cmt/crs/add")
    public String CrsCmtPost(@ModelAttribute("cmt") @Valid Notice notice,
                             BindingResult result,HttpServletRequest request){

        if (result.hasErrors()) {
            throw new RuntimeException("表单数据不合法");
        }
       noticeSer.addCrsComment(request,notice);
       return "redirect:/course/"+notice.getFromInfoId();
    }

}
