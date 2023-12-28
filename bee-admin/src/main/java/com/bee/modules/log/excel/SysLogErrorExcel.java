package com.bee.modules.log.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * @author Bruce
 * @create 2023/12/21
 * @description 异常日志导出Excel
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class SysLogErrorExcel {
    @ExcelProperty("请求URI")
    private String requestUri;

    @ExcelProperty("请求方式")
    private String requestMethod;

    @ExcelProperty("请求参数")
    private String requestParams;

    @ExcelProperty("User-Agent")
    private String userAgent;

    @ExcelProperty("操作IP")
    private String ip;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("创建时间")
    private Date createDate;
}