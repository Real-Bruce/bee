package com.bee.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/11/20
 * @description
 */
public abstract class ConvertUtils {

    private static Logger logger = LoggerFactory.getLogger(ConvertUtils.class);

    public static <T> T sourceToTarget(Object source, Class<T> target) {
        if (Objects.isNull(source)) {
            return null;
        }

        T targetSource = null;

        try {
            targetSource = target.newInstance();
            BeanUtils.copyProperties(source, targetSource);
        } catch (Exception e) {
            logger.error("convert error ", e);
        }

        return targetSource;
    }

    public static <T> List<T> sourceToTarget(Collection<?> sourceList, Class<T> target) {
    if (Objects.isNull(sourceList)) {
        return null;
    }

        List<T> targetList = new ArrayList<>(sourceList.size());
        try {
            for (Object source : sourceList) {
                T targetObject = target.newInstance();
                BeanUtils.copyProperties(source, targetObject);
                targetList.add(targetObject);
            }
        } catch (Exception e) {
            logger.error("convert list error ", e);
        }

        return targetList;
    }
}
