package com.bee.modules.log.dict;

/**
 * @author Bruce
 * @create 2023/12/21
 * @description 用户登录操作枚举
 */
public enum LoginOperationEnum {
    /**
     * 用户登录
     */
    LOGIN(0),
    /**
     * 用户退出
     */
    LOGOUT(1);

    private int value;

    LoginOperationEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
