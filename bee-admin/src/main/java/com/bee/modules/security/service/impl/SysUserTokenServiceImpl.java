package com.bee.modules.security.service.impl;

import com.bee.common.constant.Constant;
import com.bee.common.service.impl.BaseServiceImpl;
import com.bee.common.util.ResultVO;
import com.bee.modules.security.entity.SysUserToken;
import com.bee.modules.security.mapper.SysUserTokenMapper;
import com.bee.modules.security.service.SysUserTokenService;
import com.bee.oauth2.TokenGenerator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author Bruce
 * @create 2023/12/11
 * @desc SysUserTokenServiceImpl
 */
@Service
public class SysUserTokenServiceImpl extends BaseServiceImpl<SysUserTokenMapper, SysUserToken> implements SysUserTokenService {

    /**
     * 默认2小时过期
     */
    private final static int EXPIRE_TIME = 60 * 60 * 2;
    private final SysUserTokenMapper sysUserTokenMapper;

    public SysUserTokenServiceImpl(SysUserTokenMapper sysUserTokenMapper) {
        this.sysUserTokenMapper = sysUserTokenMapper;
    }

    @Override
    public ResultVO createToken(Long userId) {
        String token;

        // 当前时间
        Date now = new Date();
        // 过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        // 判断是否已经生成token
        SysUserToken userToken = baseMapper.getByUserId(userId);
        if (Objects.isNull(userToken)) {
            // 生成token
            token = TokenGenerator.generateValue();

            userToken = new SysUserToken();
            userToken.setUserId(userId);
            userToken.setToken(token);
            userToken.setCreateDate(now);
            userToken.setExpireDate(expireDate);
            // 保存token
            this.insert(userToken);
        } else {
            // 判断token是否过期，过期则重新生成
            if (userToken.getExpireDate().before(now)) {
                token = TokenGenerator.generateValue();
            } else {
                token = userToken.getToken();
            }

            userToken.setToken(token);
            userToken.setUpdateDate(now);
            userToken.setExpireDate(expireDate);
            // 更新token
            this.updateById(userToken);
        }

        HashMap<String, Object> map = new HashMap<>(2);
        map.put("expire", EXPIRE_TIME);
        map.put(Constant.TOKEN_HEADER, token);

        return new ResultVO().ok(map);
    }

    @Override
    public void logout(Long userId) {
        String token = TokenGenerator.generateValue();

        baseMapper.updateToken(userId, token);
    }
}
