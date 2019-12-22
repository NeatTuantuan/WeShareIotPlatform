package edu.xd.bdilab.iotplatform.dao.auth;

import java.util.Date;
import java.util.List;

/**
 * @Decription TODO
 * @Author Humphrey
 * @Date 2019/9/21 10:39
 * @Version 1.0
 **/
public class License {
    private Integer licenseNum;
    private String authCode;
    private String machineCode;
    private List<Integer> products;
    private Date expireDate;
    private String sign;
    private Integer isUnlimited;

    public Date getExpireDate() {
        return expireDate;
    }

    public List<Integer> getProducts() {
        return products;
    }

    public void setProducts(List<Integer> products) {
        this.products = products;
    }

    public Integer getIsUnlimited() {
        return isUnlimited;
    }

    public Integer getLicenseNum() {
        return licenseNum;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public String getSign() {
        return sign;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setIsUnlimited(Integer isUnlimited) {
        this.isUnlimited = isUnlimited;
    }

    public void setLicenseNum(Integer licenseNum) {
        this.licenseNum = licenseNum;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString(){
        return "machineCode: " + machineCode +", authCode: "+ authCode+", expireDate: "+expireDate+", sign: "+sign+", isUnlimited: "+isUnlimited;
    }
}
