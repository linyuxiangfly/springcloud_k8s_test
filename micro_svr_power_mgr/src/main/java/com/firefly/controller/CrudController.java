package com.firefly.controller;

import com.firefly.entity.BaseTable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
public class CrudController<T extends BaseTable> implements BaseController{
    private JpaRepository<T,String> repository=null;

    public JpaRepository<T, String> getRepository() {
        return repository;
    }

    public void setRepository(JpaRepository<T, String> repository) {
        this.repository = repository;
    }

    /**
     * 用ID查找用户
     * @param id
     * @return
     */
    @GetMapping("getById")
    public T getById(@RequestParam("id") String id){
        Optional<T> data=repository.findById(id);
        return data.get();
    }

    /**
     * 添加
     * @param t
     * @return
     */
    @PostMapping("")
    public boolean add(@RequestBody T t){
        repository.save(t);
        return true;
    }

    /**
     * 修改
     * @param t
     * @return
     */
    @PutMapping("")
    public boolean update(@RequestBody T t){
        Optional<T> data=repository.findById(t.getId());
        data.get();
        if(t!=null){
            repository.save(t);
            return true;
        }else
        {
            return false;
        }
    }

    /**
     * 删除
     * @param t
     * @return
     */
    @DeleteMapping("")
    public boolean del(@RequestBody T t){
        Optional<T> data=repository.findById(t.getId());
        t=data.get();
        t.setDel(true);
        repository.save(t);
        return true;
    }

    /**
     * 恢复
     * @param t
     * @return
     */
    @DeleteMapping("restore")
    public boolean restore(@RequestBody T t){
        Optional<T> data=repository.findById(t.getId());
        t=data.get();
        t.setDel(false);
        repository.save(t);
        return true;
    }
}