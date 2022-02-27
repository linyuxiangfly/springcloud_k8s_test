package com.firefly.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAutoConfiguration
@ComponentScan({"com.firefly.main","com.firefly.controller","com.firefly.config","com.firefly.advice"})
@EntityScan(basePackages = {"com.firefly.entity"})
@EnableJpaRepositories(basePackages = "com.firefly.repository")
@ServletComponentScan(basePackages = "com.firefly.filter")
@SpringBootApplication
@EnableDiscoveryClient //启用服务注册与发现
@EnableCaching//开启缓存
public class MicroSvrPowerMgrApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroSvrPowerMgrApplication.class, args);
	}

}
