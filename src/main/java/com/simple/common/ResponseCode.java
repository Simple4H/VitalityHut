package com.simple.common;

/**
 * Create by S I M P L E on 2017/12/02
 */
public enum ResponseCode {
    SUCCESS(1, "SUCCESS"),
    ERROR(2, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN");

    private final int code;

    private final String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
