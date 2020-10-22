package co.mawen.majiangcommunity.service;

import co.mawen.majiangcommunity.dto.PaginationDTO;
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
//   List<Question> list_user();

    /**
     * 主页分页功能
     * @param page
     * @param size
     * @return
     */
    PaginationDTO pagination(Integer page,Integer size);

    /**
     * 个人页面分页功能
     * @param creator
     * @param page
     * @param size
     * @return
     */
    PaginationDTO pagination(Integer creator,Integer page,Integer size);
}
