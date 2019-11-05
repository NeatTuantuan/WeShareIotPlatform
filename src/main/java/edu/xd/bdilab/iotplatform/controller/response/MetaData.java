package edu.xd.bdilab.iotplatform.controller.response;

/**
 * @ClassName MetaData
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/5 9:55
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
public class MetaData {
    /**
     * 是否成功标识符
     */
    private Boolean success;
    /**
     * 返回码
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;

    public MetaData() {
    }

    public MetaData(Boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
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
