package com.bee.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/06
 * @description Exception工具类
 */
public abstract class ExceptionUtils {

    /**
     * 获取异常堆栈信息
     */
    public static String getErrorStackTrace(Exception ex) {
        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw, true);
        } finally {
            try {
                if (Objects.nonNull(pw)) {
                    pw.close();
                }
            } catch (Exception e) {

            }

            try {
                if (Objects.nonNull(sw)) {
                    sw.close();
                }
            } catch (Exception e) {

            }
        }

        return sw.toString();
    }
}
