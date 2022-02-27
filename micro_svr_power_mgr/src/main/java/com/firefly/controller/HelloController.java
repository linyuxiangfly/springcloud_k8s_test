package com.firefly.controller;

import com.firefly.controller.BaseController;
import com.firefly.entity.power.User;
import com.firefly.repository.power.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author fly
 * @Date 2019/6/3
 * @Description
 */
public class HelloController implements BaseController {
    @Autowired
    UserRepository repository;

    @Value("${hello.num}")
    Integer num=0;

    @Value("${neo.hello}")
    private String hello;

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is first messge "+num+","+hello;
    }


    @RequestMapping("/get")
    //Propagation.REQUIRED如果上级调用已经开启过事务则使用上级的事务，当前就不开启事务
    @Transactional(rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
    public User get(
            @RequestParam(value = "id") String id) {
//        User retVal=new User();
//        retVal.setName(name);
//        retVal.setPwd("123456");

        List<User> users=repository.findByIdAndDelIsFalse(id);
        if(users!=null && users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }

    //    首先要设置consumes为multipart/form-data
    @PostMapping(value = "/files", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> uploadFile(@RequestParam("file")MultipartFile file){
        System.out.println(file.getName());
        return null;
    }
}