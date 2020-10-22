package co.mawen.majiangcommunity.service.impl;

import co.mawen.majiangcommunity.dto.PaginationDTO;
import co.mawen.majiangcommunity.mapper.QuestionMapper;
import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void insert(Question question) {
        Integer id = question.getId();
        if(id==null){
            question.setGmtCreate(System.currentTimeMillis());
            questionMapper.insert(question);
        }else {
            questionMapper.update(question);
        }

    }

    @Override
    public List<Question> list() {
        return questionMapper.list();
    }


    @Override
    public PaginationDTO pagination(Integer page, Integer size) {
        Integer totalCount = questionMapper.count();
        PaginationDTO<Question> paginationDTO = new PaginationDTO<>();
        paginationDTO.setPagination(page,totalCount,size);
        //这里的page还需要经过DTO对象的setPagination()方法进行数据校验
        page=paginationDTO.getPage();
        //查询questions
        Map<String,Object> criteria = new HashMap<>();
        criteria.put("offset",(page - 1) * size);
        criteria.put("size",size);
        List<Question> questions = questionMapper.list_user(criteria);
        paginationDTO.setDataList(questions);
        return paginationDTO;
    }

    @Override
    public PaginationDTO pagination(Integer creator, Integer page, Integer size) {
        Integer totalCount = questionMapper.countByCreator(creator);
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

    @Override
    public Question getUnionQuestionById(Integer id) {
        return questionMapper.getUnionQuestionById(id);
    }
}
