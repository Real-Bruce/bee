package com.bee.modules.sys.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author Bruce
 * @create 2024/01/10
 * @description dict data
 */
@Data
public class DictDataVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long dictTypeId;

    private String dictLabel;

    private String dictValue;
}
