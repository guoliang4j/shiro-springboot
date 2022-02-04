package com.gl.shirospringboot.utils;

/**
 * @author guoliang
 */
public enum LoginType {
    //密码登录
    PASSWORD("password"),
    //免密登录
    NOPASSWORD("nopassword");

    /**
     * 状态值
     */
    private String code;

    private LoginType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
