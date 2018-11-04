package com.cloud.base.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PortalConfigInit {
    private static final Logger LOGGER = LoggerFactory.getLogger(PortalConfigInit.class);

    private String test = "";

    public void init() {
        LOGGER.info("加载properties, 并初始化参数...");

    }
}
