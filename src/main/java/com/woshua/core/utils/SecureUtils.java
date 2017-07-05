package com.woshua.core.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Acer on 2017/3/27.
 */
public class SecureUtils {
    /**
     * 生成SHA1摘要
     *
     * @param distStr
     * @return
     */
    public static String sha1Hex(String distStr) {
        if (distStr == null || "".equals(distStr.trim())) {
            return null;
        }
        return DigestUtils.sha1Hex(distStr + Constant.SECRET_KEY_PASSWORD);
    }
}
