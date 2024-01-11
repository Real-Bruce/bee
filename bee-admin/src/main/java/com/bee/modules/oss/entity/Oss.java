package com.bee.modules.oss.entity;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author Bruce
 * @create 2024/01/10
 * @desc Oss
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_oss")
public class Oss implements Serializable {
    private static final long serialVersionUID = 616487232796969148L;
    
    /**
    * id
    */
    public static final String ID = "ID";
    /**
    * URL地址
    */
    public static final String URL = "URL";
    /**
    * 创建者
    */
    public static final String CREATOR = "CREATOR";
    /**
    * 创建时间
    */
    public static final String CREATE_DATE = "CREATE_DATE";

    /**
    * id
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
    * URL地址
    */
    @TableField("url")
    private String url;
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

}

