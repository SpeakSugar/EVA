package com.cloud.base.controller;

import com.cloud.base.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Source;

@RestController
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;
    @RequestMapping(value = "/testConsume")
    public String hi(@RequestParam String name){
        System.out.println(name);
        return consumerService.testConsume(name);
    }
    @RequestMapping(value = "/hi")
    public void hello(){
        System.out.println("hello ribbon!");
    }
}
