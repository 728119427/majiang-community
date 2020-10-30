package co.mawen.majiangcommunity.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionQueryDTO implements Serializable {
    //搜索条件
    private String search;
    //分页信息
    private Integer page;
    private Integer size;
    //标签搜索
    private String tag;
    //xx天最热
    private String sort;
    private Long time;



    public Integer getOffset() {
        return (page-1)*size;
    }
}
