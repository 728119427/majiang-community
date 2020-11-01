package co.mawen.majiangcommunity.service.impl;

import co.mawen.majiangcommunity.dto.PaginationDTO;
import co.mawen.majiangcommunity.dto.QuestionQueryDTO;
import co.mawen.majiangcommunity.enums.SortEnum;
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


    /**
     * 主页分页
     * @param search
     * @param page
     * @param size
     * @param tag
     * @param sort
     * @return
     */
    @Override
    public PaginationDTO pagination(String sort,String tag,String search,Integer page, Integer size) {
        //搜索条件
        if(!StringUtils.isEmpty(search)){
            String[] split = search.split(" ");
            search = Arrays.stream(split).collect(Collectors.joining("|"));
        }
        //标签
        if(!StringUtils.isEmpty(tag)){
            String[] split = tag.split(",");
            tag = Arrays.stream(split).collect(Collectors.joining("|"));
        }



        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);
        questionQueryDTO.setTag(tag);
        //热门条件
        for(SortEnum sortEnum:SortEnum.values()){
            if(sortEnum.name().toLowerCase().equals(sort)){
                questionQueryDTO.setSort(sort);
            }
            if(sortEnum==SortEnum.HOT7){
                questionQueryDTO.setTime(System.currentTimeMillis()-1000L * 60 * 60 * 24 * 7);
            }
            if(sortEnum==SortEnum.HOT30){
                questionQueryDTO.setTime(System.currentTimeMillis()-1000L * 60 * 60 * 24 * 30);
            }
        }

        Long totalCount = questionExtMapper.countBySearch(questionQueryDTO);
        PaginationDTO<Question> paginationDTO = new PaginationDTO<>();
        paginationDTO.setPagination(page,totalCount,size);
        //这里的page还需要经过DTO对象的setPagination()方法进行数据校验
        page=paginationDTO.getPage();
        //查询questions
        Map<String,Object> criteria = new HashMap<>();
        questionQueryDTO.setPage(page);
        questionQueryDTO.setSize(size);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);
   /*     if (questions==null || questions.size()==0){
            questions=new ArrayList<>();
        }*/
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
        if(question==null)throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
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
