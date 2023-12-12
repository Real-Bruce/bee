package com.bee.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author Bruce
 * @create 2023/12/11
 * @description 公共字段自动填充
 */
public class FieldMetaObjectHandler implements MetaObjectHandler {

    private final static String CREATE_DATE = "createDate";

    private final static String CREATOR = "creator";

    private final static String UPDATE_DATE = "updateDate";

    private final static String UPDATER = "updater";

    private final static String DEPT_ID = "deptId";

    @Override
    public void insertFill(MetaObject metaObject) {
        // todo 待完善
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // todo 待完善
    }
}
