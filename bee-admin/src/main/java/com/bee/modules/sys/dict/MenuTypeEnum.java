package com.bee.modules.sys.dict;

/**
 * @author Bruce
 * @create 2023/12/19
 * @description 菜单类型枚举类
 */
public enum MenuTypeEnum {
    BUTTON(1),
    MENU(0);

    private int value;

    MenuTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
