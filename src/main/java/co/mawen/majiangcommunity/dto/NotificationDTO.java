package co.mawen.majiangcommunity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationDTO implements Serializable {
    private Long id;
    private Long notifier;
    private Long outerid;
    private Integer type;
    private String typeName;
    private Long gmtCreate;
    private Integer status;
    private String notifierName;
    private String outerTitle;

}
