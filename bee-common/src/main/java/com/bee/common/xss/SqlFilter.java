package com.bee.common.xss;

import cn.hutool.core.util.StrUtil;
import com.bee.common.exception.BeeException;
import com.bee.common.exception.ErrorCode;

/**
 * @author Bruce
 * @create 2023/12/06
 * @description SQL 防注入过滤
 */
public class SqlFilter {

    public static String sqlInject(String str) {
        if (StrUtil.isBlank(str)) {
            return null;
        }

        str = StrUtil.replace(str, "'", "");
        str = StrUtil.replace(str, "\"", "");
        str = StrUtil.replace(str, ";", "");
        str = StrUtil.replace(str, "\\", "");
        str.toLowerCase();

        String [] illegalWords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        // 判断是否包含非法字符
        for (String word : illegalWords) {
            if (str.contains(word)) {
                throw new BeeException(ErrorCode.INVALID_SYMBOL);
            }
        }

        return str;
    }
}
