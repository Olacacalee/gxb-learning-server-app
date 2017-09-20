package com.gxb.modules.core.web.vo;

/**
 * time : 15/12/26.
 * auth : jqwang
 *
 * ErrorCode 4位
 * 2xxx 用户相关
 * 3xxx 课程相关  39xx 资源相关 其他自定义
 * 4xxx 班次信息相关  41
 * 5xxx 租户相关
 * 6xxx 学生学习相关
 * 。。。
 */

public enum ErrorCode {
    //2xxx 用户相关
    USER_NOT_EXISTS(2001, "用户不存在"),
    MOBILE_EXISTS(2002, "手机号码已存在"),
    EAMIL_EXISTS(2003, "邮箱已存在"),

    //3xxx 课程相关  39xx 资源相关 其他自定义
    COURSE_NOT_EXISTS(3001,"课程不存在");

    private final int code;
    private final String message;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }



}
