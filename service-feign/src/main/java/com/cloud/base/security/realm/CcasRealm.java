package com.cloud.base.security.realm;

import com.cloud.base.security.Principal;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.json.JsonMapper;

public class CcasRealm extends CasRealm {

    private static Logger LOGGER = LoggerFactory.getLogger(CcasRealm.class);


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CasToken casToken = (CasToken) token;
        if (casToken == null) {
            return null;
        }
        String ticket = (String) casToken.getCredentials();
        LOGGER.info("登录CCAS,ticket为:" + ticket);
        if (!StringUtils.isNotBlank(ticket)) {
            return null;
        }
        try {
            /**
             * 这里写从数据源获取数据的方法...
             */
            LOGGER.info(JsonMapper.getDefault().toJson(""));
            Principal principal = new Principal();
            principal.setAccount("");
            return new SimpleAuthenticationInfo(principal, ticket, getName());
        } catch (Exception e) {
            LOGGER.error("获取principal出现异常!" + e.getMessage(), e);
            throw new CasAuthenticationException("ccas 无法校验 ticket [" + ticket + "]", e);
        }
    }

}
