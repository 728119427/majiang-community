package co.mawen.majiangcommunity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileDTO implements Serializable {

    private Integer success;
    private String message;
    private String url;
}
