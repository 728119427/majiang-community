package co.mawen.majiangcommunity.dto;

import co.mawen.majiangcommunity.model.User;
import lombok.Data;
import java.io.Serializable;
@Data
public class CommentDTO implements Serializable {

    private Long id;
    private Long parentId;
    private Integer type;
    private String content;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private User user;


}
