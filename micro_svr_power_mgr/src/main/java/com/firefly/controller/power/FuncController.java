package com.firefly.controller.power;

import com.firefly.controller.BaseController;
import com.firefly.controller.CrudController;
import com.firefly.entity.power.Func;
import com.firefly.repository.power.FuncRepository;
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
@RequestMapping("/func")
public class FuncController extends CrudController<Func> {
    FuncRepository repository;

    @Autowired
    public void setRepository(FuncRepository repository) {
        this.repository = repository;
        super.setRepository(repository);
    }

    @RequestMapping("/get")
    public List<Func> get(
            @RequestParam(value = "name") String name) {
        return repository.findByNameAndDel(name,false);
    }
}