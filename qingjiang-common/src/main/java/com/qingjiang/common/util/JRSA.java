package com.qingjiang.common.util;

import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 江-RAS
 * qingjiang.li
 */
public class JRSA {

    //定义加密方式
    private static final String KEY_RSA = "RSA";
    //定义公钥关键词
    private static final String KEY_RSA_PUBLICKEY = "RSAPublicKey";
    //定义私钥关键词
    private static final String KEY_RSA_PRIVATEKEY = "RSAPrivateKey";
    //定义签名算法
    private final static String KEY_RSA_SIGNATURE = "MD5withRSA";
    //定义字符集
    public static final String CHARSET = "UTF-8";

    public static final int KEY_SIZE = 1024;


    /**
     * BASE64 解码
     *
     * @param key 需要Base64解码的字符串
     * @return 字节数组
     */
    public static byte[] decryptBase64(String key) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(key);
    }

    /**
     * BASE64 编码
     *
     * @param key 需要Base64编码的字节数组
     * @return 字符串
     */
    public static String encryptBase64(byte[] key) {
        return org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(key);
    }


    /**
     * 用私钥对加密数据进行签名
     *
     * @param encryptedStr
     * @param privateKey
     * @return
     */
    public static String sign(String encryptedStr, String privateKey) {
        String str = "";
        try {
            //将私钥加密数据字符串转换为字节数组
            byte[] data = encryptedStr.getBytes();
            // 解密由base64编码的私钥
            byte[] bytes = decryptBase64(privateKey);
            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);
            // 指定的加密算法
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            // 取私钥对象
            PrivateKey key = factory.generatePrivate(pkcs);
            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
            signature.initSign(key);
            signature.update(data);
            str = encryptBase64(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 校验数字签名
     *
     * @param encryptedStr
     * @param publicKey
     * @param sign
     * @return 校验成功返回true，失败返回false
     */
    public static boolean verify(String encryptedStr, String publicKey, String sign) {
        boolean flag = false;
        try {
            //将私钥加密数据字符串转换为字节数组
            byte[] data = encryptedStr.getBytes();
            // 解密由base64编码的公钥
            byte[] bytes = decryptBase64(publicKey);
            // 构造X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            // 指定的加密算法
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            // 取公钥对象
            PublicKey key = factory.generatePublic(keySpec);
            // 用公钥验证数字签名
            Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
            signature.initVerify(key);
            signature.update(data);
            flag = signature.verify(decryptBase64(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    public static String publicEncrypt(String data, String publicKeyStr) {
        try {
            // 将公钥由字符串转为UTF-8格式的字节数组
            byte[] publicKeyBytes = decryptBase64(publicKeyStr);
            // 获得公钥
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory factory;
            factory = KeyFactory.getInstance(KEY_RSA);
            RSAPublicKey publicKey = (RSAPublicKey) factory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(KEY_RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    public static String privateDecrypt(String data, String privateKeyStr) {
        try {
            // 对私钥解密
            byte[] privateKeyBytes = decryptBase64(privateKeyStr);
            // 获得私钥
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            RSAPrivateKey privateKey = (RSAPrivateKey) factory.generatePrivate(keySpec);
//            RSAPublicKey publicKey = (RSAPublicKey)factory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(KEY_RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, org.apache.commons.codec.binary.Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }


    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }

    public static Map<String, String> createKeys() {
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(KEY_RSA);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + KEY_RSA + "]");
        }

        //初始化KeyPairGenerator对象,不要被initialize()源码表面上欺骗,其实这里声明的size是生效的
        kpg.initialize(KEY_SIZE);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    public static void main(String[] args) {
        Map<String, String> rsaKeys = createKeys();
        System.out.println("publicKey:" + rsaKeys.get("publicKey"));
        System.out.println("privateKey:" + rsaKeys.get("privateKey"));


        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTWeM_POREJzFrJOOhEqa3DcidqSljPjAznkb5mEvPlL4xJKKba47s0SoBKdsiEf-C9aGoUAtHw3W9K5xR05aeu3gJ8Z0ElU1MQP-oghASg9Epj0ZC_8_Nt1v5EUpRfhiezrXa-3iyrWoSRvv4flKhNbK7JkSlBQwtNtj7270INwIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJNZ4z885EQnMWsk46ESprcNyJ2pKWM-MDOeRvmYS8-UvjEkoptrjuzRKgEp2yIR_4L1oahQC0fDdb0rnFHTlp67eAnxnQSVTUxA_6iCEBKD0SmPRkL_z823W_kRSlF-GJ7Otdr7eLKtahJG-_h-UqE1srsmRKUFDC022PvbvQg3AgMBAAECgYANGVaMTpt0O4-3GKZrsT7E0yLccSdOvT6HuDBTC-jhYTWvJLSX4G3qO4XiaJgkZTkzqSZf0g3OqV-UG4Zf99FR1IvVa_sQz4Ng1wly8_RHUatAnqj4bWJ5SxZg76EATrAqFPV7NuqvPkSZUO4yHT6QkkJgpqr9AHqa-FT65lckAQJBAMSHt9w2rcFkga2xIpcdJijn2IHM2knb0hGGuRzMk5RUHXQLKFS-8-tzDwkyKvE3iQW7VMnRE7elAzgDWz-yCwECQQC_8H9wVgvWzRfOLMbjg0Hd1JBU2jUFRVT0gHFYJurVnzWjc8OYUT8cfX2BepNPYgX1qLSeM_Sh3_AgwlO-I6s3AkEAl3DRoDLOP9k4CGUX94QxtJG7h4IYr5NhU7oSCPwnuMP8kkBKysr-YO-fpYFwD8KwpqcNFhsza28PhOkpNnL3AQJACeGpzfDAH0jjxtuLSb9CLtd4rsWlISZWn1fVfgJS6LiPnGCjWz4jj6XuRjI3T0juoZ0T32kxWPwccrNOeGWBtQJBAIA4uglOeLlZ6rBIFXamEpTgO7XvWslvUANqC5z9NRzqz3OdLq8foEkE7DRm2K5sMU6V6_X1aZBglzW98vSX-FM";

        String str = "wo shi liqingjiang.";
        for (int i = 0; i < 10; i++) {
            str += str;
        }
        System.out.println(str.length());//加密字符串长度

        String publicEncrypt = JRSA.publicEncrypt(str, publicKey);
        System.out.println(publicEncrypt);

        System.out.println("============");
        String privateDecrypt = JRSA.privateDecrypt(publicEncrypt, privateKey);
        System.out.println(privateDecrypt);

        String sign = JRSA.sign(str, privateKey);
        System.out.println("sign:" + sign);

        boolean verify = JRSA.verify(str, publicKey, sign);
        System.out.println("verify:" + verify);

    }


}
