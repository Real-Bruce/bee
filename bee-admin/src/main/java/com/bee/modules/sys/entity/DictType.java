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
 * @create 2024/01/09
 * @desc DictType
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_dict_type")
public class DictType extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 347132628386958091L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 字典类型
    */
    public static final String DICT_TYPE = "DICT_TYPE";
    /**
    * 字典名称
    */
    public static final String DICT_NAME = "DICT_NAME";
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
    * 字典类型
    */
    @TableField("dict_type")
    private String dictType;
    /**
    * 字典名称
    */
    @TableField("dict_name")
    private String dictName;
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

