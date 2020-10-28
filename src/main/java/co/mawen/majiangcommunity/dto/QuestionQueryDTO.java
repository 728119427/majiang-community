package co.mawen.majiangcommunity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionQueryDTO implements Serializable {

    private String search;
    private Integer page;
    private Integer size;


    public Integer getOffset() {
        return (page-1)*size;
    }
}
