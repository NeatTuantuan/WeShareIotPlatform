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

    GET_PRODUCT_SUCCESS("02001","查看产品成功"),
    GET_PRODUCT_FAILURE("02002","查看产品失败"),
    ADD_PRODUCT_SUCCESS("02003","添加产品成功"),
    ADD_PRODUCT_FAILURE("02004","添加产品失败"),
    NO_REQUESTED_PRODUCT("02005","没有该类型的产品"),
    UPDATE_PRODUCT_SUCCESS("02006","更新产品成功"),
    UPDATE_PRODUCT_FAILURE("02007","更新产品失败"),
    DELETE_PRODUCT_SUCCESS("02008", "删除产品成功"),
    DELETE_PRODUCT_FAILURE("02009", "删除产品失败"),
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
