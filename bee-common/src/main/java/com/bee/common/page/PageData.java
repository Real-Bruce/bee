package com.bee.common.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Bruce
 * @create 2023/11/20
 * @description 分页工具类
 */
@Data
@ApiModel(value = "分页数据")
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 894021617730115161L;

    @ApiModelProperty(value = "总记录数")
    private int total;

    @ApiModelProperty(value = "列表数据")
    private List<T> list;

    public PageData(List<T> list, long total) {
        this.list = list;
        this.total = (int)total;
    }
}
