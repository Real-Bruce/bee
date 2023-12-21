package com.bee.modules.log.dict;

/**
 * @author Bruce
 * @create 2023/12/21
 * @description 操作状态枚举
 */
public enum OperationStatusEnum {
    /**
     * 失败
     */
    FAIL(0),
    /**
     * 成功
     */
    SUCCESS(1);

    private int value;

    OperationStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
