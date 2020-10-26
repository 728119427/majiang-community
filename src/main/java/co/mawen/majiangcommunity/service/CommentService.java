package co.mawen.majiangcommunity.service;

import co.mawen.majiangcommunity.dto.CommentDTO;
import co.mawen.majiangcommunity.enums.CommentEnum;
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
