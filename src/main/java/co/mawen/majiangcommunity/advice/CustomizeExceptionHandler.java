package co.mawen.majiangcommunity.advice;

import co.mawen.majiangcommunity.dto.ResultDTO;
import co.mawen.majiangcommunity.exception.CustomizeErrorCode;
import co.mawen.majiangcommunity.exception.CustomizeException;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    //注意可以定义多个方法，捕获不同的异常
    @ExceptionHandler(CustomizeException.class)
    ModelAndView handleControllerException(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json;charset=utf-8".contains(contentType.toLowerCase())){

            try {
                //返回json
                CustomizeException customizeException = (CustomizeException) e;
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(ResultDTO.errorOf(customizeException)));
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        }else {
            // 错误页面跳转
            model.addAttribute("message", e.getMessage());
            return new ModelAndView("error");
        }

    }

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, Throwable e, Model model) {
        // 错误页面跳转
        model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
        return new ModelAndView("error");
    }


}
