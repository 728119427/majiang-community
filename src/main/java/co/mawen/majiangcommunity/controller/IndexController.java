package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.dto.PaginationDTO;
import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.model.User;
import co.mawen.majiangcommunity.service.QuestionService;
import co.mawen.majiangcommunity.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(value = "page",defaultValue = "1",required = false) Integer page,
                        @RequestParam(value = "size",defaultValue = "7",required = false) Integer size
    ){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String value = cookie.getValue();
                    User user = userService.findByToken(value);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        PaginationDTO<Question> pagination = questionService.pagination(page, size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
