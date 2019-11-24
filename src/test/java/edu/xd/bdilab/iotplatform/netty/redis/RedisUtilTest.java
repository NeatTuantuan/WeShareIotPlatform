package edu.xd.bdilab.iotplatform.netty.redis;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RedisUtilTest {
 RedisUtil redisUtil = new RedisUtil();

    @Test
    public void get() {
        JSONObject json = new JSONObject();
        json.put("温度","29");
        json.put("湿度","22");
        System.out.println(json);

    }
}