package com.bee.common.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/05
 * @description 时间处理工具类
 */
public abstract class DateUtils {

    /**
     * 时间格式 yyyy-MM-dd
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式yyyy-MM-dd
     * @param date 待格式化日期
     * @return 返回日期格式yyyy-MM-dd
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化
     * @param date 待格式化日期
     * @param pattern 格式化参数DateUtils.DATE_TIME_PATTERN
     * @return 格式化后的日期
     */
    public static String format(Date date, String pattern) {
        if (Objects.isNull(date)) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 日期解析
     * @param date String类型日期
     * @param pattern 格式化样式
     * @return
     */
    public static Date parse(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
