package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.dto.CommentDTO;
import co.mawen.majiangcommunity.dto.ResultDTO;
import co.mawen.majiangcommunity.exception.CustomizeErrorCode;
import co.mawen.majiangcommunity.interceptor.SystemInterceptor;
import co.mawen.majiangcommunity.mapper.CommentMapper;
import co.mawen.majiangcommunity.model.Comment;
import co.mawen.majiangcommunity.model.User;
import co.mawen.majiangcommunity.service.CommentService;
import co.mawen.majiangcommunity.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping("/comment")
    @ResponseBody
    public Object comment(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        if(commentDTO==null || StringUtils.isEmpty(commentDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        commentService.insert(comment);
        return ResultDTO.okOf();

    }
}
