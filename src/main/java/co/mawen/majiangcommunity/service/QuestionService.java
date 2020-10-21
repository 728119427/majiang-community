package co.mawen.majiangcommunity.service;

import co.mawen.majiangcommunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface QuestionService {

    /**
     * 插入发布内容
     * @param question
     */
    void insert(Question question);

    /**
     * 查询所有question
     * @return
     */
    List<Question> list();

    /**
     * 查询所有questiond关联user
     * @return
     */
    List<Question> list_user();
}
