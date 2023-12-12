package com.bee.oauth2;

import com.bee.common.exception.ErrorCode;
import com.bee.common.util.common.ConvertUtils;
import com.bee.common.util.common.MessageUtils;
import com.bee.modules.security.entity.SysUserToken;
import com.bee.modules.security.service.ShiroService;
import com.bee.modules.security.user.UserDetail;
import lombok.AllArgsConstructor;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Bruce
 * @create 2023/12/12
 * @description token认证
 */
@Component
@AllArgsConstructor
public class Oauth2Realm extends AuthorizingRealm {

    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Oauth2Token;
    }

    /**
     * 授权，验证权限时调用
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserDetail user = (UserDetail) principals.getPrimaryPrincipal();

        Set<String> userPermissions = shiroService.getUserPermissions(user);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(userPermissions);

        return info;
    }

    /**
     * 认证，登陆时调用
     * @param token the authentication token containing the user's principal and credentials.
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getCredentials();

        SysUserToken userToken = shiroService.getByToken(accessToken);
        // token失效
        if (Objects.isNull(userToken) || userToken.getExpireDate().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException(MessageUtils.getMessage(ErrorCode.TOKEN_INVALID));
        }

        // 查询用户信息
        SysUserToken user = shiroService.getUser(userToken.getUserId());

        UserDetail userDetail = ConvertUtils.sourceToTarget(user, UserDetail.class);

        // 获取用户对应的部门数据权限
        List<Long> dataScopeList = shiroService.getDataScopeList(userDetail.getUserId());

        // 账号锁定
        if (userDetail.getStatus() == 0) {
            throw new LockedAccountException(MessageUtils.getMessage(ErrorCode.ACCOUNT_LOCK));
        }

        return new SimpleAuthenticationInfo(userDetail, accessToken, getName());
    }
}
