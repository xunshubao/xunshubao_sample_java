package com.xunshubao.sample.util;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Security;
import java.util.Arrays;

public class SM3Utils {
    private static final Logger logger = LoggerFactory.getLogger(SM3Utils.class);

    private static final String ENCODING = "UTF-8";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * sm3算法加密
     *
     * @param paramStr 待加密字符串
     * @return 返回加密后，固定长度=32的16进制字符串
     * @explain
     */
    public static String encrypt(String paramStr) {
        // 将返回的hash值转换成16进制字符串
        String resultHexString = "";

        try {
            // 将字符串转换成byte数组
            byte[] srcData = paramStr.getBytes(ENCODING);
            // 调用hash()
            byte[] resultHash = hash(srcData);
            // 将返回的hash值转换成16进制字符串
            resultHexString = Hex.toHexString(resultHash);
        } catch (Exception e) {
            logger.error("encrypt", e);
        }

        return resultHexString;
    }

    /**
     * sm3算法加密
     *
     * @param paramStr 待加密字符串
     * @param key      密钥
     * @return 返回加密后，固定长度=32的16进制字符串
     * @explain
     */
    public static String encryptPlus(String paramStr, String key) {
        // 将返回的hash值转换成16进制字符串
        String resultHexString = "";
        try {
            // 将字符串转换成byte数组
            byte[] srcData = paramStr.getBytes(ENCODING);
            // 调用hash()
            byte[] resultHash = hmac(srcData, key.getBytes(ENCODING));
            // 将返回的hash值转换成16进制字符串
            resultHexString = Hex.toHexString(resultHash);
        } catch (Exception e) {
            logger.error("encryptPlus", e);
        }
        return resultHexString;
    }

    /**
     * 返回长度=32的byte数组
     *
     * @param srcData
     * @return
     * @explain 生成对应的hash值
     */
    public static byte[] hash(byte[] srcData) {
        SM3Digest digest = new SM3Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }

    /**
     * 通过密钥进行加密
     *
     * @param key     密钥
     * @param srcData 被加密的byte数组
     * @return
     * @explain 指定密钥进行加密
     */
    public static byte[] hmac(byte[] key, byte[] srcData) {
        KeyParameter keyParameter = new KeyParameter(key);
        SM3Digest digest = new SM3Digest();
        HMac mac = new HMac(digest);
        mac.init(keyParameter);
        mac.update(srcData, 0, srcData.length);
        byte[] result = new byte[mac.getMacSize()];
        mac.doFinal(result, 0);
        return result;
    }

    /**
     * 判断源数据与加密数据是否一致
     *
     * @param srcStr       原字符串
     * @param sm3HexString 16进制字符串
     * @return 校验结果
     * @explain 通过验证原数组和生成的hash数组是否为同一数组，验证2者是否为同一数据
     */
    public static boolean verify(String srcStr, String sm3HexString) {
        boolean flag = false;
        try {
            byte[] srcData = srcStr.getBytes(ENCODING);
            byte[] sm3Hash = Hex.decode(sm3HexString);
            byte[] newHash = hash(srcData);
            if (Arrays.equals(newHash, sm3Hash)) {
                flag = true;
            }
        } catch (Exception e) {
            logger.error("verify", e);
        }
        return flag;
    }

    public static void main(String[] args) {
        String str = "xxxxx";
        //秘钥
        String hex = SM3Utils.encrypt(str);
        System.out.println("参数:" + str);
        System.out.println("密文:" + hex);

    }


}
