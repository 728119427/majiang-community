package co.mawen.majiangcommunity.mapper;

import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.model.QuestionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionExtMapper {

    /**
     * 增加阅读数
     * @param question
     */
    void incView(Question question);


    /**
     * 增加评论数
     * @param question
     */
    void incCommentCount(Question question);

    /**
     * 查询相关问题
     * @return
     */
    List<Question> selectRelatedQues(Question question);

}