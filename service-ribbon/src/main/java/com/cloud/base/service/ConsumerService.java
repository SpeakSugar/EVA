package com.cloud.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {

    @Autowired
    RestTemplate restTemplate;

    public String testConsume(String name) {
        return restTemplate.getForObject("http://USER-CENTER/testEurekaClient?name="+name,String.class);
    }

}
