package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.exception.CustomizeException;
import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id, Model model){
        questionService.incView(id);
        Question question = questionService.getUnionQuestionById(id);
        model.addAttribute("question",question);
        return "question";
    }
}
