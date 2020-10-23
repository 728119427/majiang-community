package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.model.User;
import co.mawen.majiangcommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(String title, String description, String tag,
                            Model model, HttpServletRequest request,
                            Integer id){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            model.addAttribute("error","请先登录！");
            return "publish";
        }

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(StringUtils.isEmpty(title)){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }

        if(StringUtils.isEmpty(description)){
            model.addAttribute("error","问题描述不能为空");
            return "publish";
        }

        if(StringUtils.isEmpty(tag)){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        Question question = new Question();
        question.setCreator(Integer.parseInt(String.valueOf(user.getAccountId())));
        question.setTag(tag);
        question.setDescription(description);
        question.setTitle(title);
        question.setId(id);
        questionService.insert(question);

        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id, Model model){
        Question question = questionService.getUnionQuestionById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",id);
        return "publish";
    }
}
