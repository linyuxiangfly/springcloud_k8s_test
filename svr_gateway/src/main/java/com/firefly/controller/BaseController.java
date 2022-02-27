package com.firefly.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 描述：
 * <p>
 *
 * @author: 赵新国
 * @date: 2018/6/5 18:35
 */
@CrossOrigin(origins = "*", maxAge = 3600)//允许跨域
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;
}
