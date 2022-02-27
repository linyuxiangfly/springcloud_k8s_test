package com.firefly.controller.power;

import com.firefly.controller.BaseController;
import com.firefly.controller.CrudController;
import com.firefly.entity.power.Role;
import com.firefly.repository.power.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author fly
 * @Date 2019/6/3
 * @Description
 */
@RestController
@RefreshScope
@RequestMapping("/role")
public class RoleController extends CrudController<Role> {
    RoleRepository repository;

    @Autowired
    public void setRepository(RoleRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }
}