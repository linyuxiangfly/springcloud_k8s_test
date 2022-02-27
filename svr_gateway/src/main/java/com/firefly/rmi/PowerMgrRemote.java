package com.firefly.rmi;

import com.firefly.entity.power.Func;
import com.firefly.entity.power.User;
import com.firefly.entity.power.UserAuths;
import com.firefly.utils.MenuItemUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author fly
 * @Date 2019/6/3
 * @Description
 */
//@FeignClient(name= "${feign_client.service-id-micro-svr-power-mgr}",fallbackFactory = UserRemoteFallback.class)
//@FeignClient(name= "micro-svr-power-mgr",fallback = UserRemoteHystrix.class)
@FeignClient(name= "${feign_client.service-id-micro-svr-power-mgr}")
//@RequestMapping("/user")
public interface PowerMgrRemote {
    //-------------------user----------------------------
    @RequestMapping("/userAuths/get")
    List<UserAuths> user_auths_get(
            @RequestParam(value = "identityType") String identityType,
            @RequestParam(value = "identityFier") String identityFier);

    //-------------------user----------------------------

    //-------------------user----------------------------
    @RequestMapping("/user/get")
    List<User> user_get(@RequestParam("id") String id);

    @RequestMapping("/user/getById")
    User user_getById(@RequestParam("id") String id);
    //-------------------user----------------------------


    //-------------------power----------------------------
    @RequestMapping("/power/getFuncByUser")
    List<Func> power_getFuncByUser(
            @RequestParam(value = "userId") String userId);

    @RequestMapping("/power/getMenuTreeByUser")
    List<MenuItemUtil.MenuItem> power_getMenuTreeByUser(
            @RequestParam(value = "userId") String userId);
    //-------------------power----------------------------
}
