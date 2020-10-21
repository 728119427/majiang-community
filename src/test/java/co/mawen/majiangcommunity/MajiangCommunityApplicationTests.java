package co.mawen.majiangcommunity;

import co.mawen.majiangcommunity.mapper.QuestionMapper;
import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MajiangCommunityApplicationTests {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;

    @Test
    void contextLoads() {
        Map<String,Object> criteria = new HashMap<>();
        criteria.put("offset",5);
        criteria.put("size",5);
        List<Question> questions = questionMapper.list_user(criteria);
        for (Question question : questions) {
            System.out.println(question);
        }
    }

}
