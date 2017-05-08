package me.jcala.jmooc.ctrl;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public class FileController {

    @PostMapping("/file/video/upload.do")
    @ResponseBody
    public String uploadAva(HttpServletRequest request, Model model){

        return "admin/info";
    }
}
