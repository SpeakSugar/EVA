package com.cloud.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class UserCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCenterApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	@RequestMapping("/testEurekaClient")
	public String home(@RequestParam(value = "name", defaultValue = "yufy") String name) {
		return "hi " + name + " ,i am from port:" + port;
	}

}
