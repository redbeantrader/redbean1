package com.management.property.common;


public enum ResponseCode {//返回消息
    USERNAME_NOT_EMPTY(1,"用户名不能为空"),
    PASSWORD_NOT_EMPTY(2,"密码不能为空"),
    USERNAME_NOT_EXISTS(3,"用户名不存在"),
    PASSWORD_ERROR(4,"密码错误"),
    USERNAME_EXISTED(5,"用户名已存在"),
    PHONE_NOT_EMPTY(6,"手机号不能为空")

    ;

    private  int code;
    private String msg;

    ResponseCode(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
