package co.mawen.majiangcommunity.mapper;

import co.mawen.majiangcommunity.model.Comment;
import co.mawen.majiangcommunity.model.CommentExample;
import co.mawen.majiangcommunity.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentExtMapper {

    /**
     * 通过问题id查找评论
     * @param id
     * @return
     */
    List<Comment> unionListByQuestionId(Integer id);

    /**
     * 增加评论数
     * @param comment
     */
    void incCommentCount(Comment comment);
}