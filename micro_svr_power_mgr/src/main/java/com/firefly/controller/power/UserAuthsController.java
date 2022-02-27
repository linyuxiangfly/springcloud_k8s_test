package com.firefly.controller.power;

import com.firefly.controller.CrudController;
import com.firefly.entity.power.User;
import com.firefly.entity.power.UserAuths;
import com.firefly.repository.power.UserAuthsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/3
 * @Description
 */
@RestController
@RefreshScope
@RequestMapping("/userAuths")
public class UserAuthsController extends CrudController<UserAuths> {
    UserAuthsRepository repository;

    @Autowired
    public void setRepository(UserAuthsRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @RequestMapping("/get")
    public List<UserAuths> get(
            @RequestParam(value = "identityType") String identityType,
            @RequestParam(value = "identityFier") String identityFier
            ) {
        List<UserAuths> userAuthsList=repository.findByIdentityTypeAndIdentityFierAndDelIsFalse(identityType,identityFier);
        return userAuthsList;
    }
}