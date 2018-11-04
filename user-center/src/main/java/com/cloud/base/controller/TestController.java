package com.cloud.base.controller;

import beans.ResultBean;
import com.cloud.base.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping("/hello")
    private String index() {
        return "Hello World!";
    }

    @RequestMapping("/testResultBean")
    public ResultBean<String> testResultBean() {
        return new ResultBean<>("hello resultBean");
    }

    @RequestMapping(value = "/testDbConnect")
    @ResponseBody
    public ResultBean<String> testDbConnect() {
        return new ResultBean<>(testMapper.testQuery());
    }

}
