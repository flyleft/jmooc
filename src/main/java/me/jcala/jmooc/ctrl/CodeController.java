package me.jcala.jmooc.ctrl;

import lombok.extern.slf4j.Slf4j;
import me.jcala.jmooc.entity.Solution;
import me.jcala.jmooc.entity.auxiliary.CodeForm;
import me.jcala.jmooc.entity.auxiliary.CodeResult;
import me.jcala.jmooc.service.inter.CodeSer;
import me.jcala.jmooc.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Slf4j
public class CodeController {

    private CodeSer codeSer;

    @Autowired
    public CodeController(CodeSer codeSer) {
        this.codeSer = codeSer;
    }

    @GetMapping("/code")
    public String codePage(@RequestParam(value = "lau",required = false) String lau,
                           @RequestParam(value = "id",required = false) Long solutionId,
                           HttpServletRequest request,
                           Model model){
        RequestUtils.setFrontUserInfo(model,request);
        if (solutionId!=null){
            model.addAttribute("so_id",solutionId);
        }
        if (lau==null || lau.isEmpty()){
            return "code/c";
        }else if ("java".equals(lau)){
            return "code/java";
        }else {
            return "code/cpp";
        }
    }


    @PostMapping("/code/run")
    public String codePost(@ModelAttribute("code") @Valid CodeForm codeForm,
                           BindingResult result,
                           HttpServletRequest request,
                           Model model){

        long userId=RequestUtils.getUserIdFromReq(request);
        if (userId<0){
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            throw new RuntimeException("表单数据不合法");
        }

        Solution solution=codeSer.submitCode(userId+"",codeForm);
        if (solution.getLanguage()==3){
            return "redirect:/code?lau=cpp&id="+solution.getSolution_id();
        }else if (solution.getLanguage()==5){
            return "redirect:/code?lau=java&id="+solution.getSolution_id();
        }else {
            return "redirect:/code?id="+solution.getSolution_id();
        }
    }

    @GetMapping("/code/result")
    @ResponseBody
    public CodeResult getResult(@RequestParam("id") long solution_id, HttpServletRequest request){
        log.info("收到请求，time:"+System.currentTimeMillis());
        return codeSer.getResult(solution_id);
    }
}
