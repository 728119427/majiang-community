package co.mawen.majiangcommunity.service;

import co.mawen.majiangcommunity.dto.CommentDTO;
import co.mawen.majiangcommunity.enums.CommentEnum;
import co.mawen.majiangcommunity.model.Comment;
import co.mawen.majiangcommunity.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CommentService {

    /**
     * 添加评论
     * @param comment
     * @param user
     */
    void insert(Comment comment, User user);

    /**
     * 根据id查询comment
     * @param id
     * @param code
     * @return
     */
    List<CommentDTO> listByParentId(Integer id, CommentEnum commentEnum);

    /**
     * pageHelper分页用
     * @param id
     * @return
     */
    List<Comment> listByQuestionId2(Integer id);
}
