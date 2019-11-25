package edu.xd.bdilab.iotplatform.netty.redis;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {
    RedisUtil redisUtil = new RedisUtil();

    @Test
    public void get() {
        JSONObject json = new JSONObject();
        json.put("温度", "29");
        json.put("湿度", "22");
        System.out.println(json);
    }

    @Test
    public void deleteKey() {
        redisUtil.deleteKey("test",1);
    }
}
