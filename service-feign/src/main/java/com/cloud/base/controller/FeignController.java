package com.cloud.base.controller;

import beans.ResultBean;
import com.cloud.base.gateway.TestFeignGateway;
import com.cloud.base.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignController {

    @Autowired
    TestFeignGateway testFeignGateway;

    @RequestMapping(value = "/testFeign", method = RequestMethod.GET)
    public ResultBean<String> testFeign() {
        return testFeignGateway.testFeign();
    }

    @RequestMapping("/hello")
    private String index() {
        return "Hello World!";
    }

    @RequestMapping(value = "/testWSC", method = RequestMethod.GET)
    public String testWSC() {
        return testFeignGateway.hello();
    }

    @RequestMapping(value = "/testWSCMenu", method = RequestMethod.GET)
    public ResultBean<List<Menu>> testWSCMenu() {
        return testFeignGateway.menuList();
    }



}
