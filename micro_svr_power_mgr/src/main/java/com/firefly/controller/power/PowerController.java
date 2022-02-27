package com.firefly.controller.power;

import com.firefly.controller.BaseController;
import com.firefly.entity.power.Func;
import com.firefly.entity.power.User;
import com.firefly.repository.power.*;
import com.firefly.utils.MenuItemUtil;
import com.firefly.utils.PowerUtil;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestHeader;
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
@RequestMapping("/power")
@CacheConfig(cacheNames = "power")
public class PowerController implements BaseController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FuncRepository funcRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleMenuRepository roleMenuRepository;

    @Autowired
    MenuFuncRepository menuFuncRepository;

    @RequestMapping("/getFuncByUser")
    @Cacheable(key="#root.method.name+'::'+#userId", unless = "#result == null")
    public List<Func> getFuncByUser(
            @RequestHeader(value="user.id", required=false) String mySelfId,
            @RequestHeader(value="user.tel", required=false) String mySelfTel,
            @RequestParam(value = "userId") String userId) {
        if(userId!=null){
            User user=new User();
            user.setId(userId);
            return PowerUtil.getFuncByUser(userRoleRepository,roleMenuRepository,menuFuncRepository,user);
        }
        return null;
    }

    @RequestMapping("/getMenuTreeByUser")
    @Cacheable(key="#root.method.name+'::'+#userId", unless = "#result == null")
    public List<MenuItemUtil.MenuItem> getMenuTreeByUser(
            @RequestParam(value = "userId") String userId) throws Exception {
        if(userId!=null){
            User user=new User();
            user.setId(userId);
            return PowerUtil.getMenuTreeByUser(userRoleRepository,roleMenuRepository,user);
        }
        return null;
    }
}