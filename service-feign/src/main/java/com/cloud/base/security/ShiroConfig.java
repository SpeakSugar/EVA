package com.cloud.base.security;


import com.cloud.base.security.filter.AuthenticationFilter;
import com.cloud.base.security.filter.AuthorizationFilter;
import com.cloud.base.security.realm.CcasRealm;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);

    public static final String APP_LOGIN_URI = "/admin/index";

    @Value("${app.main.url}")
    private String APP_MAIN_URL;

    @Value("${app.cas.url}")
    private String APP_CAS_URL;

    @Value("${ccas.login.url}")
    private String CCAS_LOGIN_URL;

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl(CCAS_LOGIN_URL);
        shiroFilterFactoryBean.setSuccessUrl(APP_LOGIN_URI);
        Map<String, Filter> filters = new HashMap<>();
        filters.put("casFilter", casFilter());
        filters.put("loginFilter", authenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/cas", "casFilter");
        filterChainDefinitionMap.put("/admin/index", "loginFilter");
        filterChainDefinitionMap.put("/hello", "loginFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        LOGGER.info(CCAS_LOGIN_URL);
        LOGGER.info(APP_LOGIN_URI);
        LOGGER.info("初始化shiro配置成功...");
        return shiroFilterFactoryBean;
    }

    @Bean
    public CasFilter casFilter() {
        return new CasFilter();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Bean
    public AuthorizationFilter authorizationFilter() {
        return new AuthorizationFilter();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(CcasRealm());
        return securityManager;
    }

    @Bean
    public CcasRealm CcasRealm() {
        return new CcasRealm();
    }

    public String getAPP_MAIN_URL() {
        return APP_MAIN_URL;
    }

    public String getCCAS_LOGIN_URL() {
        return CCAS_LOGIN_URL;
    }
}
