package co.mawen.majiangcommunity.schedule;

import co.mawen.majiangcommunity.cache.HotTagCache;
import co.mawen.majiangcommunity.mapper.QuestionMapper;
import co.mawen.majiangcommunity.model.Question;
import co.mawen.majiangcommunity.model.QuestionExample;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
@Slf4j
public class HotTagSchedule {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 3600000)
    public void getPriority(){
        log.info("The time is {}",new Date());

        int offset=0;
        int limit=8;
        List<Question> questions = new ArrayList<>();
        Map<String,Integer> mapDTO = new HashMap<>();
        Map<String,Integer> commentMap = new HashMap<>();
        while(offset==0 || questions.size()==limit){
            QuestionExample questionExample = new QuestionExample();
            questionExample.setOffset(offset);
            questionExample.setLimit(limit);
            questions=questionMapper.selectByExample(questionExample);
            for(Question question:questions){
                String[] tags = question.getTag().split(",");
                for (String tag : tags) {
                    //计算优先级
                    Integer count = mapDTO.get(tag);
                    if(count==null){
                        mapDTO.put(tag,5+question.getCommentCount());
                    }else {
                        mapDTO.put(tag,count+5+question.getCommentCount());
                    }
                    //计算评论数
                    Integer commentCount = commentMap.get(tag);
                    if(commentCount==null){
                        commentMap.put(tag,question.getCommentCount());
                    }else {
                        commentMap.put(tag,commentCount+question.getCommentCount());
                    }
                }

            }
            offset+=limit;
        }
        hotTagCache.setCommentMap(commentMap);
        hotTagCache.updateHotTags(mapDTO);
        System.out.println(hotTagCache.getHots());

    }
}
