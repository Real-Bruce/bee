package com.bee.modules.security.service.impl;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.bee.common.redis.RedisKeys;
import com.bee.common.redis.RedisUtils;
import com.bee.modules.security.service.CaptchaService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/13
 * @description
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource
    private RedisUtils redisUtils;

    @Value("${bee.redis.enable}")
    private boolean redisEnable;

    Cache<String, String> localCache = CacheUtil.newLRUCache(1000, 1000 * 60 * 3);

    @Override
    public void create(HttpServletResponse response, String uuid) throws IOException {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        captcha.out(response.getOutputStream());

        // 保存到缓存
        setCaptchaCache(uuid, captcha.text());
    }

    @Override
    public boolean validate(String uuid, String code) {
        return code.equalsIgnoreCase(getCaptchaCache(uuid));
    }

    /**
     * 验证码图片存入缓存
     */
    private void setCaptchaCache(String key, String value) {
        if (redisEnable) {
            key = RedisKeys.getCaptchaKey(key);
            redisUtils.set(key, value, 180);
        } else {
            localCache.put(key, value);
        }
    }

    /**
     * 从缓存获取验证码图片
     */
    private String getCaptchaCache(String key) {
        if (redisEnable) {
            key = RedisKeys.getCaptchaKey(key);
            String captcha = (String) redisUtils.get(key);

            // 删除验证码缓存
            if (Objects.nonNull(captcha)) {
                redisUtils.delete(key);
            }
            return captcha;
        } else {

            String captcha = localCache.get(key);
            if (Objects.nonNull(captcha)) {
                // 删除验证码缓存
                localCache.remove(key);
            }
            return captcha;
        }
    }
}
