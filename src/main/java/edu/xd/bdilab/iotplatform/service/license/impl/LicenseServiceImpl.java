package edu.xd.bdilab.iotplatform.service.license.impl;


import com.google.gson.Gson;
import edu.xd.bdilab.iotplatform.dao.auth.CheckFile;
import edu.xd.bdilab.iotplatform.dao.auth.License;
import edu.xd.bdilab.iotplatform.netty.util.DecryptUtils;
import edu.xd.bdilab.iotplatform.netty.util.MachineCodeUtils;
import edu.xd.bdilab.iotplatform.service.license.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Decription TODO
 * @Author Humphrey
 * @Date 2019/9/21 10:24
 * @Version 1.0
 **/
@Service
public class LicenseServiceImpl implements LicenseService {
    @Value("${license.path}")
    private String licensePath;
    @Value("${certificate.path}")
    private String certificatePath;
    @Value("${checkfile.path}")
    private String checkFilePath;

    @Override
    public boolean licenseFileExistenceCheck() {
        String licenseFilePath = licensePath+File.separatorChar + "license";
        File licenseFile = new File(licenseFilePath);
        if(!licenseFile.exists()){
            return false;
        }
        return true;
    }

    @Override
    public License decryptLicense() {
        String licenseFilePath = licensePath+File.separatorChar + "license";
        File licenseFile = new File(licenseFilePath);
        String licenseEncryptString = "";
        try{
            InputStream is = new FileInputStream(licenseFile);
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];
            is.read(bytes);
            licenseEncryptString = new String(bytes);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        String licenseDecryptString ="";
        try{
            licenseDecryptString = DecryptUtils.decryptLicense(licenseEncryptString,certificatePath);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(licenseDecryptString, License.class);
    }

    @Override
    public Map<String,String> checkLicense(License license){
        if(license==null){
            return null;
        }
        Map<String,String> data = new HashMap<>(6);
        //检查内容是否被篡改
        if(!license.getSign().equals(DecryptUtils.getMd5(license.getMachineCode() + "bdilab" + license.getAuthCode() + "bdilab" + license.getExpireDate() + "bdilab"))){
            data.put("isTempered","true");
            data.put("isAccessible","false");
            return  data;
        }

        data.put("isTempered","false");
        boolean isMachineCodeCorrect = license.getMachineCode().equals(MachineCodeUtils.getMachineCode());
        if(!isMachineCodeCorrect){
            data.put("machineCodeCheck","false");
            data.put("isAccessible","false");
            return  data;
        }

        data.put("machineCodeCheck","true");
        //检查是否为无限版本
        if(license.getIsUnlimited() == 1){
            data.put("isUnlimited","true");
            data.put("isAccessible","true");
            return  data;
        }
        data.put("isUnlimited","false");
        //检查过期时间
        if(license.getExpireDate().getTime()<=System.currentTimeMillis()){
            data.put("isExpired","true");
            data.put("expireDate",license.getExpireDate().toString());
            data.put("isAccessible","false");
            return data;
        }
        data.put("isExpired","false");
        data.put("expireDate",license.getExpireDate().toString());
        data.put("isAccessible","true");
        return data;
    }

    @Override
    public boolean generateCheckFile(String authCode) {
        String machineCode = MachineCodeUtils.getMachineCode();
        CheckFile checkFile =new CheckFile();
        checkFile.setAuthCode(authCode);
        checkFile.setMachineCode(machineCode);
        Gson gson = new Gson();
        String checkFileJsonString = gson.toJson(checkFile);
        String filePath = checkFilePath+File.separatorChar+"checkfile";
        File file = new File(filePath);
        //删除已有的checkfile文件
        if(!file.isDirectory()&&file.exists()){
            file.delete();
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
            out.write(checkFileJsonString);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
