package co.mawen.majiangcommunity.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private Long accountId;
    private String name;
    private String token;
    private String bio;
    private String avatarUrl;
    private Long gmtCreate;
    private Long gmtModified;

}
