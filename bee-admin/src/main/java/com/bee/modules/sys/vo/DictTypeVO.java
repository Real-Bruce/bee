package com.bee.modules.sys.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruce
 * @create 2024/01/10
 * @description dict type
 */
@Data
public class DictTypeVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String dictType;

    private List<DictDataVO> dictDataVOList = new ArrayList<>();
}
