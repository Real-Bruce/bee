package com.bee.common.redis;

/**
 * @author Bruce
 * @create 2023/12/06
 * @description Redis Keys
 */
public class RedisKeys {

    /**
     * 系统参数key
     */
    public static String getSystemParamsKey() {
        return "sys:params";
    }

    /**
     * 验证码key
     */
    public static String getCaptchaKey(String uuid) {
        return "sys:captcha:" + uuid;
    }

    /**
     * 登录用户key
     */
    public static String getSecurityUserKey(Long id) {
        return "sys:security:" + id;
    }

    /**
     * 系统日志key
     */
    public static String getSysLogKey() {
        return "sys:log";
    }

    /**
     * 系统资源key
     */
    public static String getResourceKey() {
        return "sys:resource";
    }

    /**
     * 用户菜单导航key
     */
    public static String getUserMenuNavKey(Long userId) {
        return "sys:user:nav" + userId;
    }

    /**
     * 用户权限标识key
     */
    public static String getUserPermissionKey(Long userId) {
        return "sys:user:permission" + userId;
    }


}
