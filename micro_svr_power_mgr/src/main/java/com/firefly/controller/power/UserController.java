package com.firefly.controller.power;

import com.firefly.controller.BaseController;
import com.firefly.controller.CrudController;
import com.firefly.repository.power.UserRepository;
import com.firefly.entity.power.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/3
 * @Description
 */
@RestController
@RefreshScope
@RequestMapping("/user")
public class UserController extends CrudController<User> {
    UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @RequestMapping("/get")
    public List<User> get(
            @RequestParam(value = "id") String id) {
        return repository.findByIdAndDelIsFalse(id);
    }
}