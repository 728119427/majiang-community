package co.mawen.majiangcommunity.service.impl;

import co.mawen.majiangcommunity.dto.PaginationDTO;
import co.mawen.majiangcommunity.exception.CustomizeErrorCode;
import co.mawen.majiangcommunity.exception.CustomizeException;
import co.mawen.majiangcommunity.mapper.QuestionExtMapper;
import co.mawen.majiangcommunity.mapper.QuestionMapper;
import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.model.QuestionExample;
import co.mawen.majiangcommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Override
    public void insert(Question question) {
        Integer id = question.getId();
        if(id==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.insertSelective(question);
        }else {
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.updateByPrimaryKeySelective(question);
        }

    }

    @Override
    public List<Question> list() {
      return questionMapper.selectByExampleWithBLOBs(new QuestionExample());
    }


    @Override
    public PaginationDTO pagination(Integer page, Integer size) {
        Long totalCount = questionMapper.countByExample(new QuestionExample());
        PaginationDTO<Question> paginationDTO = new PaginationDTO<>();
        paginationDTO.setPagination(page,totalCount,size);
        //这里的page还需要经过DTO对象的setPagination()方法进行数据校验
        page=paginationDTO.getPage();
        //查询questions
        Map<String,Object> criteria = new HashMap<>();
        criteria.put("offset",(page - 1) * size);
        criteria.put("size",size);
        List<Question> questions = questionMapper.list_user(criteria);
        if (questions==null || questions.size()==0){
            questions=new ArrayList<>();
        }
        paginationDTO.setDataList(questions);
        return paginationDTO;
    }

    /**
     * 问题详情页，自定义
     * @param creator
     * @param page
     * @param size
     * @return
     */
    @Override
    public PaginationDTO pagination(Integer creator, Integer page, Integer size) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(creator);
        long totalCount = questionMapper.countByExample(questionExample);
        PaginationDTO<Question> paginationDTO = new PaginationDTO<>();
        paginationDTO.setPagination(page,totalCount,size);
        //这里的page还需要经过DTO对象的setPagination()方法进行数据校验
        page=paginationDTO.getPage();
        //查询questions
        Map<String,Object> criteria = new HashMap<>();
        criteria.put("offset",(page - 1) * size>=0?(page-1)*size:0);
        criteria.put("size",size);
        criteria.put("creator",creator);
        List<Question> questions = questionMapper.list_creator(criteria);
        paginationDTO.setDataList(questions);
        return paginationDTO;
    }

    /**
     * 问题详情页，pageHelper
     * @param id
     * @return
     */
    @Override
    public Question getUnionQuestionById(Integer id) {
        Question question = questionMapper.getUnionQuestionById(id);
        //if(question==null)throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND.getMessage());
        return question;
    }

    /**
     * 测试pageHelper
     * @param creator
     * @return
     */
    @Override
    public List<Question> unionList(Integer creator) {
        return questionMapper.unionList(creator);
    }

    @Override
    public void incView(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question==null)throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    @Override
    public List<Question> selectRelatedQues(Question question) {
        String tag = question.getTag();
        String[] tags = tag.split(",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question _question = new Question();
        _question.setTag(regexpTag);
        _question.setId(question.getId());
        return questionExtMapper.selectRelatedQues(_question);
    }
}
