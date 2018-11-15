package com.qingjiang.qingjiangparent.util;

import com.qingjiang.qingjiangparent.enums.RSAEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RSATest {


    @Test
    public void testRSA() {
        String str = "wo shi liqingjiang";
        String privateKey = RSAEnum.ORDER_RSA.getPrivateKey();
        String publicKey = RSAEnum.ORDER_RSA.getPublicKey();
        System.out.println("=== 公钥加密, 私钥解密 ===");
        String result = RSA.encryptByPublic(str, publicKey);
        System.out.println("result:" + result);
        String str2 = RSA.decryptByPrivate(result, privateKey);
        System.out.println("str2:" + str2);

        System.out.println("=== 私钥加密, 公钥解密 ===");
        String result2 = RSA.encryptByPrivate(str, privateKey);
        System.out.println("result:" + result2);
        String str3 = RSA.decryptByPublic(result2, publicKey);
        System.out.println("str2:" + str2);

        System.out.println("=== 私钥签名 & 公钥验签 ===");
        // 产生签名
        String sign = RSA.sign(str, privateKey);
        System.out.println("签名:"+sign);
        // 验证签名
        boolean status = RSA.verify(str, publicKey, sign);
        System.out.println("状态:"+status);


    }

    @Test
    public void initRSAKey() {
        Map<String, Object> map = new HashMap<String, Object>();
        map = RSA.init();
        System.out.println("公钥："+RSA.getPublicKey(map));
        System.out.println("私钥："+RSA.getPrivateKey(map));

    }

}
