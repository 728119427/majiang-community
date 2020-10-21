package co.mawen.majiangcommunity.service.impl;

import co.mawen.majiangcommunity.mapper.QuestionMapper;
import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Question> list_user() {
        return questionMapper.list_user();
    }
}
