package com.cloud.base.gateway;

import beans.ResultBean;
import com.cloud.base.model.Menu;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "wsc-service", url = "http://127.0.0.1:8801/base-portal", configuration = ApiConfig.class)
public interface TestFeignGateway {

    @RequestMapping(value = "/testResultBean", method = RequestMethod.GET)
    ResultBean<String> testFeign();

    @RequestMapping(value = "/frontpages/help/hello", method = RequestMethod.GET)
    String hello();

    @RequestMapping(value = "/frontpages/help/menu/HELP", method = RequestMethod.GET)
    ResultBean<List<Menu>> menuList();


}
