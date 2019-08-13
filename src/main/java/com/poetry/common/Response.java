package com.poetry.common;

public class Response<T> {
    private Integer code;
    private String message;
    private T data;

    public Response() {

}

    public Response(T data, ResponseStatusEnum responseStatusEnum) {
        this.code = responseStatusEnum.getCode();
        this.message = responseStatusEnum.getMessage();
        this.data = data;
    }

    public Response(ResponseStatusEnum responseStatusEnum) {
        this.code = responseStatusEnum.getCode();
        this.message = responseStatusEnum.getMessage();
        this.data = null;
    }

    public Response(String message) {
        ResponseStatusEnum responseStatusEnum = ResponseStatusEnum.ERROR;
        responseStatusEnum.setMessage(message);

        this.code = responseStatusEnum.getCode();
        this.message = responseStatusEnum.getMessage();
        this.data = null;
    }

    public static <T> Response<T> ok(T data) {
        return new Response(data, ResponseStatusEnum.SUCCESS);
    }

    public static <T> Response<T> error() {
        return new Response(ResponseStatusEnum.FALIURE);
    }

    public static <T> Response<T> errorWithMsg(String message) {
        return new Response(message);
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
