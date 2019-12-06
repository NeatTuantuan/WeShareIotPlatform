package edu.xd.bdilab.iotplatform.controller.license;

import edu.xd.bdilab.iotplatform.controller.response.MetaData;
import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import edu.xd.bdilab.iotplatform.service.license.LicenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Decription TODO
 * @Author Humphrey
 * @Date 2019/9/21 10:00
 * @Version 1.0
 **/
@Controller
@CrossOrigin
@Api(value = "license controller")
public class LicenseController {

    @Autowired
    LicenseService licenseService;

    @Value("${checkfile.path}")
    private String checkFilePath;

    @Value("${license.path}")
    private String licensePath;

    @Value("${certificate.path}")
    private String certificatePath;



    @ApiOperation("上传证书")
    @ResponseBody
    @RequestMapping(value = "/license/uploadLicense",method = RequestMethod.POST)
    public ResponseResult uploadLicense(@RequestParam(value = "licenseFile")@ApiParam(value = "证书文件") MultipartFile licenseFile)throws Exception{
        ResponseResult responseResult = new ResponseResult();

        //将checkFile保存到文件系统，若将checkFile交至其他线程处理，会导致tomcat清除临时文件，产生空指针
        //组装新的文件名和文件地址
        String filePath =licensePath+ File.separatorChar + "license";
        File file = new File(filePath);

        //若目录下已有license文件，删除之
        if(file.exists()){
            file.delete();
        }
        //将上传的校验文件保存到新的地址中
        try {
            licenseFile.transferTo(file);

        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setMeta(new MetaData(false,"002","上传许可证文件失败。"));
            return responseResult;
        }
        responseResult.setMeta(new MetaData(true,"001","成功上传许可证文件。"));
        return responseResult;
    }

    @ApiOperation("生成checkfile文件")
    @ResponseBody
    @RequestMapping(value = "/license/generateCheckFile",method = RequestMethod.POST)
    public ResponseResult generateCheckFile(@RequestParam(value = "authCode")@ApiParam(value = "激活码") String authCode)throws Exception{
        boolean isSuccess = licenseService.generateCheckFile(authCode);
        if(isSuccess){
            return new ResponseResult(isSuccess,"001","成功生成校验文件。");
        }
        return new ResponseResult(isSuccess,"002","生成校验文件失败。");
    }


    @ResponseBody
    @ApiOperation("下载checkfile")
    @RequestMapping(value = "/auth/downloadCheckfile",method = RequestMethod.POST)
    public void downloadFile( HttpServletRequest request, HttpServletResponse response) {
        String checkFilePath = "D:/Idea/WeShareIotPlatform/checkfile"+File.separatorChar+"checkfile";
        //设置文件路径
        File file = new File(checkFilePath);
        if (file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=checkfile.file");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
