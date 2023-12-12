package com.bee.modules.security.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/12
 * @description
 */
@Data
public class UserDetail implements Serializable {

    private static final long serialVersionUID = -6037308615564336342L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * headUrl
     */
    private String headUrl;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 部门Id
     */
    private Long deptId;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 是否admin用户
     */
    private Integer supperAdmin;
    /**
     * 部门权限
     */
    private List<Long> deptIdList;
}
