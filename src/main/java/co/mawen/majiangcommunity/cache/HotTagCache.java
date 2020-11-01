package co.mawen.majiangcommunity.cache;

import co.mawen.majiangcommunity.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Component
@Data
public class HotTagCache {
    private Map<String,Integer> commentMap;
    private List<HotTagDTO> hots = new ArrayList<>();

    public void updateHotTags(Map<String,Integer> tags){
        int max=8;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);
        tags.forEach((name,prioritiy)->{
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(prioritiy);
            hotTagDTO.setCommentCount(commentMap.get(name));
            if(priorityQueue.size()<max){
                priorityQueue.add(hotTagDTO);
            }else {
                HotTagDTO minHot = priorityQueue.peek();
                if(hotTagDTO.compareTo(minHot)>0){
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });
        List<HotTagDTO> list = new ArrayList<>();
        HotTagDTO pollTag = priorityQueue.poll();
        while (pollTag!=null){
            list.add(0,pollTag);
            pollTag = priorityQueue.poll();
        }
        hots=list;
    }


}
