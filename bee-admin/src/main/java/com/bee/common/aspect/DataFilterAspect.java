package com.bee.common.aspect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.bee.common.annotation.DataFilter;
import com.bee.common.constant.Constant;
import com.bee.common.exception.BeeException;
import com.bee.common.exception.ErrorCode;
import com.bee.common.interceptor.DataScope;
import com.bee.modules.security.user.SecurityUser;
import com.bee.modules.security.user.UserDetail;
import com.bee.modules.sys.dict.SuperAdminEnum;
import com.qiniu.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/22
 * @description 数据过滤，切面处理
 */
@Aspect
@Component
public class DataFilterAspect {
    @Pointcut("@annotation(com.bee.common.annotation.DataFilter)")
    public void dataFilterAspect() {
    }

    @Before("dataFilterAspect()")
    public void dataFilter(JoinPoint point) throws Exception {
        Object params = point.getArgs()[0];
        if (Objects.nonNull(params) && params instanceof Map) {
            UserDetail user = SecurityUser.getUser();

            // super admin not do filter
            if (SuperAdminEnum.YES.getValue() == user.getSupperAdmin()) {
                return;
            }

            Map paramsMap = (Map) params;
            String sqlFilter = getSqlFilter(user, point);
            paramsMap.put(Constant.SQL_FILTER, new DataScope(sqlFilter));
            return;
        }

        throw new BeeException(ErrorCode.DATA_SCOPE_PARAMS_ERROR);
    }

    /**
     * 获取数据过滤的SQL
     */
    private String getSqlFilter(UserDetail user, JoinPoint point) throws Exception {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = point.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        DataFilter dataFilter = method.getAnnotation(DataFilter.class);

        // 获取表别名
        String tableAlias = dataFilter.tableAlias();
        if (StrUtil.isNotBlank(tableAlias)) {
            tableAlias += ".";
        }

        StringBuilder sqlFilter = new StringBuilder();
        sqlFilter.append(" (");

        // 部门ID列表
        List<Long> deptIdList = user.getDeptIdList();
        if (CollUtil.isNotEmpty(deptIdList)) {
            sqlFilter.append(tableAlias).append(dataFilter.deptId());

            sqlFilter.append(" (in(").append(StringUtils.join(deptIdList, ",")).append(")");
        }

        // 获取用户数据
        if (CollUtil.isNotEmpty(deptIdList)) {
            sqlFilter.append(" or ");
        }
        sqlFilter.append(tableAlias).append(dataFilter.userId()).append("=").append(user.getUserId());
        sqlFilter.append(")");

        return sqlFilter.toString();
    }
}
