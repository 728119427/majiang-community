package co.mawen.majiangcommunity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GithubUser implements Serializable {
    private String id;
    private String name;
    private String bio;//描述
    private String avatarUrl;

}
