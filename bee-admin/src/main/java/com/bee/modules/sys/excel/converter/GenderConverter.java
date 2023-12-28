package com.bee.modules.sys.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @author Bruce
 * @create 2023/12/28
 * @description
 */
public class GenderConverter implements Converter<Integer> {

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

        if (cellData.getStringValue().equals("男")) {
            return 0;
        } else if (cellData.getStringValue().equals("女")) {
            return 1;
        } else {
            return 2;
        }

    }

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        if (value == 0){
            return new WriteCellData<>("男");
        } else if (value == 1) {
            return new WriteCellData<>("女");
        } else {
            return new WriteCellData<>("保密");
        }
    }
}
