package com.bee.modules.security.mapper;

import com.bee.common.mapper.BMapper;
import com.bee.modules.security.entity.SysUserToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Bruce
 * @create 2023/12/11
 * @desc SysUserTokenMapper
 */
@Mapper
public interface SysUserTokenMapper extends BMapper<SysUserToken> {

    /**
     * 根据token查询
     */
    SysUserToken getByToken(@Param("token") String token);

    /**
     * 根据userId查询
     */
    SysUserToken getByUserId(@Param("userId") Long userId);

    /**
     * 更新授权情况
     */
    void updateToken(@Param("userId")Long userId, @Param("token")String token);
}

