package com.bee.common.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.converters.longconverter.LongStringConverter;
import com.bee.common.util.common.ConvertUtils;
import com.bee.common.util.common.DateUtils;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/21
 * @description excel 工具类
 */
public abstract class ExcelUtils {

    /**
     * Excel 导出工具
     * @param response response
     * @param fileName 文件名称
     * @param sheetName sheetName
     * @param list 导出数据
     * @param pojoClass 数据类型
     */
    public static void exportExcel(HttpServletResponse response, String fileName, String sheetName, List<?> list, Class<?> pojoClass) throws IOException {

        if (StrUtil.isBlank(fileName)) {
            fileName = DateUtils.format(new Date());
        }

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        fileName = URLUtil.encode(fileName, StandardCharsets.UTF_8);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), pojoClass).registerConverter(new LongStringConverter()).sheet(sheetName).doWrite(list);
    }

    public static void exportExcelToTarget(HttpServletResponse response, String fileName, String sheetName, List<?> sourceList, Class<?> targetClass) throws Exception {

        List targetList = new ArrayList<>(sourceList.size());
        for (Object source : sourceList) {
            Object target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }

        exportExcel(response, fileName, sheetName, targetList, targetClass);
    }
}
