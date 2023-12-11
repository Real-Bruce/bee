package com.bee.common.convert;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/11
 * @description 日期转换
 */
@Component
public class DateConvert implements Converter<String, Date> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateConvert.class);

    private static final List<String> FORMAT_LIST = new ArrayList<>(5);

    static {
        FORMAT_LIST.add("yyyy-MM");
        FORMAT_LIST.add("yyyy-MM-dd");
        FORMAT_LIST.add("yyyy-MM-dd HH:mm");
        FORMAT_LIST.add("yyyy-MM-dd HH:mm:ss");
        FORMAT_LIST.add("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }


    @Override
    public Date convert(String source) {
        String value = source.trim();
        if (StrUtil.isEmpty(value)) {
            return null;
        }

        if (source.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(source, FORMAT_LIST.get(0));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source, FORMAT_LIST.get(1));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, FORMAT_LIST.get(2));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, FORMAT_LIST.get(3));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}.*T.*\\d{1,2}:\\d{1,2}:\\d{1,2}.*..*$")) {
            return parseDate(source, FORMAT_LIST.get(4));
        } else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }

    /**
     * 格式化Date
     * @param dateStr 字符型日期
     * @param format 日期格式
     * @return 格式化后的日期对象
     */
    public Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            LOGGER.error("Formatted date with date: {} and format {}", dateStr, format);
        }
        return date;
    }

}
