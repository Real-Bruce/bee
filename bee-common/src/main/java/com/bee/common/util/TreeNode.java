package com.bee.common.util;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruce
 * @create 2023/12/05
 * @description 树节点，所有树节点的父类
 */
@Data
public class TreeNode<T> implements Serializable {

    private static final long serialVersionUID = -6354916670732961413L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 父级ID
     */
    private Long pid;

    /**
     * 子节点列表
     */
    private List<T> children = new ArrayList<>();

}
