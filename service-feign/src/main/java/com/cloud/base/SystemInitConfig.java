package com.cloud.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import system.App;
import system.AppChain;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统初始化配置 app调用链、全局语言变量
 */
@Configuration
public class SystemInitConfig {

    private Logger LOGGER = LoggerFactory.getLogger(SystemInitConfig.class);

    //前台前端语言设置
    private static final ThreadLocal<String> LANGUAGE = new ThreadLocal<>();

    @Bean
    public AppChain appChain() {
        AppChain appChain = new AppChain();
        List<App> appList = new ArrayList<>();
        appChain.setApps(appList);
        return appChain;
    }

    public static void setLanguage(String language) {
        LANGUAGE.set(language);
    }

    public static String getLanguage() {
        return LANGUAGE.get();
    }

}
