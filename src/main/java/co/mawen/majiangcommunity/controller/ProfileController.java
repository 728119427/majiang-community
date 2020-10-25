package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.model.User;
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
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{section}")
    public String profile(@PathVariable("section") String section, HttpServletRequest request, Model model,
                          @RequestParam(value = "page",defaultValue = "1",required = false) Integer page,
                          @RequestParam(value = "size",defaultValue = "8",required = false) Integer size){
        if("questions".equals(section)){
            model.addAttribute("section",section);
            model.addAttribute("sectionName","我的问题");
        }else if("replies".equals(section)){
            model.addAttribute("section",section);
            model.addAttribute("sectionName","最新回复");
        }
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        /*PaginationDTO pagination = questionService.pagination(Integer.parseInt(String.valueOf(user.getAccountId())), page, size);
        model.addAttribute("pagination",pagination);*/
        PageHelper.startPage(page,size);//设置当前页以及每页显示的数量
        List<Question> questions = questionService.unionList(Integer.parseInt(String.valueOf(user.getAccountId())));
        PageInfo<Question> pageInfo = new PageInfo<>(questions, 5);
        model.addAttribute("pageInfo",pageInfo);

        return "profile";
    }
}
