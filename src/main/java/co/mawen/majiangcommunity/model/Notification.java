package co.mawen.majiangcommunity.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Notification implements Serializable {
    private Long id;
    private Long notifier;
    private Long receiver;
    private Long outerid;
    private Integer type;
    private Long gmtCreate;
    private Integer status;
    private String notifierName;
    private String outerTitle;


}