package com.firefly.controller;

import com.firefly.entity.BaseTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author fly
 * @Date 2019/6/3
 * @Description
 */
//@RestController
@RefreshScope // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
//@CrossOrigin(origins = "*", maxAge = 3600)//允许跨域
public interface BaseController {
}