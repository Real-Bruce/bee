package com.bee.modules.sys.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author Bruce
 * @create 2023/12/14
 * @desc SysParams
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_params")
public class SysParams implements Serializable {
    private static final long serialVersionUID = 231012303849603233L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * 参数编码
    */
    public static final String PARAM_CODE = "PARAM_CODE";
    /**
    * 参数值
    */
    public static final String PARAM_VALUE = "PARAM_VALUE";
    /**
    * 类型   0：系统参数   1：非系统参数
    */
    public static final String PARAM_TYPE = "PARAM_TYPE";
    /**
    * 备注
    */
    public static final String REMARK = "REMARK";
    /**
    * 创建者
    */
    public static final String CREATOR = "CREATOR";
    /**
    * 创建时间
    */
    public static final String CREATE_DATE = "CREATE_DATE";
    /**
    * 更新者
    */
    public static final String UPDATER = "UPDATER";
    /**
    * 更新时间
    */
    public static final String UPDATE_DATE = "UPDATE_DATE";

    /**
    * id
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
    * 参数编码
    */
    @TableField("param_code")
    private String paramCode;
    /**
    * 参数值
    */
    @TableField("param_value")
    private String paramValue;
    /**
    * 类型   0：系统参数   1：非系统参数
    */
    @TableField("param_type")
    private Integer paramType;
    /**
    * 备注
    */
    @TableField("remark")
    private String remark;
    /**
    * 创建者
    */
    @TableField("creator")
    private Long creator;
    /**
    * 创建时间
    */
    @TableField("create_date")
    private Date createDate;
    /**
    * 更新者
    */
    @TableField("updater")
    private Long updater;
    /**
    * 更新时间
    */
    @TableField("update_date")
    private Date updateDate;

}

