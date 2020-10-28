package co.mawen.majiangcommunity.mapper;

import co.mawen.majiangcommunity.dto.QuestionQueryDTO;
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

    /**
     * 根据条件查找问题总数
     * @param questionQueryDTO
     * @return
     */
    Long countBySearch(QuestionQueryDTO questionQueryDTO);

    /**
     * 根据条件查找问题
     * @param questionQueryDTO
     * @return
     */
    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);

}