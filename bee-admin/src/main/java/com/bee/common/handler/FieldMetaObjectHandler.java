package com.bee.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.bee.modules.security.user.SecurityUser;
import com.bee.modules.security.user.UserDetail;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

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
        UserDetail user = SecurityUser.getUser();
        Date date = new Date();
        // 创建人
        strictInsertFill(metaObject, CREATOR, Long.class, user.getUserId());
        // 创建时间
        strictInsertFill(metaObject, CREATE_DATE, Date.class, date);
        // 创建者所属部门
        strictInsertFill(metaObject, DEPT_ID, Long.class, user.getDeptId());
        // 更新者
        strictInsertFill(metaObject, UPDATER, Long.class, user.getUserId());
        // 更新时间
        strictInsertFill(metaObject, UPDATE_DATE, Date.class, date);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新人
        strictUpdateFill(metaObject, UPDATE_DATE, Date.class, new Date());
        // 更新时间
        strictUpdateFill(metaObject, UPDATER, Long.class, SecurityUser.getUserId());
    }
}
