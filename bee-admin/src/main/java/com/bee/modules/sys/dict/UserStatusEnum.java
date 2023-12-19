package com.bee.modules.sys.dict;

/**
 * @author Bruce
 * @create 2023/12/19
 * @description 用户账户状态枚举类
 */
public enum UserStatusEnum {

    ENABLED(1),
    DISABLED(0);
    private int value;

    UserStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
