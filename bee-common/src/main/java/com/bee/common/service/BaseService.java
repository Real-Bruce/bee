package com.bee.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Bruce
 * @create 2023/11/20
 * @description 通用service层
 */
public interface BaseService<T> {

    /**
     * 获取当前类的类型
     * @return
     */
    Class<T> currentModelClass();

    /**
     * 单条插入
     * @param entity 实体对象
     * @return
     */
    boolean insert(T entity);

    /**
     * 批量插入，不支持oracle、SQL server
     * @param entityList 实体对象集合
     * @return
     */
    boolean insertBatch(Collection<T> entityList);

    /**
     * 批量插入，不支持Oracle、SQL server
     * @param entityList 实体对象集合
     * @param batchSize 插入批次数量
     * @return
     */
    boolean insertBatch(Collection<T> entityList, int batchSize);

    /**
     * 根据ID修改
     * @param entity 实体对象
     * @return
     */
    boolean updateById(T entity);

    /**
     * 根据whereEntity条件，更新记录
     * @param entity 实体对象
     * @param updateWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper}
     * @return
     */
    boolean update(T entity, Wrapper<T> updateWrapper);

    /**
     * 根据Id 批量更新
     * @param entityList 实体对象集合
     * @return
     */
    boolean updateBatchById(Collection<T> entityList);

    /**
     * 根据ID 批量更新
     * @param entityList 实体对象集合
     * @param batchSize 更新批次数量
     * @return
     */
    boolean updateBatchById(Collection<T> entityList, int batchSize);

    /**
     * 根据ID查询
     * @param id 主键ID
     * @return
     */
    T selectById(Serializable id);

    /**
     * 根据ID删除
     * @param id 主键ID
     * @return
     */
    boolean deleteById(Serializable id);

    /**
     * 根据ID集合批量删除
     * @param idList 主键ID集合
     * @return
     */
    boolean deleteBachIds(Collection<? extends  Serializable> idList);
}
