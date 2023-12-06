package com.bee.common.util.validator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.bee.common.exception.BeeException;
import com.bee.common.exception.ErrorCode;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/05
 * @description 校验工具类
 */
public abstract class AssertUtils {

    /**
     * String校验
     * @param str
     * @param params
     */
    public static void isBlank(String str, String... params) {
        isBlank(str, ErrorCode.NOT_NULL, params);
    }

    public static void isBlank(String str, Integer code, String... params) {
        if (Objects.isNull(code)) {
            throw new BeeException(ErrorCode.NOT_NULL, "code");
        }

        if (StrUtil.isBlank(str)) {
            throw new BeeException(code, params);
        }
    }

    /**
     * Object校验
     * @param object
     * @param params
     */
    public static void isNull(Object object, String... params) {
        isNull(object, ErrorCode.NOT_NULL, params);
    }

    public static void isNull(Object object, Integer code, String... params) {
        if (Objects.isNull(code)) {
            throw new BeeException(ErrorCode.NOT_NULL, "code");
        }

        if (Objects.isNull(object)) {
            throw new BeeException(code, params);
        }
    }

    /**
     * Array校验
     * @param array
     * @param params
     */
    public static void isArrayEmpty(Object[] array, String... params) {
        isArrayEmpty(array, ErrorCode.NOT_NULL, params);
    }

    public static void isArrayEmpty(Object[] array, Integer code, String... params) {
        if (Objects.isNull(code)) {
            throw new BeeException(ErrorCode.NOT_NULL, "code");
        }

        if (ArrayUtil.isEmpty(array)) {
            throw new BeeException(code, params);
        }
    }

    /**
     * List校验
     * @param list
     * @param params
     */
    public static void isListEmpty(List<?> list, String... params) {
        isListEmpty(list, ErrorCode.NOT_NULL, params);
    }

    public static void isListEmpty(List<?> list, Integer code, String... params) {
        if (Objects.isNull(code)) {
            throw new BeeException(ErrorCode.NOT_NULL, "code");
        }

        if (CollUtil.isEmpty(list)) {
            throw new BeeException(code, params);
        }
    }

    /**
     * Map校验
     * @param map
     * @param params
     */
    public static void isMapEmpty(Map map, String... params) {
        isMapEmpty(map, ErrorCode.NOT_NULL, params);
    }

    public static void isMapEmpty(Map map, Integer code, String... params) {
        if (Objects.isNull(code)) {
            throw new BeeException(ErrorCode.NOT_NULL, "code");
        }

        if (MapUtil.isEmpty(map)) {
            throw new BeeException(code, params);
        }
    }

}
