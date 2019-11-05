package edu.xd.bdilab.iotplatform.controller.decive;

/**
 * @ClassName DeviceCode
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/5 18:45
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
public enum DeviceCode {
    GET_DEVICE_SUCCESS("03001", "查看设备成功"),
    ;

    private String code;
    private String message;

    DeviceCode(String code, String message) {
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
