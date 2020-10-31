package co.mawen.majiangcommunity.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2019/8/2.
 */
@Data
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;
    private Integer questionNum;
    private Integer commentCount;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }

    public Integer getQuestionNum() {
        return (priority-commentCount)/5;
    }
}
