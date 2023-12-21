package com.bee.modules.log.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.bee.modules.log.dict.LoginStatusEnum;

import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/21
 * @description
 */
public class SysLogStatusConverter implements Converter<Integer> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if ("失败".equals(cellData.getStringValue())) {
            return 0;
        } else if ("成功".equals(cellData.getStringValue())) {
            return 1;
        } else if ("账号已锁定".equals(cellData.getStringValue())) {
            return 2;
        } else {
            return -1;
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        LoginStatusEnum loginStatusEnum = LoginStatusEnum.formToEnum(value);

        if (Objects.nonNull(loginStatusEnum)) {
            return new WriteCellData<>(loginStatusEnum.getName());
        }else {
            return new WriteCellData<>("未知");
        }
    }
}
