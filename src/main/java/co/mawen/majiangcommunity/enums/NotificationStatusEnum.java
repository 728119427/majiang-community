package co.mawen.majiangcommunity.enums;

public enum NotificationStatusEnum {
    //已读
    READ(1),
    //未读
    UNREAD(0);

    private Integer status;

    NotificationStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
