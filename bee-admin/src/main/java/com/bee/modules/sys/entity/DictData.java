package com.bee.modules.sys.entity;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bee.common.entity.BaseEntity;
import lombok.*;

/**
 * @author Bruce
 * @create 2024/01/10
 * @desc DictData
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_dict_data")
public class DictData extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 922010036104642989L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 字典类型ID
    */
    public static final String DICT_TYPE_ID = "DICT_TYPE_ID";
    /**
    * 字典标签
    */
    public static final String DICT_LABEL = "DICT_LABEL";
    /**
    * 字典值
    */
    public static final String DICT_VALUE = "DICT_VALUE";
    /**
    * 备注
    */
    public static final String REMARK = "REMARK";
    /**
    * 排序
    */
    public static final String SORT = "SORT";

    /**
    * id
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
    * 字典类型ID
    */
    @TableField("dict_type_id")
    private Long dictTypeId;
    /**
    * 字典标签
    */
    @TableField("dict_label")
    private String dictLabel;
    /**
    * 字典值
    */
    @TableField("dict_value")
    private String dictValue;
    /**
    * 备注
    */
    @TableField("remark")
    private String remark;
    /**
    * 排序
    */
    @TableField("sort")
    private Integer sort;

}

