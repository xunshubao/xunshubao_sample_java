package com.xunshubao.sample.util;

import com.xunshubao.sample.ZxgkConstant;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class SignUtils {

    public static String digest(String signType, String src) {
        if (src == null)
            return null;
        if (ZxgkConstant.DIGEST_SM3.equalsIgnoreCase(signType)) {
            return SM3Utils.encrypt(src);
        } else if (ZxgkConstant.DIGEST_MD5.equalsIgnoreCase(signType)) {
            return DigestUtils.md5Hex(src);
        } else if (ZxgkConstant.DIGEST_SHA256.equalsIgnoreCase(signType)) {
            return DigestUtils.sha256Hex(src);
        }
        return null;
    }

    /**
     * 验证签名是否正确
     *
     * @param signType 摘要算法类型
     * @param src      明文
     * @param token    密文
     * @return 校验结果是否一致
     */
    public static boolean verify(String signType, String src, String token) {
        if (ZxgkConstant.DIGEST_SM3.equalsIgnoreCase(signType)) {
            return SM3Utils.verify(src, token);
        } else if (ZxgkConstant.DIGEST_MD5.equalsIgnoreCase(signType)) {
            String checkStr = DigestUtils.md5Hex(src);
            return StringUtils.isNotBlank(checkStr) && checkStr.equals(token);
        } else if (ZxgkConstant.DIGEST_SHA256.equalsIgnoreCase(signType)) {
            String checkStr = DigestUtils.sha256Hex(src);
            return StringUtils.isNotBlank(checkStr) && checkStr.equals(token);
        }

        return false;
    }


}
