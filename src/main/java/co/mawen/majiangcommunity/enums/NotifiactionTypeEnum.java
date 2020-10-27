package co.mawen.majiangcommunity.enums;

public enum  NotifiactionTypeEnum {

    REPLY_QUESTION(1,"回复了你的问题"),
    REPLY_COMMENT(2,"回复了你的评论");


    private Integer type;
    private String name;

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotifiactionTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String nameOfType(Integer type){
        NotifiactionTypeEnum[] values = NotifiactionTypeEnum.values();
        for (NotifiactionTypeEnum value : values) {
            if(value.getType()==type) return value.getName();
        }
        return "";
    }
}
