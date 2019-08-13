package com.poetry.common;

public enum ResponseStatusEnum {

    FALIURE(-1, "请求失败"),
    SUCCESS(0, "请求成功"),
    ERROR(500, "操作失败");


    private Integer code;
    private String message;

    ResponseStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
