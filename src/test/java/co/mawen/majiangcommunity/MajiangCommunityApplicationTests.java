package co.mawen.majiangcommunity;

import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MajiangCommunityApplicationTests {
    @Autowired
    private QuestionService questionService;

    @Test
    void contextLoads() {
        List<Question> questions = questionService.list_user();
        for (Question question : questions) {
            System.out.println(question);
        }
    }

}
