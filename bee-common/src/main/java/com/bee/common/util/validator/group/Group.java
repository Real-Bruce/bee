package com.bee.common.util.validator.group;

import javax.validation.GroupSequence;

/**
 * @author Bruce
 * @create 2023/12/06
 * @description 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public class Group {
}
