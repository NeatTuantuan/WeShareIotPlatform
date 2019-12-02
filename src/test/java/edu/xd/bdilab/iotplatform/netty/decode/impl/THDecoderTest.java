package edu.xd.bdilab.iotplatform.netty.decode.impl;

import edu.xd.bdilab.iotplatform.netty.util.DataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class THDecoderTest {

    @Test
    public void decode() {
        String s = "020304010A00CBA95A";
        byte[] bytes = DataUtil.deocde(s);
        String channelid = "4f353555";
        THDecoder thDecoder = new THDecoder();
        thDecoder.decode(bytes,channelid);
    }
}