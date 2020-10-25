package co.mawen.majiangcommunity.dto;

import co.mawen.majiangcommunity.exception.CustomizeErrorCode;
import co.mawen.majiangcommunity.exception.CustomizeException;

import java.io.Serializable;

public class ResultDTO implements Serializable {
    private String message;
    private Integer code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResultDTO() {
    }

    public ResultDTO(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public static ResultDTO errorOf(Integer code, String message){
       return new ResultDTO(message,code);
   }

   public static ResultDTO errorOf(CustomizeErrorCode customizeErrorCode){
        return errorOf(customizeErrorCode.getCode(),customizeErrorCode.getMessage());
   }

   public static ResultDTO errorOf(CustomizeException customizeException){
        return errorOf(customizeException.getCode(),customizeException.getMessage());
   }

   public static ResultDTO okOf(){
        return new ResultDTO("success",200);
   }


}
