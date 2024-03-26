package com.bee.common.util.common;

import com.bee.common.util.TreeNode;
import com.bee.common.util.validator.AssertUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Bruce
 * @create 2023/12/05
 * @description 树形结构工具类，如构建菜单、部门等
 */
public abstract class TreeUtils {

    /**
     * 根据pid构建树节点
     */
    public static <T extends TreeNode> List<T> build(List<T> treeNodes, Long pid) {
        AssertUtils.isNull(pid, "pid");

        List<T> treeList = new ArrayList<>();
        for (T node : treeNodes) {
            if (pid.equals(node.getPid())) {
                treeList.add(findChildren(treeNodes, node));
            }
        }

        return treeList;
    }

    /**
     * 递归查找子节点
     */
    private static <T extends TreeNode> T findChildren(List<T> treeNodes, T rootNode) {
        for (T treeNode : treeNodes) {
            if (rootNode.getId().equals(treeNode.getPid())) {
                treeNode.getChildren().add(findChildren(treeNodes, treeNode));
            }
        }
        return rootNode;
    }

    public static <T extends TreeNode> List<T> build(List<T> treeNodes) {
        List<T> result = new ArrayList<>();

        // list => map
        LinkedHashMap<Long, T> nodeMap = treeNodes.stream().collect(Collectors.toMap(T::getId, Function.identity(), (existing, replacement) -> existing, LinkedHashMap::new));

        for (T node : nodeMap.values()) {
            T parent = nodeMap.get(node.getPid());

            if (Objects.nonNull(parent) && !(node.getId()).equals(parent.getPid())) {
                parent.getChildren().add(node);
                continue;
            }

            result.add(node);
        }

        return result;
    }
}
