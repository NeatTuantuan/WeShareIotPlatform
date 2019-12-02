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
    GET_DEVICE_FAILURE("03002", "查看设备失败"),
    ADD_DEVICE_SUCCESS("03003", "添加设备成功"),
    ADD_DEVICE_FAILURE("03004", "添加设备失败"),
    NO_REQUESTED_DEVICE("03005","没有该类型的设备"),
    UPDATE_DEVICE_SUCCESS("03006", "更新设备成功"),
    UPDATE_DEVICE_FAILURE("03007", "更新设备失败"),
    DELETE_DEVICE_SUCCESS("03008", "删除设备成功"),
    DELETE_DEVICE_FAILURE("03009", "删除设备失败"),
    UPDATE_DEVICE_STATE_SUCCESS("03010","修改设备状态成功"),
    UPDATE_DEVICE_STATE_FAILURE("03011","修改设备状态失败"),
    GET_DEVICE_DATA_BY_TIME_SUCCESS("03012","按时间采集数据成功"),
    GET_DEVICE_DATA_BY_TIME_FAILURE("03013","按实际采集数据失败"),
    GET_DEVICE_PRODUCT_DATA_SUCCESS("03014","查询所有设备和产品数据成功"),
    GET_DEVICE_PRODUCT_DATA_FAILURE("03015","查询所有设备和产品数据失败"),
    GET_RECENT_DATA_SUCCESS("03016","查询最新一条数据成功"),
    GET_RECENT_DATA_FAILURE("03017","查询最新一条数据失败"),
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
