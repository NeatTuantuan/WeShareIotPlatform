package edu.xd.bdilab.iotplatform.controller.product;
/**
 * @ClassName ProductCode
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/5 18:46
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
public enum ProductCode {

    ADD_PRODUCT_SUCCESS("02005","添加产品成功"),
    ;

    private String code;
    private String message;

    ProductCode(String code, String message) {
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
