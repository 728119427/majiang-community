package co.mawen.majiangcommunity.advice;

import co.mawen.majiangcommunity.exception.CustomizeErrorCode;
import co.mawen.majiangcommunity.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    //注意可以定义多个方法，捕获不同的异常
    @ExceptionHandler(CustomizeException.class)
    ModelAndView handleControllerException( Throwable e, Model model) {
        // 错误页面跳转
        model.addAttribute("message", e.getMessage());
        return new ModelAndView("error");
    }

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, Throwable e, Model model) {
        // 错误页面跳转
        model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
        return new ModelAndView("error");
    }


}
