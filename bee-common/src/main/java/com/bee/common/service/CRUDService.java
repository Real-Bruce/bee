package com.bee.common.service;

import com.bee.common.page.PageData;

import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/05
 * @description CRUD基本服务接口
 */
public interface CRUDService <T, D> extends BaseService<T> {

    PageData<D> page(Map<String, Object> params);

    List<D> list(Map<String, Object> params);

    D getById(Long id);

    void save(D dto);

    void update(D dto);

    void delete(Long[] ids);
}
