package com.bee.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bruce
 * @create 2023/11/17
 * @description 通用entity
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5634536428309478754L;

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
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
}
