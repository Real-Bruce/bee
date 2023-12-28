package com.bee.modules.sys.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Bruce
 * @create 2023/12/28
 * @description 系统参数excel导出
 */
@Data
public class SysParamsExcel {

    @ExcelProperty("参数编码")
    private String paramCode;

    @ExcelProperty("参数值")
    private String paramValue;

    @ExcelProperty("备注")
    private String remark;
}
