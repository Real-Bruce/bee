package com.bee.oauth2;

import com.bee.common.exception.BeeException;

import java.security.MessageDigest;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Bruce
 * @create 2023/12/12
 * @description token生成器
 */
public class TokenGenerator {

    private static final char[] HEX_CODE = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * 生成token
     * @return
     */
    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    /**
     * 生成value
     */
    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] digest = algorithm.digest();
            return toHexString(digest);

        } catch (Exception e) {
            throw new BeeException("token invalid", e);
        }
    }

    /**
     * 转换字符串
     */
    public static String toHexString(byte[] data) {
        if (Objects.isNull(data)) {
            return null;
        }

        StringBuilder builder = new StringBuilder(data.length * 2);
        for (byte b : data) {
            builder.append(HEX_CODE[(b >> 4) & 0xF]);
            builder.append(HEX_CODE[(b & 0xF)]);
        }
        return builder.toString();
    }
}
