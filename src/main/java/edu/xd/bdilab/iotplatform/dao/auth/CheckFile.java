package edu.xd.bdilab.iotplatform.dao.auth;

/**
 * @Decription TODO
 * @Author Humphrey
 * @Date 2019/9/22 11:45
 * @Version 1.0
 **/
public class CheckFile {
    String authCode;
    String machineCode;

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public String getAuthCode() {
        return authCode;
    }
}
