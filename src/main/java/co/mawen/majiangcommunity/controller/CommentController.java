package co.mawen.majiangcommunity.controller;

import co.mawen.majiangcommunity.dto.CommentDTO;
import co.mawen.majiangcommunity.dto.ResultDTO;
import co.mawen.majiangcommunity.enums.CommentEnum;
import co.mawen.majiangcommunity.exception.CustomizeErrorCode;
import co.mawen.majiangcommunity.model.Comment;
import co.mawen.majiangcommunity.model.User;
import co.mawen.majiangcommunity.service.CommentService;
import co.mawen.majiangcommunity.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    /**
     * 插入评论
     * @param commentDTO
     * @param request
     * @return
     */
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

    /**
     * 查询二级评论
     * @param id
     * @return
     */
    @GetMapping("/comment/{id}")
    @ResponseBody
    public Object secondComment(@PathVariable("id") Integer  id){
        List<CommentDTO> commentDTOS = commentService.listByParentId(id, CommentEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);

    }
}
