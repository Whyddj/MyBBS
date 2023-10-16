package com.wany.myuestcbbs.entity;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200, "success"),
    FAIL(400, "fail"),
    UNAUTHORIZED(401, "Invalid User"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");


    private final int status;
    private final String message;

    ResultCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int status() {
        return status;
    }

    public String message() {
        return message;
    }
}
