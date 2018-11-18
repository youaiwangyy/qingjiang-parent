package com.qingjiang.common.util;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.qingjiang.common.ApplicationBaseTest;
import com.qingjiang.common.domain.User;
import com.qingjiang.common.enums.RSAEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

@Slf4j
@SuppressWarnings("all")
public class JRSATest extends ApplicationBaseTest {

    @Test
    public void testClientRequest() {
        RSAEnum order = RSAEnum.ORDER;
        //发送的数据
        String data = "wo shi li qing jiang";
        //签名
        String sign = JRSA.sign(data, order.getPrivateKey());
        //加密
        String sendData = JRSA.publicEncrypt(data, RSAEnum.SERVER.getPublicKey());
        //组装请求参数
        Map<String, String> requestInfoMap = Maps.newHashMap();
        requestInfoMap.put("data", sendData);
        requestInfoMap.put("sign", sign);
        requestInfoMap.put("appkey", "order");
        String requestInfo = JSON.toJSONString(requestInfoMap);
        log.info("requestInfo:{}", requestInfo);

        //发送请求


        //String requestInfo = "{\"data\":\"DkqVtMSZ4BC04JcYeTWJCV2_WLXYN-RJqcxpzeWIm_1DWketBYrMU_Ta5ywoTnRcmgtwF0GmvyOu7H5-TvSjeBOjRgZjjBwQKdZixDPSBiiWu3WnyNrEMzs5ppMuZWUzWeRwHz5k1970Gr86pNr9_M9KZ3cpW2fet78ZrODAyks\",\"sign\":\"FkD3_n0VpCzvbQelXJHba5S7R9a6oRKq2CY1IdVX6UhtNaj2DMaL47_jS-7lJqe2l_A8RvhV5p_9xK-hT-GYWryhlkANO9zB0dqPnd3H65uiqM_e1H8HfNwFeTwTmAqQdJVsQ-7LRjnbiTBLkYulT1uPO648syU2MJsyvm_FPeA\",\"appkey\":\"order\"}";

    }

    @Test
    public void testServerReceive() {
        String requestInfo = "{\"data\":\"M_w5oyhhzAvrsny4I6TROyOm4iEraQrax6gAxwS6YJZNWA41JCSNrTZavvXCXUD3mQkL822pL84lQog4FWlid_weiPpz2hJpZE7TyofkhDuStHub-WZwiJMD6lquE2yauiFotnbG-wkY6Ov3NkYQixymKnUdcL0WF7hiTnX209g\",\"sign\":\"FkD3_n0VpCzvbQelXJHba5S7R9a6oRKq2CY1IdVX6UhtNaj2DMaL47_jS-7lJqe2l_A8RvhV5p_9xK-hT-GYWryhlkANO9zB0dqPnd3H65uiqM_e1H8HfNwFeTwTmAqQdJVsQ-7LRjnbiTBLkYulT1uPO648syU2MJsyvm_FPeA\",\"appkey\":\"order\"}";

        Map<String, String> map = JSON.parseObject(requestInfo, Map.class);
        String appkey = map.get("appkey");
        String data2 = map.get("data");
        String sign2 = map.get("sign");
        log.info("data:{}", data2);
        log.info("sign:{}", sign2);
        log.info("appkey:{}", appkey);

        RSAEnum rsaEnum = RSAEnum.parse(appkey);
        String info = JRSA.privateDecrypt(data2, RSAEnum.SERVER.getPrivateKey());
        log.info("data:{}", info);
        boolean verify = JRSA.verify(info, rsaEnum.getPublicKey(), sign2);
        log.info("verify:{}", verify);

    }


    @Test
    public void testClientRequestServer() {
        RSAEnum cliEnum = RSAEnum.ORDER;
        RSAEnum serEnum = RSAEnum.SERVER;

        log.info("===== 发送请求 =====");
        User user = new User();
        user.setId(100L);
        user.setName("my name is li qing jiang.");
        String desc = "wo shi li qing jiang";
        for (int i = 0; i < 10; i++) {
            desc += desc;
        }
        user.setDesc(desc);
        String userInfo = JSON.toJSONString(user);

        String sign = JRSA.sign(userInfo, cliEnum.getPrivateKey());
        log.info("userInfo:{}", userInfo);
        log.info("sign:{}", sign);

        String sendData = JRSA.publicEncrypt(userInfo, serEnum.getPublicKey());
        Map<String, String> data = Maps.newHashMap();
        data.put("data", sendData);
        data.put("sign", sign);
        data.put("appkey", "client");
        log.info("sendData:{}", sendData);

        String sendInfo = JSON.toJSONString(data);
        log.info("sendInfo={}", sendInfo);
        log.info("===== 发送结束 =====");

        log.info("===== 服务开始 =====");
        Map<String, String> sendInfoMap = JSON.parseObject(sendInfo, Map.class);
        String appkey = sendInfoMap.get("appkey");
        String sign2 = sendInfoMap.get("sign");
        String sendData2 = sendInfoMap.get("data");

        String userInfo2 = JRSA.privateDecrypt(sendData2, serEnum.getPrivateKey());
        log.info("userInfo2={}", userInfo2);
        boolean verify = JRSA.verify(userInfo2, cliEnum.getPublicKey(), sign2);
        log.info("verify={}", verify);
        if (verify) {
            User user2 = JSON.parseObject(userInfo2, User.class);
            log.info("user2={}", user2);
        }
        log.info("===== 服务结束 =====");
    }


}
