package com.cloud.base.gateway;

import com.cloud.base.SystemInitConfig;
import feign.RequestInterceptor;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utils.time.TimeUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class ApiConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiConfig.class);

    private static final String accessId = "";

    private static final String secretKey = "";

    @Bean
    public RequestInterceptor headerInterceptor() {
        LOGGER.info("CASS HTTP过滤器创建中...");
        return requestTemplate -> {
            LOGGER.info("...进入CCAS的RequestInterceptor...");
            try {
                String UTC_Date = TimeUtil.transferToUTC(System.currentTimeMillis());
                requestTemplate.header("accessId", accessId);
                requestTemplate.header("timestamp", UTC_Date);
                requestTemplate.header("signature", createSignature(requestTemplate.method(), requestTemplate.url(), UTC_Date));
                requestTemplate.header("Cookie", SystemInitConfig.getLanguage());
            } catch (Exception e) {
                LOGGER.info("构建CCAS请求失败", e);
            }
        };
    }

    //利用签名算法生成签名
    private String createSignature(String httpVerb, String uri, String date) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String toSign = httpVerb + uri + date;
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA1"));
        return Base64.encodeBase64String(mac.doFinal(toSign.getBytes("UTF-8")));
    }
}
