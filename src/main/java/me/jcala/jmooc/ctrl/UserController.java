package me.jcala.jmooc.ctrl;

import me.jcala.jmooc.entity.Course;
import me.jcala.jmooc.entity.User;
import me.jcala.jmooc.entity.auxiliary.UserAuxiliary;
import me.jcala.jmooc.exception.NoPageException;
import me.jcala.jmooc.service.inter.CrsSer;
import me.jcala.jmooc.service.inter.UserSer;
import me.jcala.jmooc.utils.CommonUtils;
import me.jcala.jmooc.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    private UserSer userSer;
    private CrsSer crsSer;

    @Autowired
    public UserController(UserSer userSer, CrsSer crsSer) {
        this.userSer = userSer;
        this.crsSer = crsSer;
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

    @GetMapping("/user/tea/crs_mgr")
    public String courseManagerPage(@RequestParam("do") String operate){
        if (operate==null || CommonUtils.isEmpty(operate)){
           throw new NoPageException();
        }
        if ("add".equals(operate.trim())){
            return "crs_mgr_add";
        }else if ("del".equals(operate.trim())){
            return "crs_mgr_del";
        }else if ("mod".equals(operate.trim())){
            return "crs_mgr_mod";
        }else {
            throw new NoPageException();
        }

    }

    @PostMapping("/user/tea/crs_mgr/add.do")
    public String addCourse(@ModelAttribute("course") @Valid Course course,
                            BindingResult result,
                            HttpServletRequest request){

        int userId=RequestUtils.getUserIdFromReq(request);

        if (result.hasErrors()) {
            return "crs_mgr_add";
        }
         course.setUserId(userId);
        int crsId=crsSer.addCourse(course);
        return "redirect:/user/tea/chp_mgr?crs_id="+crsId;
    }

    @GetMapping("/user/tea/chp_mgr")
    public String chpMgr(@RequestParam("crs_id") int crsId,Model model){

        return "chp_mgr";
    }

}

