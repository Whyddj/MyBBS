package com.wany.myuestcbbs.entity;


/**
 * 封装返回结果
 */
public class Result {
    /**
     * 状态码
     */
    private Integer status;
    private String message;
    private Object data;


    public Result(ResultCode resultCode, Object data) {
        this.status = resultCode.status();
        this.message = resultCode.message();
        this.data = data;
    }

    public Result(ResultCode resultCode) {
        this.status = resultCode.status();
        this.message = resultCode.message();
    }

    public Result(int code, String message) {
        this.status = code;
        this.message = message;
    }

    public Result(int code, String message, Object data) {
        this.status = code;
        this.message = message;
        this.data = data;
    }

    public static Result SUCCESS(Object data) {
        return new Result(ResultCode.SUCCESS, data);
    }

    public static Result FAIL(ResultCode resultCode) {
        return new Result(resultCode);
    }

    public static Result FAIL(String message) {
        return new Result(400 , message);
    }

    //添加Getter方法，便于SpringBoot将返回对象自动解析为json格式
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}