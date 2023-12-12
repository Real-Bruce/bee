package com.bee.modules.security.user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/12
 * @description 用户信息
 */
public class SecurityUser {

    public static Subject getSubject() {
        try {
            return SecurityUtils.getSubject();
        }catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户信息
     */
    public static UserDetail getUser() {
        Subject subject = getSubject();
        if (Objects.isNull(subject)) {
            return new UserDetail();
        }

        UserDetail userDetail = (UserDetail) subject.getPrincipal();
        if (Objects.isNull(userDetail)) {
            return new UserDetail();
        }

        return userDetail;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return getUser().getUserId();
    }

    /**
     * 获取部门ID
     */
    public static Long getDeptId() {
        return getUser().getDeptId();
    }
}
