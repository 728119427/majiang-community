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
        questionMapper.insert(question);
    }

    @Override
    public List<Question> list() {

        return questionMapper.list();
    }


    @Override
    public PaginationDTO pagination(Integer page, Integer size) {
        Integer totalCount = questionMapper.count();
        PaginationDTO<Question> questionPaginationDTO = new PaginationDTO<>();
        questionPaginationDTO.setPagination(page,totalCount,size);
        //这里的page还需要经过DTO对象的setPagination()方法进行数据校验
        page=questionPaginationDTO.getPage();
        //查询questions
        Map<String,Object> criteria = new HashMap<>();
        criteria.put("offset",(page - 1) * size);
        criteria.put("size",size);
        List<Question> questions = questionMapper.list_user(criteria);
        questionPaginationDTO.setDataList(questions);
        return questionPaginationDTO;
    }
}
