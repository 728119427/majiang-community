package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.cache.HotTagCache;
import co.mawen.majiangcommunity.dto.HotTagDTO;
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
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index( Model model,
                        @RequestParam(value = "page",defaultValue = "1",required = false) Integer page,
                        @RequestParam(value = "size",defaultValue = "10",required = false) Integer size,
                        @RequestParam(value = "search",required = false) String search,
                        @RequestParam(value = "tag",required = false) String tag,
                        @RequestParam(value = "sort",defaultValue = "new") String sort,
                         HttpServletRequest request
    ){

        PaginationDTO<Question> pagination = questionService.pagination(sort,tag,search,page, size);
        List<HotTagDTO> hotTags = hotTagCache.getHots();
        model.addAttribute("pagination",pagination);
            model.addAttribute("tags",hotTags);
            //保留分页的查询条件
            model.addAttribute("search",search);
            model.addAttribute("tag",tag);
            model.addAttribute("sort",sort);
            //保留搜索的条件，回显
            request.getSession().setAttribute("search",search);
             return "index";
    }
}
