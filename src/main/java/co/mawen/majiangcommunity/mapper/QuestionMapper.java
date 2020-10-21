package co.mawen.majiangcommunity.mapper;

import co.mawen.majiangcommunity.model.Question;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface QuestionMapper {

    /**
     * 插入发布内容
     * @param question
     */
    void insert(Question question);

    /**
     * 查所有question
     * @return
     */
    List<Question> list();

    /**
     * 查询所有question关联user
     * @return
     */
    List<Question> list_user();
}
