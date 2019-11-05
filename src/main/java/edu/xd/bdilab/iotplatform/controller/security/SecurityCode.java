package edu.xd.bdilab.iotplatform.controller.security;

public enum SecurityCode {
    LOGIN_SUCCESSFUL("01001", "登录成功"),
    LOGIN_FAILED("01002","登录失败"),
    NEED_LOGIN("01003","需要登录"),
    NO_AUTHORITIES("01004","无权访问"),
    LOGOUT_SUCCESSFUL("01005","登出成功")
    ;

    private String code;
    private String message;

    SecurityCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
