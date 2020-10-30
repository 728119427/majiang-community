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
     * @param search
     * @param tag
     * @param tag xx天最热
     * @return
     */
    PaginationDTO pagination(String sort,String tag,String search,Integer page,Integer size);

    /**
     * 个人页面分页功能
     * @param creator
     * @param page
     * @param size
     * @return
     */
    PaginationDTO pagination(Integer creator,Integer page,Integer size);

    /**
     * 测试pageHelper分页
     * @return
     */
    List<Question> unionList(Integer creator);

    /**
     * 根据id查找问题关联user
     * @param id
     * @return
     */
    Question getUnionQuestionById(Integer id);

    /**
     * 增加阅读数
     * @param id
     */
    void incView(Integer id);

    /**
     * 查询相关问题
     * @return
     */
    List<Question> selectRelatedQues(Question question);
}
