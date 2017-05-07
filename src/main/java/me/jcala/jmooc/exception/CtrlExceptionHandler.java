package me.jcala.jmooc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理类
 */
@ControllerAdvice(basePackages = { "me.jcala.jmooc.ctrl" })
@Slf4j
class CtrlExceptionHandler {


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandlerProd(Exception e, Model model) {
        log.info(e.getLocalizedMessage());
        model.addAttribute("status",500);
        model.addAttribute("title","对不起，服务器开小差了。");
        model.addAttribute("error",e.getLocalizedMessage());
        return new ModelAndView("error");
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoPageException.class)
    public String noPageError(Exception e, Model model) {
        log.info(e.getLocalizedMessage());
        model.addAttribute("status",404);
        model.addAttribute("title","对不起，您访问的页面不存在");
        model.addAttribute("error",e.getLocalizedMessage());
        return "error";
    }

}
