package co.mawen.majiangcommunity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GithubDTO implements Serializable {
    private String client_id;
    private String client_secret;
    private String code;
    private String state;
    private String redirect_uri;

}
