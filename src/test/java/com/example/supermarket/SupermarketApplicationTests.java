package com.example.supermarket;

import com.alibaba.fastjson.JSON;
import com.example.supermarket.client.JyClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SupermarketApplicationTests {
    @Resource
    private JyClient jyClient;

    @Test
    public void contextLoads() throws Exception {
        Map<String, Object> map = new HashMap<>();
//        map.put("outOrderId", "11");
//        map.put("outOrderAmount", 100);
//        map.put("tradeNo", "");
        jyClient.request("pay/refund/gateway", JSON.toJSONString(map));
//        String str = "{\"appid\":\"ea5d5a258f\",\"mch_id\":10000100,\"orderAmount\":10000,\"nonce_str\":\"ibuaiVcKdpRxkhJA\",\"sign\":\"96e79218965eb72c92a549dd5a330112\"}";
//
//        log.info("-----#{}", EncryptUtil.encode(str));
//        log.info("-----#{}", EncryptUtil.encode3Des("7a0afa8bfaa73f0f064d46e1",str));


    }

}
