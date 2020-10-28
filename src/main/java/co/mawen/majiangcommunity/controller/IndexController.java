package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.dto.PaginationDTO;
import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.model.User;
import co.mawen.majiangcommunity.service.NotificationService;
import co.mawen.majiangcommunity.service.QuestionService;
import co.mawen.majiangcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/")
    public String index( Model model,
                        @RequestParam(value = "page",defaultValue = "1",required = false) Integer page,
                        @RequestParam(value = "size",defaultValue = "8",required = false) Integer size,
                        @RequestParam(value = "search",required = false) String search
    ){

        PaginationDTO<Question> pagination = questionService.pagination(search,page, size);
        model.addAttribute("pagination",pagination);
            model.addAttribute("search",search);

        return "index";
    }
}
