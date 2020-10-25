package co.mawen.majiangcommunity.enums;

public enum CommentEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer code;

    public Integer getCode() {
        return code;
    }

    CommentEnum(Integer code) {
        this.code = code;
    }

    public static boolean isCommentType(Integer type){
        CommentEnum[] values = CommentEnum.values();
        for (CommentEnum value : values) {
            if(value.code.equals(type)){
                return true;
            }
        }
        return false;
    }
}
