package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.dto.PaginationDTO;
import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.service.QuestionService;
import co.mawen.majiangcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index( Model model,
                        @RequestParam(value = "page",defaultValue = "1",required = false) Integer page,
                        @RequestParam(value = "size",defaultValue = "7",required = false) Integer size
    ){

        PaginationDTO<Question> pagination = questionService.pagination(page, size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
