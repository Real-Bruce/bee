package com.bee.common.dynamic.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Bruce
 * @create 2024/01/12
 * @description 多数据源属性
 */
@ConfigurationProperties(prefix = "dynamic")
public class DynamicDataSourceProperties {

    private Map<String, DataSourceProperties> dataSource = new LinkedHashMap<>();

    public Map<String, DataSourceProperties> getDataSource() {
        return dataSource;
    }

    public void setDataSource(Map<String, DataSourceProperties> dataSource) {
        this.dataSource = dataSource;
    }
}
