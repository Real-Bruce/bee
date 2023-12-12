package com.bee.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.bee.common.page.PageData;
import com.bee.common.service.CRUDService;
import com.bee.common.util.common.ConvertUtils;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2023/12/05
 * @description
 */
public abstract class CRUDServiceImpl<M extends BaseMapper<T>, T, D> extends BaseServiceImpl<M, T> implements CRUDService<T, D> {


    protected Class<D> currentDtoClass() {
        return (Class<D>) ReflectionKit.getSuperClassGenericType(getClass(), CRUDService.class, 2);
    }

    public abstract QueryWrapper<T> getWrapper(Map<String, Object> params);

    @Override
    public PageData<D> page(Map<String, Object> params) {
        IPage<T> page = baseMapper.selectPage(
                getPage(params, null, false),
                getWrapper(params)
        );
        return getPageData(page, currentDtoClass());
    }

    @Override
    public List<D> list(Map<String, Object> params) {
        List<T> entityList = baseMapper.selectList(getWrapper(params));
        return ConvertUtils.sourceToTarget(entityList, currentDtoClass());
    }

    @Override
    public D getById(Long id) {
        T entity = baseMapper.selectById(id);
        return ConvertUtils.sourceToTarget(entity, currentDtoClass());
    }

    @Override
    public void save(D dto) {
        T entity = ConvertUtils.sourceToTarget(dto, currentModelClass());
        insert(entity);

        // 拷贝主键值
        BeanUtils.copyProperties(entity, dto);
    }

    @Override
    public void update(D dto) {
        T entity = ConvertUtils.sourceToTarget(dto, currentModelClass());
        updateById(entity);
    }

    @Override
    public void delete(Long[] ids) {
        baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

}
