package com.bee.common.interceptor;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.StringUtils;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/11/20
 * @description 数据过滤
 */
public class DataFilterInterceptor implements InnerInterceptor {

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        DataScope scope = getDataScope(parameter);

        // 不过滤数据
        if (Objects.isNull(scope) || StrUtil.isBlank(scope.getSqlFilter())) {
            return;
        }

        // 拼接SQL
        String buildSql = getSelect(boundSql.getSql(), scope);

        // 重写SQL
        PluginUtils.mpBoundSql(boundSql).sql(buildSql);
    }

    private DataScope getDataScope (Object parameter) {
        if (Objects.isNull(parameter)) {
            return null;
        }

        // 判断参数里是否有DataScope对象
        if (parameter instanceof Map) {
            Map<?, ?> parameterMap = (Map<?, ?>) parameter;
            for (Map.Entry<?, ?> entry : parameterMap.entrySet()) {

                if (Objects.nonNull(entry.getValue()) && entry.getValue() instanceof DataScope) {
                    return (DataScope) entry.getValue();
                }
            }
        } else if (parameter instanceof DataScope) {
            return (DataScope) parameter;
        }

        return null;
    }

    private String getSelect (String buildSql, DataScope scope) {
        try {
            Select select = (Select) CCJSqlParserUtil.parse(buildSql);
            PlainSelect plainSelect = (PlainSelect) select.getSelectBody();

            Expression expression = plainSelect.getWhere();
            if (Objects.isNull(expression)) {
                plainSelect.setWhere(new StringValue(scope.getSqlFilter()));
            } else {
                AndExpression andExpression = new AndExpression(expression, new StringValue(scope.getSqlFilter()));
                plainSelect.setWhere(andExpression);
            }

            return select.toString().replaceAll("'", "");
        } catch (JSQLParserException e) {
            e.printStackTrace();
            return buildSql;
        }
    }
}
