package com.cloud.base.security.filter;


import com.cloud.base.security.Principal;
import com.cloud.base.security.ShiroConfig;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.json.JsonMapper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends FormAuthenticationFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        LOGGER.info("进入身份验证拦截器...");
        Subject subject = getSubject(request, response);
        Principal principal = (Principal) subject.getPrincipal();
        LOGGER.info("principal为:" + JsonMapper.getDefault().toJson(principal));
        return principal != null;
    }

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        LOGGER.info("身份验证拦截器重定向...");
        if (isLoginUri((HttpServletRequest) request)) {
            String loginUrl = getLoginUrl();
            LOGGER.info("重定向的url为:" + loginUrl);
            WebUtils.issueRedirect(request, response, loginUrl);
        } else {
            ((HttpServletResponse) response).setStatus(401);
        }
    }

    private boolean isLoginUri(HttpServletRequest request) {
        String uri = request.getServletPath();
        LOGGER.info("uri为:" + uri);
        if (uri.endsWith("/") && uri.length() > 1) {
            uri = uri.substring(0, uri.length() - 1);
        }
        return uri.equals(ShiroConfig.APP_LOGIN_URI);
    }

}
