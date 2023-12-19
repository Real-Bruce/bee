package com.bee.modules.sys.dict;

/**
 * @author Bruce
 * @create 2023/12/19
 * @description 超级管理员枚举类
 */
public enum SuperAdminEnum {
    YES(1),
    NO(0);

    private int value;

    SuperAdminEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
