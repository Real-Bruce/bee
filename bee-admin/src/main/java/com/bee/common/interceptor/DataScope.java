package com.bee.common.interceptor;

/**
 * @author Bruce
 * @create 2023/11/20
 * @description 数据范围
 */
public class DataScope {
    private String sqlFilter;

    public DataScope(String sqlFilter) {
        this.sqlFilter = sqlFilter;
    }

    public String getSqlFilter() {
        return sqlFilter;
    }

    public void setSqlFilter(String sqlFilter) {
        this.sqlFilter = sqlFilter;
    }

    @Override
    public String toString() {
        return "DataScope{" +
                "sqlFilter='" + sqlFilter + '\'' +
                '}';
    }
}
