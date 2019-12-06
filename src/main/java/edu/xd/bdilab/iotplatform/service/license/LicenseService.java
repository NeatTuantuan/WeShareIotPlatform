package edu.xd.bdilab.iotplatform.service.license;



import edu.xd.bdilab.iotplatform.dao.auth.License;

import java.util.Map;

/**
 * @Decription TODO
 * @Author Humphrey
 * @Date 2019/9/21 10:24
 * @Version 1.0
 **/
public interface LicenseService {

    /**
     * 检测是否存在许可证文件
     * @return
     */
    boolean licenseFileExistenceCheck();

    /**
     * 检测许可证有效性
     * @param license
     * @return
     */
    Map<String,String> checkLicense(License license);

    /**
     * 解码许可证
     * @return
     */
    License decryptLicense();

    /**
     * 生成校验文件
     * @param authCode
     * @return
     */
    boolean generateCheckFile(String authCode);
}
