package com.bee.modules.security.service;

import com.bee.common.service.BaseService;
import com.bee.common.util.ResultVO;
import com.bee.modules.security.entity.SysUserToken;
import org.springframework.stereotype.Service;

/**
 * @author Bruce
 * @create 2023/12/11
 * @desc SysUserTokenService
 */
@Service
public interface SysUserTokenService extends BaseService<SysUserToken> {

    /**
     * 生成token
     * @param userId 用户ID
     */
    ResultVO createToken(Long userId);

    /**
     * 注销登录，销毁token
     * @param userId
     */
    void logout(Long userId);

}
