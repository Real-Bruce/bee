package com.bee.modules.security.password;

/**
 * @author Bruce
 * @create 2023/12/20
 * @description 密码工具类
 */
public abstract class PasswordUtils {

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 密码加密
     * @param str 字符串
     * @return 加密后的字符串
     */
    public static String encode(String str) {
        return passwordEncoder.encode(str);
    }

    /**
     * 密码比较
     * @param str 明文密码
     * @param password 加密后的密码
     * @return boolean
     */
    public static boolean matchs(String str, String password) {
        return passwordEncoder.matches(str, password);
    }
}
