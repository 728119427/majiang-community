package co.mawen.majiangcommunity.mapper;

import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.model.QuestionExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface QuestionMapper {
    long countByExample(QuestionExample example);

    int deleteByExample(QuestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    List<Question> selectByExampleWithBLOBs(QuestionExample example);

    List<Question> selectByExample(QuestionExample example);

    Question selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExampleWithBLOBs(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKeyWithBLOBs(Question record);

    int updateByPrimaryKey(Question record);

    //自己添加的方法============================================================

    /**
     * 查询所有question关联user
     * @return
     */
    List<Question> list_user(Map<String,Object> criteria);


    /**
     * 查询所有question关联user
     * @return
     */
    List<Question> list_creator(Map<String,Object> criteria);

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
}