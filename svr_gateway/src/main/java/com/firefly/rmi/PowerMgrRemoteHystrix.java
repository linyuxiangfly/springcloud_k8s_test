package com.firefly.rmi;

import com.firefly.entity.power.Func;
import com.firefly.entity.power.User;
import com.firefly.entity.power.UserAuths;
import com.firefly.utils.MenuItemUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/3
 * @Description
 */
@Component
//@RequestMapping("/user")
public class PowerMgrRemoteHystrix implements PowerMgrRemote {
    @Override
    public List<UserAuths> user_auths_get(String identityType,String identityFier){
        return null;
    }

    @Override
    public List<User> user_get(String id) {
        return null;
    }

    @Override
    public User user_getById(String id) {
        return null;
    }

    @Override
    public List<Func> power_getFuncByUser(String userId) {
        return null;
    }

    @Override
    public List<MenuItemUtil.MenuItem> power_getMenuTreeByUser(String userId) {
        return null;
    }
}
