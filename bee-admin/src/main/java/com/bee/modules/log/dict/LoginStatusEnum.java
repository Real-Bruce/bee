package com.bee.modules.log.dict;

import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/21
 * @description 登录状态枚举
 */
public enum LoginStatusEnum {
    /**
     * 失败
     */
    FAIL("失败",0),
    /**
     * 成功
     */
    SUCCESS("成功",1),
    /**
     * 账号已锁定
     */
    LOCK("账号已锁定",2)
    ;

    private String name;

    private int code;

    LoginStatusEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static LoginStatusEnum formToEnum(int code) {
        for (LoginStatusEnum statusEnum : LoginStatusEnum.values()) {
            if (code == statusEnum.getCode()) {
                return statusEnum;
            }
        }
        return null;
    }
}
