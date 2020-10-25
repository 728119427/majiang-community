package co.mawen.majiangcommunity.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class Comment implements Serializable {
    private Long id;
    private Long parentId;
    private Integer type;
    private String content;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private User user;
}