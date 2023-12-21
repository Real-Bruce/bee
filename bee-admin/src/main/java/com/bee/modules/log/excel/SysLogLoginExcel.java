package com.bee.modules.log.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.bee.modules.log.excel.converter.SysLogOperationConverter;
import com.bee.modules.log.excel.converter.SysLogStatusConverter;
import lombok.Data;

import java.util.Date;

/**
 * @author Bruce
 * @create 2023/12/21
 * @description 登录日志导出
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class SysLogLoginExcel {
    @ExcelProperty(value = "操作类型", converter = SysLogOperationConverter.class)
    private Integer operation;

    @ExcelProperty(value = "状态", converter = SysLogStatusConverter.class)
    private Integer status;

    @ExcelProperty("user-agent")
    private String userAgent;

    @ExcelProperty("操作IP")
    private String ip;

    @ExcelProperty("用户名")
    private String creatorName;

    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}
