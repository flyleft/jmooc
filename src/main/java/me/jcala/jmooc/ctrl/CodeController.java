package me.jcala.jmooc.ctrl;

import me.jcala.jmooc.entity.auxiliary.CodeForm;
import me.jcala.jmooc.service.inter.CodeSer;
import me.jcala.jmooc.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class CodeController {

    private CodeSer codeSer;

    @Autowired
    public CodeController(CodeSer codeSer) {
        this.codeSer = codeSer;
    }

    @GetMapping("/code")
    public String codePage(@RequestParam(value = "lau",required = false) String lau,
                           HttpServletRequest request,
                           Model model){
        RequestUtils.setFrontUserInfo(model,request);
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

        codeSer.submitCode(userId+"",codeForm);
        return "redirect:/code";
    }
}
