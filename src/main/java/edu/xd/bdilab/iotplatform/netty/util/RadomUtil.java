package edu.xd.bdilab.iotplatform.netty.util;

import java.util.Random;

/**
 * @ClassName RadomUtil
 * @Description TODO
 * @Auther tuantuan
 * @Date 2019/11/23 12:05
 * @Version 1.0
 * @Attention Copyright (C)，2004-2019，BDILab，XiDian University
 **/
public class RadomUtil {
    /**
     * 生成随机5位字符串
     * @return
     */
    public String getRandomString(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<5;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
