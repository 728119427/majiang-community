package co.mawen.majiangcommunity.service;

import co.mawen.majiangcommunity.dto.CommentDTO;
import co.mawen.majiangcommunity.model.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CommentService {

    /**
     * 添加评论
     * @param comment
     */
    void insert(Comment comment);

    /**
     * 根据id查询comment
     * @param id
     * @return
     */
    List<CommentDTO> listByQuestionId(Integer id);

    /**
     * pageHelper分页用
     * @param id
     * @return
     */
    List<Comment> listByQuestionId2(Integer id);
}
