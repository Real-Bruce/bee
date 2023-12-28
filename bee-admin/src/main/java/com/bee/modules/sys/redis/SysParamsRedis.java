package com.bee.modules.sys.redis;

import com.bee.common.redis.RedisKeys;
import com.bee.common.redis.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @author Bruce
 * @create 2023/12/28
 * @description redis 参数管理
 */
@Component
@AllArgsConstructor
public class SysParamsRedis {

    private final RedisUtils redisUtils;

    public void delete(Object[] paramCodes) {
        String key = RedisKeys.getSystemParamsKey();
        redisUtils.hDel(key, paramCodes);
    }

    public void set(String paramCode, String paramValue) {
        if (Objects.isNull(paramValue)) {
            return;
        }

        String key = RedisKeys.getSystemParamsKey();
        redisUtils.hSet(key, paramCode, paramValue);
    }

    public String get(String paramCode) {
        String key = RedisKeys.getSystemParamsKey();
        return (String) redisUtils.hGet(key, paramCode);
    }

}
