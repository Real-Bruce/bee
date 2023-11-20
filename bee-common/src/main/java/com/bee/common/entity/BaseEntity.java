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
 * @description
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5634536428309478754L;

    /**
     * Id
     */
    @TableId
    private Long id;

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
}
