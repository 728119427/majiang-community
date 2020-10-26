package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.dto.CommentDTO;
import co.mawen.majiangcommunity.exception.CustomizeException;
import co.mawen.majiangcommunity.model.Comment;
import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.service.CommentService;
import co.mawen.majiangcommunity.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id, Model model, HttpServletRequest request,
                           @RequestParam(name = "page",defaultValue = "1",required = false) Integer page,
                           @RequestParam(name = "size",defaultValue = "5",required = false) Integer size){
        String referer = request.getHeader("Referer");
        System.out.println(referer);
        if("http://localhost:8080/".equalsIgnoreCase(referer)){
            questionService.incView(id);
        }
        Question question = questionService.getUnionQuestionById(id);
        //开始分页
        PageHelper.startPage(page,size);
        List<Comment> comments = commentService.listByQuestionId2(id);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments, 4);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("question",question);
        return "question";
    }
}
